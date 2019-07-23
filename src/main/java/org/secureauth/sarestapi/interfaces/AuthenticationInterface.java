package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.Response.ValidateOTPResponse;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public interface AuthenticationInterface {

    BaseResponse validateUser(SAAccess saAccess, String userId);

    BaseResponse validatePassword(SAAccess saAccess, String userId, String password);

    FactorsResponse getFactorsForUser(SAAccess saAccess, String userId);

    BaseResponse validateOath(SAAccess saAccess, String userId, String otp, String factorId);

    ResponseObject validatePushOTP(SAAccess saAccess, String userId, String factorId);

    ResponseObject sendSmsOTP(SAAccess saAccess, String userId, String factorId);

    ResponseObject sendPhoneOTP(SAAccess saAccess, String userId, String factorId);

    ResponseObject sendHelpDeskOTP(SAAccess saAccess, String userid, String factorId);

    ResponseObject sendEmailOTP(SAAccess saAccess, String userId, String factorId);

    BaseResponse validateKBQ(SAAccess saAccess, String userId, String answer, String factorId);

    BaseResponse validatePin(SAAccess saAccess, String userId, String pin);

    ValidateOTPResponse validateOTP(SAAccess saAccess, String userId, String otp);


    void PushToAccept(SAAccess saAccess, String user, String factorID, String ipAddress) throws InterruptedException;

}
