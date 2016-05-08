package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public interface AuthenticationInterface {

    ResponseObject validateUser(SAAccess saAccess, String userId);

    ResponseObject validatePassword(SAAccess saAccess, String userId, String password);

    FactorsResponse getFactorsForUser(SAAccess saAccess, String userId);

    ResponseObject validateOath(SAAccess saAccess,String userId, String otp, String factorId);

    ResponseObject validatePushOTP(SAAccess saAccess,String userId, String factorId);

    ResponseObject sendSmsOTP(SAAccess saAccess, String userId, String factorId);

    ResponseObject sendPhoneOTP(SAAccess saAccess, String userId, String factorId);

    ResponseObject sendEmailOTP(SAAccess saAccess, String userId, String factorId);

    ResponseObject validateKBQ(SAAccess saAccess, String userId, String answer, String factorId);


    void PushToAccept(SAAccess saAccess, String user, String factorID, String ipAddress) throws InterruptedException;

}
