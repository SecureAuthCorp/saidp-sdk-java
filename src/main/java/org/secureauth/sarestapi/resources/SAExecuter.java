package org.secureauth.sarestapi.resources;



import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

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
import org.secureauth.sarestapi.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Jersey 2 Libs
import javax.ws.rs.client.WebTarget;
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

public class SAExecuter extends SAGenericExecuter{

    private static Logger logger = LoggerFactory.getLogger(SAExecuter.class);

    public SAExecuter(SABaseURL saBaseURL){
        super(saBaseURL);
    }

    /**
     * Use executeGenericPost instead!
     * @param header to be send by the request
     * @param query url
     * @param requestType object type to be converted To JSON
     * @param ts timestamp
     * @param responseType class expected from the response
     * @param <T> BaseResponse, ResponseObject, etc
     * @return json from response
     * @throws SARestAPIException something wrong
     */
    @Deprecated
    public <T> T executePostRequest(String header,String query,Object requestType,String ts, Class<T> responseType) throws SARestAPIException {
        return executeGenericPostRequest(header, query, requestType, responseType, ts, "Exception Base Post");
    }

    public <T> T executeGenericPost(String header, String query, Object requestType, Class<T> responseType, String ts) throws SARestAPIException {
        return executeGenericPostRequest(header, query, requestType, responseType, ts, "Exception Base Post");
    }

    //Validate User against Repository
    public BaseResponse executeValidateUser(String header,String query, AuthRequest authRequest,String ts) throws SARestAPIException{
        return executeGenericPostRequest(header, query, authRequest, BaseResponse.class, ts, "Exception Validating User");
    }

    //Validate Users Password
    public BaseResponse executeValidateUserPassword(String auth, String query, AuthRequest authRequest, String ts) throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, BaseResponse.class, ts, "Exception Validating User Password");
    }

    //Validate Users Pin
    public BaseResponse executeValidateUserPin(String auth,String query, AuthRequest authRequest,String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, BaseResponse.class, ts, "Exception Validating User Password");
    }

    //Validate Users KBA
    public BaseResponse executeValidateKba(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, BaseResponse.class, ts, "Exception Validating KBA");
    }

    //Validate User Oath TokenSARestAPIException
    public BaseResponse executeValidateOath(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, BaseResponse.class, ts, "Exception Validating OATH");
    }

    //Validate OTP By Phone
    public ResponseObject executeOTPByPhone(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, ResponseObject.class, ts, "Exception Delivering OTP by Phone");
    }

    //Validate User OATH by SMS
    public ResponseObject executeOTPBySMS(String auth, String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, ResponseObject.class, ts, "Exception Delivering OTP by SMS");
    }

    //Validate User OTP by Email
    public ResponseObject executeOTPByEmail(String auth,String query, AuthRequest authRequest,String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, ResponseObject.class, ts, "Exception Delivering OTP by Email");
    }

    //VALIDATE OTP by using IDP EndPoint
    public ValidateOTPResponse executeValidateOTP(String auth, String query, ValidateOTPRequest validateOTPRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, validateOTPRequest, ValidateOTPResponse.class, ts, "Exception Running Validate OTP POST");
    }

    //Validate User Token by Help Desk Call
    public ResponseObject executeOTPByHelpDesk(String auth,String query, AuthRequest authRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, authRequest, ResponseObject.class, ts, "Exception Delivering OTP by HelpDesk");
    }

    //Run IP Evaluation against user and IP Address
    public IPEval executeIPEval(String auth, String query, IPEvalRequest ipEvalRequest, String ts) throws SARestAPIException {
        return executeGenericPostRequest(auth, query, ipEvalRequest, IPEval.class, ts, "Exception Running IP Evaluation");
    }

    //Run AccessHistories Post
    public ResponseObject executeAccessHistory(String auth, String query, AccessHistoryRequest accessHistoryRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, accessHistoryRequest, ResponseObject.class, ts, "Exception Running Access History POST");
    }

    // Run DFP Validate
    public DFPValidateResponse executeDFPValidate(String auth, String query, DFPValidateRequest dfpValidateRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, dfpValidateRequest, DFPValidateResponse.class, ts, "Exception Running Access History POST");
    }

    // Run DFP Confirm
    public DFPConfirmResponse executeDFPConfirm(String auth, String query, DFPConfirmRequest dfpConfirmRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, dfpConfirmRequest, DFPConfirmResponse.class, ts, "Exception Running Access History POST");
    }

    //Get JavaScript Source for DFP and Behavioral
    public <T> T executeGetJSObject(String auth, String query,String ts,  Class<T> valueType)throws SARestAPIException {
        return executeGenericGetRequest(auth, query, ts, valueType, "Exception getting JS Object SRC");
    }

    //Run BehaveBio Post
    public BehaveBioResponse executeBehaveBioPost(String auth, String query, BehaveBioRequest behaveBioRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, behaveBioRequest, BehaveBioResponse.class, ts, "Exception Running BehaveBio POST");
    }

    //Run BehaveBio Put
    public ResponseObject executeBehaveBioReset(String auth, String query, BehaveBioResetRequest behaveBioResetRequest, String ts)throws SARestAPIException{
        return executeGenericPutRequest(auth, query, behaveBioResetRequest, ResponseObject.class, ts, "Exception Running BehaveBio PUT");
    }

    //Run Password Reset (Admin level reset)
    public ResponseObject executeUserPasswordReset(String auth, String query, UserPasswordRequest userPasswordRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, userPasswordRequest, ResponseObject.class, ts, "Exception Running Password Reset POST");
    }

    //Run Change Password (Self Service)
    public ResponseObject executeUserPasswordChange(String auth, String query, UserPasswordRequest userPasswordRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, userPasswordRequest, ResponseObject.class, ts, "Exception Running Password Reset POST");
    }

    //Run NumberProfile Post
    public NumberProfileResponse executeNumberProfilePost(String auth, String query, NumberProfileRequest numberProfileRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, numberProfileRequest, NumberProfileResponse.class, ts, "Exception Running NumberProfile POST");
    }

    //Run Number Profile Put
    public BaseResponse executeNumberProfileUpdate(String auth, String query, NumberProfileUpdateRequest numberProfileUpdateRequest, String ts)throws SARestAPIException{
        return executeGenericPostRequest(auth, query, numberProfileUpdateRequest, NumberProfileResponse.class, ts, "Exception Running NumberProfile POST");
    }

}
