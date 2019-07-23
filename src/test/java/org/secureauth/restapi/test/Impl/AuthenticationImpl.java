package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.PushAcceptStatus;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.Response.ValidateOTPResponse;
import org.secureauth.sarestapi.interfaces.AuthenticationInterface;
import org.secureauth.sarestapi.resources.s;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class AuthenticationImpl implements AuthenticationInterface {

    public AuthenticationImpl(){}

    @Override
    public BaseResponse validateUser(SAAccess saAccess, String userId) {
        //Validate the User Exists
        BaseResponse responseObject = saAccess.validateUser(userId);
        if (responseObject != null) {

            if (responseObject.getStatus().equalsIgnoreCase("found")) {
                System.out.println("We matched User");

                System.out.println(responseObject.toString());


            }
            if(responseObject.getStatus().equalsIgnoreCase(s.STATUS_NOT_FOUND)) {
                System.out.println(responseObject.toString());
            }

        }
        return responseObject;
    }

    @Override
    public BaseResponse validatePassword(SAAccess saAccess, String userid, String password) {
        BaseResponse passObject = saAccess.validateUserPassword(userid, password);
        if (passObject != null) {

            System.out.println("Password Test+++++++");
            System.out.println(passObject.toString());
            System.out.println("END Password Test+++++++");
        }
        return passObject;
    }

    @Override
    public FactorsResponse getFactorsForUser(SAAccess saAccess, String userid) {
        //Return Factors
        FactorsResponse factorsResponse = saAccess.factorsByUser(userid);
        System.out.println(factorsResponse.getMessage());
        if (!factorsResponse.getStatus().equalsIgnoreCase("invalid")) {
            System.out.println("FACTORS +++++++++++++++++\n" + factorsResponse.toString());
            System.out.println("END FACTORS++++++++++++++");
        }
        return factorsResponse;
    }

    @Override
    public BaseResponse validateOath(SAAccess saAccess, String user, String otp, String factorId) {
        BaseResponse responseObject = saAccess.validateOath(user, otp, factorId);
        System.out.println("Start OATH Test+++++++++++++++");
        System.out.println(responseObject.toString());
        System.out.println("End OATH Test+++++++++++++++");
        return responseObject;
    }

    @Override
    public ResponseObject validatePushOTP(SAAccess saAccess, String user, String factorId) {
        ResponseObject responseObject = saAccess.deliverOTPByPush(user, factorId);
        System.out.println("Start Push Notification +++++++++++++++++");
        System.out.println(responseObject.toString());
        System.out.println("End Push Notification +++++++++++++++++");
        return responseObject;
    }

    @Override
    public ResponseObject sendSmsOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject smsOTP = saAccess.deliverOTPBySMS(userid, factorId);
        System.out.println("SMS OTP is: " + smsOTP.getOtp());
        return smsOTP;
    }

    @Override
    public ResponseObject sendPhoneOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject phoneOTP = saAccess.deliverOTPByPhone(userid, factorId);
        System.out.println("PHONE OTP is: " + phoneOTP.getOtp());
        return phoneOTP;
    }

    @Override
    public ResponseObject sendHelpDeskOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject helpDeskOTP = saAccess.deliverOTPByHelpDesk(userid, factorId);
        System.out.println("Help Desk OTP is: " + helpDeskOTP.getOtp());
        return helpDeskOTP;
    }

    @Override
    public ResponseObject sendEmailOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject emailOTP = saAccess.deliverOTPByEmail(userid, factorId);
        System.out.println("Email OTP is: " + emailOTP.getOtp());
        return emailOTP;
    }

    @Override
    public BaseResponse validateKBQ(SAAccess saAccess, String userid, String answer, String factorId) {
        System.out.println("TEST KBQ:  " + factorId);
        BaseResponse kba = saAccess.validateKba(userid, answer, factorId);
        System.out.println(kba.toString());
        System.out.println("END KBQ +++++++++++");

        return kba;
    }

    @Override
    public BaseResponse validatePin(SAAccess saAccess, String userId, String pin) {
        System.out.println("TEST Pin Validation  " );
        BaseResponse pinResponse = saAccess.validateUserPin(userId, pin);
        System.out.println(pinResponse.toString());
        System.out.println("END PIN +++++++++++");

        return pinResponse;
    }


    @Override
    public void PushToAccept(SAAccess saAccess, String user, String factorID, String ipAddress) throws InterruptedException {
        System.out.println("Start Push 2 Accept Test ++++++++++++++++++");
        ResponseObject ro = saAccess.sendPushToAcceptReq(user, factorID, ipAddress, null, null);
        System.out.println(ro);
        String refId = ro.getReference_id();
        PushAcceptStatus status;
        do {
            Thread.sleep(2000);
            status = saAccess.queryPushAcceptStatus(refId);
            System.out.println(status);
        } while ("PENDING".equals(status.getMessage()));
        //break;
        System.out.println("END Push 2 Accept Test+++++++++++++++++++");
    }

    @Override
    public ValidateOTPResponse validateOTP(SAAccess saAccess, String user, String otp) {
        System.out.println("Start Validate OTP EndPoint");
        ValidateOTPResponse validateOTPResponse = saAccess.validateOTP(user,otp);
        System.out.println(validateOTPResponse.toString());
        return validateOTPResponse;
    }
}
