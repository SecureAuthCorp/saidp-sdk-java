package org.secureauth.restapi.test;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.IPEval;
import org.secureauth.sarestapi.data.PushAcceptStatus;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.interfaces.AdaptiveAuthenticationInterface;
import org.secureauth.sarestapi.interfaces.AuthenticationInterface;
import org.secureauth.sarestapi.interfaces.JavascriptResource;
import org.secureauth.sarestapi.resources.s;
import org.secureauth.sarestapi.util.JSONUtil;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by rrowcliffe on 5/8/16.
 */
public class InterfaceTestImpl implements AuthenticationInterface, AdaptiveAuthenticationInterface, JavascriptResource {

    public InterfaceTestImpl(){}

    @Override
    public ResponseObject validateUser(SAAccess saAccess, String userId) {
        //Validate the User Exists
        ResponseObject responseObject = saAccess.validateUser(userId);
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
    public ResponseObject validatePassword(SAAccess saAccess, String userid, String password) {
        ResponseObject passObject = saAccess.validateUserPassword(userid, password);
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
    public ResponseObject validateOath(SAAccess saAccess,String user, String otp, String factorId) {
        ResponseObject responseObject = saAccess.validateOath(user, otp, factorId);
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
    public ResponseObject sendEmailOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject emailOTP = saAccess.deliverOTPByEmail(userid, factorId);
        System.out.println("Email OTP is: " + emailOTP.getOtp());
        return emailOTP;
    }

    @Override
    public ResponseObject validateKBQ(SAAccess saAccess, String userid, String answer, String factorId) {
        System.out.println("TEST KBQ:  " + factorId);
        ResponseObject kba = saAccess.validateKba(userid, answer, factorId);
        System.out.println(kba.toString());
        System.out.println("END KBQ +++++++++++");

        return kba;
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
    public IPEval ipThreatCheck(SAAccess saAccess, String userid, String ip_address) {
        IPEval ipEval = saAccess.iPEvaluation(userid, ip_address);
        System.out.println("Start IPEvaluation  +++++++++++++++++");
        if (ipEval != null) {
            System.out.println(ipEval.toString());
        } else {
            System.out.println("If you See this then Something went Wrong!!");
        }
        System.out.println("End IPEvaluation  +++++++++++++++++");
        return ipEval;
    }

    @Override
    public ResponseObject updateAccessHistories(SAAccess saAccess, String user, String ip_address) {
        System.out.println("START Access Histories test +++++++++");
        ResponseObject ahistories = saAccess.accessHistory(user, ip_address);
        System.out.println(ahistories.toString());
        System.out.println("END Access Histories test +++++++++++");
        return ahistories;
    }

    @Override
    public ResponseObject submitAdaptiveAuth(SAAccess saAccess, String user, String ipAddress) {
        System.out.println("Start Adaptive Test ++++++++++++++++");
        ResponseObject responseObject = saAccess.adaptiveAuthQuery(user,ipAddress);
        System.out.println(responseObject.toString());
        System.out.println("End Adaptive Test ++++++++++++++++++");
        return responseObject;
    }

    @Override
    public JSObjectResponse getDFPJavaScriptSrc(SAAccess saAccess) {
        System.out.println("START JavaScript Get URL Request ++++++++");
        JSObjectResponse jsObject = saAccess.javaScriptSrc();
        if(jsObject != null){
            System.out.println(jsObject.getSrc());
        }else{
            System.out.println("Effed");
        }

        System.out.println("END JavaScript Get URL Request ++++++++");
        return jsObject;
    }
}
