package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public interface AdHocInterface {

    ResponseObject sendAdHocSmsOTP(SAAccess saAccess, String userId, String phoneNumber);

    ResponseObject sendAdHocPhoneOTP(SAAccess saAccess, String userId, String phoneNumber);

    ResponseObject sendAdHocEmailOTP(SAAccess saAccess, String userId, String emailAddress);

}
