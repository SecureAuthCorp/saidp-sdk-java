package org.secureauth.sarestapi.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

import javax.net.ssl.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientProperties;
import org.secureauth.sarestapi.data.*;
import org.secureauth.sarestapi.data.BehavioralBio.BehaveBioRequest;
import org.secureauth.sarestapi.data.Requests.BehaveBioResetRequest;
import org.secureauth.sarestapi.data.Response.*;
import org.secureauth.sarestapi.data.Requests.*;
import org.secureauth.sarestapi.data.Requests.UserPasswordRequest;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfile;

import org.secureauth.sarestapi.data.UserProfile.UserToGroups;
import org.secureauth.sarestapi.data.UserProfile.UsersToGroup;
import org.secureauth.sarestapi.exception.SARestAPIException;
import org.secureauth.sarestapi.filters.SACheckRequestFilter;
import org.secureauth.sarestapi.guid.GUIDStrategy;
import org.secureauth.sarestapi.guid.XRequestIDFilter;
import org.secureauth.sarestapi.ssl.SATrustManagerFactory;
import org.secureauth.sarestapi.util.JSONUtil;
import org.secureauth.sarestapi.util.RestApiHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Jersey 2 Libs
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


/**
 * @author rrowcliffe@secureauth.com
 *
Copyright (c) 2015, SecureAuth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class SAExecuter {

    private Client client = null;
    private static final Logger logger = LoggerFactory.getLogger(SAExecuter.class);
    private static final String TEN_SECONDS = "10000";
    private static final String TLS = "TLS";
    // The IdP Cloud version uses "INGRESSCOOKIE" as fixed value to support sticky sessions.
    private static final String SESSION_AFFINITY_COOKIE_NAME = "INGRESSCOOKIE";
    private Integer idpApiTimeout;
    // Default is do nothing.
    private ClientRequestFilter xRequestIDFilter = (requestContext) -> {};

    private SABaseURL saBaseURL = null;

    public SAExecuter(SABaseURL saBaseURL) {
        this.saBaseURL = saBaseURL;
        this.idpApiTimeout = Integer.parseInt(Optional.ofNullable(System.getProperty("rest.api.timeout")).orElse(TEN_SECONDS) );
    }

    public SAExecuter(SABaseURL saBaseURL, GUIDStrategy guidStrategy) {
        this( saBaseURL );
        this.xRequestIDFilter = new XRequestIDFilter( guidStrategy );
    }

    public void setTimeout(int timeoutInMillis) {
        if( timeoutInMillis < 0 ) {
            throw new IllegalArgumentException( "Timeout must be a positive integer value." );
        }
        this.idpApiTimeout = timeoutInMillis;
    }

    //Set up our Connection
    private void createConnection() throws SARestAPIException {
        ClientConfig config = new ClientConfig();
        try {
            SSLContext ctx = null;
            ctx = SSLContext.getInstance( TLS );
            ctx.init(null, SATrustManagerFactory.createTrustsManagersFor( this.saBaseURL ) , new SecureRandom());
            config.register(SACheckRequestFilter.class);
            config.register( this.xRequestIDFilter );
            client = ClientBuilder.newBuilder()
                    .withConfig(config)
                    .sslContext(ctx)
                    .hostnameVerifier( (s, sslSession) -> saBaseURL.isSelfSigned() )
                    .build();
            client.property( ClientProperties.CONNECT_TIMEOUT, this.idpApiTimeout );
            client.property( ClientProperties.READ_TIMEOUT, this.idpApiTimeout );
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            logger.error("Exception occurred while attempting to associating our SSL cert to the session: " + e.getMessage());
            throw new SARestAPIException("Unable to create connection object, creation attempt returned NULL.", e);
        }
    }

    //Get Factors for the user requested
    public <T> T executeGetRequest(String auth, String query, String ts, Class<T> valueType) throws SARestAPIException {
        return executeGetRequest(auth, query, "", ts, valueType);
    }

    public <T> T executeGetRequestStateful(String auth, Cookie ingressCookie, String query, String ts, Class<T> valueType) throws SARestAPIException {
        try {
            if (client == null) {
                createConnection();
            }
            WebTarget target = client.target( query );
            Response response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    cookie( ingressCookie ).
                    get();
            T genericResponse = response.readEntity(valueType);
            response.close();
            return genericResponse;
        } catch (Exception e) {
            throw new SARestAPIException("Exception Get Request: \nQuery:\n\t" + query, e);
        }
    }

    public <T> T executeGetRequest(String auth, String query, String userId, String ts, Class<T> valueType) throws SARestAPIException {

        WebTarget target = null;
        Response response = null;
        try {
            if (client == null) {
                createConnection();
            }

            if (!userId.isBlank()) {
                target = encodeQueryUser(query, userId);
            }
            else{
                target = client.target(query);
            }
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    get();
            //consider using response.ok(valueType).build(); instead.
            return response.readEntity(valueType);
        } catch (SARestAPIException e) {
            throw new SARestAPIException("Exception Get Request: \nQuery:\n\t" + query, e);
        }finally {
            response.close();
        }
    }

    private String encodedValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

    public <T> T executeGetRequest(SAAuth saAuth, String baseUrl, String query, String ts, Class<T> valueType) throws SARestAPIException {

        String header = RestApiHeader.getAuthorizationHeader(saAuth, Resource.METHOD_GET, query, ts);

        return executeGetRequest(header, baseUrl + query, ts, valueType);
    }

    // post request
    public <T> T executePostRequest(String auth,String query, AuthRequest authRequest,String ts, Class<T> valueType)throws SARestAPIException {

        Response response = null;
        try {
            if (client == null) {
                createConnection();
            }
            WebTarget target = null;

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));

            return response.readEntity(valueType);
        } catch (Exception e) {
            throw new SARestAPIException("Exception Delivering OTP by Push: \nQuery:\n\t" +
                    query + "\nError:" + e.getMessage(), e);
        }finally {
            response.close();
        }
    }

    public <T extends StatefulResponseObject> T executePostRequestStateful(String auth,String query, AuthRequest authRequest,String ts, Class<T> valueType)throws SARestAPIException {
        Response response = null;
        try {
            if (client == null) {
                createConnection();
            }
            WebTarget target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));
            T responseObject = response.readEntity(valueType);
            responseObject.setSessionAffinityCookie(
                    // return a null-empty cookie when the session affinity cookie is not found.
                    response.getCookies().getOrDefault(SESSION_AFFINITY_COOKIE_NAME, new NewCookie(SESSION_AFFINITY_COOKIE_NAME, "" ) )
            );
            return responseObject;
        } catch (Exception e) {
            throw new SARestAPIException("Exception Delivering Push Notifiation: \nQuery:\n\t" +
                    query + "\nError:" + e.getMessage(), e);
        } finally {
            response.close();
        }
    }


    public <T> T executePutRequest(String auth, String query, Object payloadRequest, Class<T> responseValueType, String ts)throws SARestAPIException {
        return executePutRequest(auth, query, "", payloadRequest, responseValueType, ts);
    }

    public <T> T executePutRequest(String auth, String query, String userId, Object payloadRequest, Class<T> responseValueType, String ts)throws SARestAPIException {

        WebTarget target = null;
        Response response = null;
        try{
            if(client == null) {
                createConnection();
            }

            if (!userId.isBlank()) {
                target = encodeQueryUser(query, userId);
            }
            else{
                target = client.target(query);
            }
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    put(Entity.entity(JSONUtil.convertObjectToJSON(payloadRequest),MediaType.APPLICATION_JSON));
            //consider using response.ok(valueType).build(); instead.
            return response.readEntity(responseValueType);
        }catch(SARestAPIException e){
            throw new SARestAPIException("Exception Put Request: \nQuery:\n\t" + query + "\n", e);
        }finally {
            response.close();
        }
    }

    public <T> T executePostRawRequest(String auth,String query, Object authRequest, Class<T> valueType, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, "", "", authRequest, valueType, ts);
    }

    public <T> T executePostRawRequest(String auth,String query, String userId, String groupId, Object authRequest, Class<T> valueType, String ts) throws SARestAPIException{
        Response response = null;
        try{
            if(client == null) {
                createConnection();
            }
            WebTarget target = null;
            if (!userId.isBlank()) {
                target = encodeQueryUser(query, userId, groupId);
            }
            else{
                target = client.target(query);
            }
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest),MediaType.APPLICATION_JSON));
            return response.readEntity(valueType);
        }catch(SARestAPIException e){
            throw new SARestAPIException("Exception Post Request: \nQuery:\n\t" + query, e);
        }finally {
            response.close();
        }
    }

    public <T> T executeGenericRawRequest(String auth,String query, String ts, String method, Object authRequest, Class<T> valueType)throws SARestAPIException {
        Response response = null;
        try {
            if (client == null) {
                createConnection();
            }
            if(Resource.METHOD_DELETE.equals(method)){
                client.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
            }
            WebTarget target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    build(method, Entity.entity(JSONUtil.convertObjectToJSON(authRequest),MediaType.APPLICATION_JSON))
                    .invoke();

            return response.readEntity(valueType);
        } catch (Exception e) {
            throw new SARestAPIException("Exception Request: \nQuery:\n\t" + query + "\nError:" + e.getMessage(), e);
        } finally {
            if(Resource.METHOD_DELETE.equals(method)){
                client.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, false);
            }
            response.close();
        }
    }

    public <T> T executeDeleteRawRequest(String auth,String query, String ts, Object authRequest, Class<T> valueType)throws SARestAPIException{
        return executeGenericRawRequest(auth, query, ts, Resource.METHOD_DELETE, authRequest, valueType);
    }

    public <T> T executePostRawRequestWithoutPayload(String auth,String query, Class<T> valueType, String ts)throws SARestAPIException {
        return executePostRawRequestWithoutPayload(auth, query, "", "",valueType, ts);
    }

    public <T> T executePostRawRequestWithoutPayload(String auth,String query, String userId,String groupId, Class<T> valueType, String ts) throws SARestAPIException{
        WebTarget target = null;
        Response response = null;
        try{
            if(client == null) {
                createConnection();
            }

            if (!userId.isBlank()) {
                target = encodeQueryUser(query, userId, groupId);
            }
            else{
                target = client.target(query);
            }
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity("",MediaType.APPLICATION_JSON));
            return response.readEntity(valueType);
        }catch(SARestAPIException e){
            throw new SARestAPIException("Exception Post Request: \nQuery:\n\t" + query, e);
        }finally {
            response.close();
        }
    }

    public String executeRawGetRequest(String auth, String query, String ts) throws SARestAPIException {
        return executeRawGetRequest(auth, query, "", ts);
    }

    public String executeRawGetRequest(String auth, String query, String userId, String ts) throws SARestAPIException {

        WebTarget target = null;
        Response response = null;
        try{
            if(client == null) {
                createConnection();
            }
            if (!userId.isBlank()) {
                target = encodeQueryUser(query, userId);
            }
            else{
                target = client.target(query);
            }
            response = target.request().
            		accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    get(Response.class);
            return response.readEntity(String.class);
        }catch(Exception e){
            throw new SARestAPIException("Exception getting User Factors: \nQuery:\n\t" + query, e);
        }finally {
            response.close();
        }
    }

    //Validate User against Repository
    public BaseResponse executeValidateUser(String header,String query, AuthRequest authRequest,String ts)throws SARestAPIException{
        return executePostRawRequest(header, query, authRequest, BaseResponse.class, ts);
    }

    //Validate Users Password
    public BaseResponse executeValidateUserPassword(String auth,String query, AuthRequest authRequest,String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, BaseResponse.class, ts);
    }

    //Validate Users Pin
    public BaseResponse executeValidateUserPin(String auth,String query, AuthRequest authRequest,String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, BaseResponse.class, ts);
    }

    //Validate Users KBA
    public BaseResponse executeValidateKba(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, BaseResponse.class, ts);
    }

    //Validate User Oath Token
    public BaseResponse executeValidateOath(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, BaseResponse.class, ts);
    }

    //Validate OTP By Phone
    public ResponseObject executeOTPByPhone(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, ResponseObject.class, ts);
    }

    //Validate User OATH by SMS
    public ResponseObject executeOTPBySMS(String auth, String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, ResponseObject.class, ts);
    }

    //Validate User OTP by Email
    public ResponseObject executeOTPByEmail(String auth,String query, AuthRequest authRequest,String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, ResponseObject.class, ts);
    }

    //VALIDATE OTP by using IDP EndPoint
    public ValidateOTPResponse executeValidateOTP(String auth, String query, ValidateOTPRequest validateOTPRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, validateOTPRequest, ValidateOTPResponse.class, ts);
    }

    //Validate User Token by Help Desk Call
    public ResponseObject executeOTPByHelpDesk(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, authRequest, ResponseObject.class, ts);
    }

    //Run IP Evaluation against user and IP Address
    public IPEval executeIPEval(String auth, String query, IPEvalRequest ipEvalRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, ipEvalRequest, IPEval.class, ts);
    }

    //Run AccessHistories Post
    public ResponseObject executeAccessHistory(String auth, String query, AccessHistoryRequest accessHistoryRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, accessHistoryRequest, ResponseObject.class, ts);
    }

    // Run DFP Validate
    public DFPValidateResponse executeDFPValidate(String auth, String query, DFPValidateRequest dfpValidateRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, dfpValidateRequest, DFPValidateResponse.class, ts);
    }

    // Run DFP Confirm
    public DFPConfirmResponse executeDFPConfirm(String auth, String query, DFPConfirmRequest dfpConfirmRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, dfpConfirmRequest, DFPConfirmResponse.class, ts);
    }

    //Get JavaScript Source for DFP and Behavioral
    public <T> T executeGetJSObject(String auth, String query,String ts, Class<T> valueType)throws Exception {
        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T jsObjectResponse =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    get();
            jsObjectResponse = response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception getting JS Object SRC: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString());
            logger.trace("Detailed trace: ", e);
        }

        return jsObjectResponse;

    }

    //Run BehaveBio Post
    public BehaveBioResponse executeBehaveBioPost(String auth, String query, BehaveBioRequest behaveBioRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, behaveBioRequest, BehaveBioResponse.class, ts);
    }

    //Run BehaveBio Put
    public ResponseObject executeBehaveBioReset(String auth, String query, BehaveBioResetRequest behaveBioResetRequest, String ts)throws SARestAPIException{
        return executePutRequest(auth, query, behaveBioResetRequest, ResponseObject.class, ts);
    }

    // Run Password Reset (Admin level reset).
    public ResponseObject executeUserPasswordReset(String auth, String query, UserPasswordRequest userPasswordRequest, String ts)throws SARestAPIException{
        return executeUserPasswordReset(auth, query, "", userPasswordRequest, ts);
    }
    // Fill userId string when you want to encode and send userId through Query Params.
    public ResponseObject executeUserPasswordReset(String auth, String query, String userId, UserPasswordRequest userPasswordRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, userId, "", userPasswordRequest, ResponseObject.class,  ts);
    }

    // Run Change Password (Self Service).
    public ResponseObject executeUserPasswordChange(String auth, String query, UserPasswordRequest userPasswordRequest, String ts)throws SARestAPIException{
        return executeUserPasswordChange(auth, query, "", userPasswordRequest, ts);
    }

    // Fill userId string when you want to encode and send userId through Query Params.
    public ResponseObject executeUserPasswordChange(String auth, String query, String userId, UserPasswordRequest userPasswordRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, userPasswordRequest, ResponseObject.class, ts);
    }

    //Update User Profile
    public <T> T executeUserProfileUpdateRequest(String auth, String query,NewUserProfile userProfile, String ts, Class<T> valueType)throws SARestAPIException{
        return executeUserProfileUpdateRequest(auth, query, "", userProfile, ts, valueType);
    }

    public <T> T executeUserProfileUpdateRequest(String auth, String query, String userId, NewUserProfile userProfile, String ts, Class<T> valueType)throws SARestAPIException{
        return executePutRequest(auth, query, userId, userProfile, valueType, ts);
    }

    //create User Profile
    public <T> T executeUserProfileCreateRequest(String auth, String query, NewUserProfile newUserProfile, String ts, Class<T> valueType)throws SARestAPIException{
        return executePostRawRequest(auth, query, newUserProfile, valueType, ts);
    }

    //Single User to Single Group
    public <T> T executeSingleUserToSingleGroup(String auth, String query, String ts, Class<T> valueType)throws SARestAPIException {
        return executePostRawRequestWithoutPayload(auth, query, valueType, ts);
    }

    //Single User to Single Group
    public <T> T executeSingleUserToSingleGroup(String auth, String query, String userId, String groupId, String ts, Class<T> valueType)throws SARestAPIException {
        return executePostRawRequestWithoutPayload(auth, query, userId, groupId, valueType, ts);
    }

    //Single Group Multiple Users
    public <T> T executeGroupToUsersRequest(String auth, String query, UsersToGroup usersToGroup, String ts, Class<T> valueType)throws SARestAPIException{
        return executePostRawRequest(auth, query, usersToGroup, valueType, ts);

    }

    //Single Group to Single User
    public <T> T executeSingleGroupToSingleUser(String auth, String query, String ts, Class<T> valueType)throws SARestAPIException {
        return executeSingleGroupToSingleUser(auth, query, "", "", ts, valueType);

    }

    //Single Group to Single User
    public <T> T executeSingleGroupToSingleUser(String auth, String query, String userId, String groupId, String ts, Class<T> valueType)throws SARestAPIException {
        return executePostRawRequestWithoutPayload(auth, query, userId, groupId, valueType, ts);

    }

    //Single User to Multiple Groups
    public <T> T executeUserToGroupsRequest(String auth, String query, UserToGroups userToGroups, String ts, Class<T> valueType)throws SARestAPIException{
        return executePostRawRequest(auth, query, userToGroups, valueType, ts);
    }

    //Run NumberProfile Post
    public NumberProfileResponse executeNumberProfilePost(String auth, String query, NumberProfileRequest numberProfileRequest, String ts)throws SARestAPIException{
        return executePostRawRequest(auth, query, numberProfileRequest, NumberProfileResponse.class, ts);
    }

    //Run Number Profile Put
    public BaseResponse executeNumberProfileUpdate(String auth, String query, NumberProfileUpdateRequest numberProfileUpdateRequest, String ts)throws SARestAPIException{
        return executePutRequest(auth, query, numberProfileUpdateRequest, ResponseObject.class, ts);
    }

    // Helper function for encoding users with special characters
    private WebTarget encodeQueryUser (String query, String userId) throws SARestAPIException{
        return encodeQueryUser(query, userId,"");
    }

    private WebTarget encodeQueryUser (String query, String userId, String groupId) throws SARestAPIException{

        URI uri = URI.create(query);
        WebTarget target = client.target(uri);
        String encodedUser = null;
        try {
            encodedUser = encodedValue(userId);
            if (!groupId.isBlank()) {
                target.queryParam("groups", encodedValue(groupId));
            }
            return target.queryParam("username", encodedUser);
        } catch (UnsupportedEncodingException e) {
            throw new SARestAPIException(e);
        }
    }

}