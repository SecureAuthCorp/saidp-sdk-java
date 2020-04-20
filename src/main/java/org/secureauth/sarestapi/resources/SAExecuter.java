package org.secureauth.sarestapi.resources;



import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.secureauth.sarestapi.data.*;
import org.secureauth.sarestapi.data.BehavioralBio.BehaveBioRequest;
import org.secureauth.sarestapi.data.Requests.BehaveBioResetRequest;
import org.secureauth.sarestapi.data.Response.*;
import org.secureauth.sarestapi.data.Requests.*;
import org.secureauth.sarestapi.data.Requests.UserPasswordRequest;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfile;

import org.secureauth.sarestapi.data.UserProfile.UserToGroups;
import org.secureauth.sarestapi.data.UserProfile.UsersToGroup;
import org.secureauth.sarestapi.filters.SACheckRequestFilter;
import org.secureauth.sarestapi.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Jersey 2 Libs
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientConfig;
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

    private ClientConfig config = null;

    private Client client = null;
    private static Logger logger = LoggerFactory.getLogger(SAExecuter.class);
    private static final String TEN_SECONDS = "10000";

    private SABaseURL saBaseURL = null;

    public SAExecuter(SABaseURL saBaseURL) {
        this.saBaseURL = saBaseURL;
    }

    //Set up our Connection
    private void createConnection() throws Exception {

        config = new ClientConfig();
        SSLContext ctx = null;
        ctx = SSLContext.getInstance("TLS");


        TrustManager[] certs = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }
                }
        };

        ctx.init(null, certs, new SecureRandom());

        try {

            config.register(SACheckRequestFilter.class);
            client = ClientBuilder.newBuilder()
                    .withConfig(config)
                    .sslContext(ctx)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return saBaseURL.isSelfSigned();
                        }
                    })
                    .build();
            int timeoutSeconds = Integer.parseInt(Optional.ofNullable(System.getProperty("rest.api.timeout")).orElse(TEN_SECONDS));
            client.property(ClientProperties.CONNECT_TIMEOUT, timeoutSeconds);
            client.property(ClientProperties.READ_TIMEOUT, timeoutSeconds);
        } catch (Exception e) {
            logger.error(new StringBuilder().append("Exception occurred while attempting to associating our SSL cert to the session.").toString(), e);
        }

        if (client == null)
            throw new Exception(new StringBuilder().append("Unable to create connection object, creation attempt returned NULL.").toString());
    }

    //Get Factors for the user requested
    public <T> T executeGetRequest(String auth, String query, String ts, Class<T> valueType) throws Exception {
        if (client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T genericResponse = null;
        try {

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    get();
            //consider using response.ok(valueType).build(); instead.
            genericResponse = response.readEntity(valueType);
            response.close();
        } catch (Exception e) {
            logger.error("Exception Get Request: \nQuery:\n\t" + query + "\nError:" + e.getMessage());
        }

        return genericResponse;

    }

    public <T> T executePutRequest(String auth, String query, Object payloadRequest, Class<T> valueType, String ts)throws Exception {
        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T genericResponse =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    put(Entity.entity(JSONUtil.convertObjectToJSON(payloadRequest),MediaType.APPLICATION_JSON));
            //consider using response.ok(valueType).build(); instead.
            genericResponse = response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error("Exception Put  Request: \nQuery:\n\t" + query + "\nError:" + e.getMessage());
        }

        return genericResponse;

    }

    public String executeRawGetRequest(String auth, String query,String ts)throws Exception {
        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        String factors=null;
        try{

            target = client.target(query);
            response = target.request().
            		accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    get(Response.class);
            return response.readEntity(String.class);
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception getting User Factors: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).append(".\nResponse code is ").append(response).toString()
                    + "; Raw response:" + factors, e);
        }
        return null;
    }

    //Validate User against Repository
    public BaseResponse executeValidateUser(String header,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }


        WebTarget target = null;
        Response response = null;

        BaseResponse responseObject =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", header).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest),MediaType.APPLICATION_JSON));

            responseObject = response.readEntity(BaseResponse.class);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Validating User: \nQuery:\n\t")
                    .append(query).append("\nError: \n\t").toString(), e);
        }

        return responseObject;

    }

    //Validate Users Password
    public BaseResponse executeValidateUserPassword(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        BaseResponse responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));
            try{
                responseObject=response.readEntity(BaseResponse.class);
            }catch (MessageBodyProviderNotFoundException e){
                logger.error("BAD status ("+response.getStatus() +") answer from API IdP. Please check: " + query );
            }
            response.close();
        }catch(Exception e){
            logger.error("Exception Validating User Password: \nQuery:\n\t" +
                    query + "\nError:" + e.getMessage(), e);
        }

        return responseObject;

    }

    //Validate Users Pin
    public BaseResponse executeValidateUserPin(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        BaseResponse responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));
            responseObject=response.readEntity(BaseResponse.class);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Validating User Password: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Validate Users KBA
    public BaseResponse executeValidateKba(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        BaseResponse responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(BaseResponse.class);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Validating KBA: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Validate User Oath Token
    public BaseResponse executeValidateOath(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        BaseResponse responseObject =null;
        try{

            target = client.target(query);
            response = target.request()
                    .accept(MediaType.APPLICATION_JSON).
                            header("Authorization", auth).
                            header("X-SA-Ext-Date", ts).
                            post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(BaseResponse.class);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Validating OATH: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Validate OTP By Phone
    public ResponseObject executeOTPByPhone(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    //type(MediaType.APPLICATION_JSON).
                            header("Authorization", auth).
                            header("X-SA-Ext-Date", ts).
                            post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Delivering OTP by Phone: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Validate User OATH by SMS
    public ResponseObject executeOTPBySMS(String auth, String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject responseObject =null;
        try{

            target = client.target(query);
            response = target.request()
                    .accept(MediaType.APPLICATION_JSON).
                            header("Authorization", auth).
                            header("X-SA-Ext-Date", ts).
                            post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(ResponseObject.class);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Delivering OTP by SMS: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Validate User OTP by Email
    public ResponseObject executeOTPByEmail(String auth,String query, AuthRequest authRequest,String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest), MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Delivering OTP by Email: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    // post request
    public <T> T executePostRequest(String auth,String query, AuthRequest authRequest,String ts, Class<T> valueType)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest),MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Delivering OTP by Push: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //VALIDATE OTP by using IDP EndPoint
    public ValidateOTPResponse executeValidateOTP(String auth, String query, ValidateOTPRequest validateOTPRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ValidateOTPResponse validateOTPResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(validateOTPRequest),MediaType.APPLICATION_JSON));

            validateOTPResponse = response.readEntity(ValidateOTPResponse.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running Validate OTP POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return validateOTPResponse;

    }

    //Validate User Token by Help Desk Call
    public ResponseObject executeOTPByHelpDesk(String auth,String query, AuthRequest authRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(authRequest),MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Delivering OTP by HelpDesk: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Run IP Evaluation against user and IP Address
    public IPEval executeIPEval(String auth, String query, IPEvalRequest ipEvalRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        String responseStr=null;
        IPEval ipEval =null;

        try{

            target = client.target(query);


            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(ipEvalRequest), MediaType.APPLICATION_JSON));

            ipEval = response.readEntity(IPEval.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running IP Evaluation: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return ipEval;

    }

    //Run AccessHistories Post
    public ResponseObject executeAccessHistory(String auth, String query, AccessHistoryRequest accessHistoryRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject accessHistory =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(accessHistoryRequest),MediaType.APPLICATION_JSON));

            accessHistory = response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running Access History POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return accessHistory;

    }
    // Run DFP Validate
    public DFPValidateResponse executeDFPValidate(String auth, String query, DFPValidateRequest dfpValidateRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        DFPValidateResponse dfpValidateResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(dfpValidateRequest),MediaType.APPLICATION_JSON));

            dfpValidateResponse = response.readEntity(DFPValidateResponse.class);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running Access History POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return dfpValidateResponse;

    }

    // Run DFP Confirm
    public DFPConfirmResponse executeDFPConfirm(String auth, String query, DFPConfirmRequest dfpConfirmRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        DFPConfirmResponse dfpConfirmResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(dfpConfirmRequest),MediaType.APPLICATION_JSON));


            dfpConfirmResponse = response.readEntity(DFPConfirmResponse.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running DFP Confirm POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return dfpConfirmResponse;

    }

    //Get JavaScript Source for DFP and Behavioral
    public <T> T executeGetJSObject(String auth, String query,String ts,  Class<T> valueType)throws Exception {
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
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return jsObjectResponse;

    }

    //Run BehaveBio Post
    public BehaveBioResponse executeBehaveBioPost(String auth, String query, BehaveBioRequest behaveBioRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        BehaveBioResponse behaveBioResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(behaveBioRequest),MediaType.APPLICATION_JSON));

            behaveBioResponse = response.readEntity(BehaveBioResponse.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running BehaveBio POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return behaveBioResponse;

    }

    //Run BehaveBio Put
    public ResponseObject executeBehaveBioReset(String auth, String query, BehaveBioResetRequest behaveBioResetRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject behaveBioResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    put(Entity.entity(JSONUtil.convertObjectToJSON(behaveBioResetRequest),MediaType.APPLICATION_JSON));

            behaveBioResponse = response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running BehaveBio POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return behaveBioResponse;

    }

    //Run Password Reset (Admin level reset)
    public ResponseObject executeUserPasswordReset(String auth, String query, UserPasswordRequest userPasswordRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject passwordResetResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(userPasswordRequest),MediaType.APPLICATION_JSON));

            passwordResetResponse = response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running Password Reset POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return passwordResetResponse;

    }

    //Run Change Password (Self Service)
    public ResponseObject executeUserPasswordChange(String auth, String query, UserPasswordRequest userPasswordRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        ResponseObject passwordChangeResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(userPasswordRequest),MediaType.APPLICATION_JSON));

            passwordChangeResponse = response.readEntity(ResponseObject.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running Password Reset POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return passwordChangeResponse;

    }

    //Update User Profile
    public <T> T executeUserProfileUpdateRequest(String auth, String query,NewUserProfile userProfile, String ts, Class<T> valueType)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    put(Entity.entity(JSONUtil.convertObjectToJSON(userProfile), MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Updating User Profile: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //create User Profile
    public <T> T executeUserProfileCreateRequest(String auth, String query, NewUserProfile newUserProfile, String ts, Class<T> valueType)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(newUserProfile),MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Creating User Profile: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Single User to Single Group
    public <T> T executeSingleUserToSingleGroup(String auth, String query,String ts,  Class<T> valueType)throws Exception {
        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T genericResponse =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity("",MediaType.APPLICATION_JSON));
            genericResponse = response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Adding user to Group: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return genericResponse;

    }

    //Single Group Multiple Users
    public <T> T executeGroupToUsersRequest(String auth, String query, UsersToGroup usersToGroup, String ts, Class<T> valueType)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(usersToGroup),MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Associating Users to Group: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Single Group to Single User
    public <T> T executeSingleGroupToSingleUser(String auth, String query,String ts,  Class<T> valueType)throws Exception {
        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T genericResponse =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity("",MediaType.APPLICATION_JSON));
            genericResponse = response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Adding Group to User: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return genericResponse;

    }

    //Signle User to Multiple Groups
    public <T> T executeUserToGroupsRequest(String auth, String query, UserToGroups userToGroups, String ts, Class<T> valueType)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        T responseObject =null;
        try{

            target = client.target(query);
            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(userToGroups),MediaType.APPLICATION_JSON));

            responseObject=response.readEntity(valueType);
            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Associating Users to Group: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return responseObject;

    }

    //Run NumberProfile Post
    public NumberProfileResponse executeNumberProfilePost(String auth, String query, NumberProfileRequest numberProfileRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        NumberProfileResponse numberProfileResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    post(Entity.entity(JSONUtil.convertObjectToJSON(numberProfileRequest),MediaType.APPLICATION_JSON));

            numberProfileResponse = response.readEntity(NumberProfileResponse.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running NumberProfile POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return numberProfileResponse;

    }

    //Run Number Profile Put
    public BaseResponse executeNumberProfileUpdate(String auth, String query, NumberProfileUpdateRequest numberProfileUpdateRequest, String ts)throws Exception{

        if(client == null) {
            createConnection();
        }

        WebTarget target = null;
        Response response = null;
        BaseResponse numberProfileUpdateResponse =null;
        try{
            target = client.target(query);

            response = target.request().
                    accept(MediaType.APPLICATION_JSON).
                    header("Authorization", auth).
                    header("X-SA-Ext-Date", ts).
                    put(Entity.entity(JSONUtil.convertObjectToJSON(numberProfileUpdateRequest),MediaType.APPLICATION_JSON));

            numberProfileUpdateResponse = response.readEntity(BaseResponse.class);

            response.close();
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception Running NumberProfile POST: \nQuery:\n\t")
                    .append(query).append("\nError:").append(e.getMessage()).toString(), e);
        }

        return numberProfileUpdateResponse;

    }


}
