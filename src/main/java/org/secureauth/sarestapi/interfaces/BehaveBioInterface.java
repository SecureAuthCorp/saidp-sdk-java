package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.BehaveBioResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public interface BehaveBioInterface {

    JSObjectResponse getBehaveBioJavaScriptSrc(SAAccess saAccess);

    BehaveBioResponse submitBehaveBioProfile(SAAccess saAccess,String userid, String behaviorProfile, String hostAddress, String userAgent);

    ResponseObject resetBehaveBioProfile(SAAccess saAccess, String userid, String fieldName, String fieldType, String deviceType);

}
