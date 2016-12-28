package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;

import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.interfaces.AdHocInterface;
import org.secureauth.sarestapi.resources.s;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class AdHocImpl implements AdHocInterface {

    public AdHocImpl(){}

    @Override
    public ResponseObject sendAdHocSmsOTP(SAAccess saAccess, String userid, String phoneNumber) {
        ResponseObject smsOTP = saAccess.deliverAdHocOTPBySMS(userid, phoneNumber);
        System.out.println("SMS OTP is: " + smsOTP.getOtp());
        return smsOTP;
    }

    @Override
    public ResponseObject sendAdHocPhoneOTP(SAAccess saAccess, String userid, String phoneNumber) {
        ResponseObject phoneOTP = saAccess.deliverAdHocOTPByPhone(userid, phoneNumber);
        System.out.println("PHONE OTP is: " + phoneOTP.getOtp());
        return phoneOTP;
    }

    @Override
    public ResponseObject sendAdHocEmailOTP(SAAccess saAccess, String userid, String emailAddress) {
        ResponseObject emailOTP = saAccess.deliverAdHocOTPByEmail(userid, emailAddress);
        System.out.println("Email OTP is: " + emailOTP.getOtp());
        return emailOTP;
    }


}
