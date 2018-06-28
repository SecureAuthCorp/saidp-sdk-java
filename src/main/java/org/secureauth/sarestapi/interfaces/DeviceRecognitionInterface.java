package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.DFPConfirmResponse;
import org.secureauth.sarestapi.data.Response.DFPValidateResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;

/**
 * Created by rrowcliffe on 5/8/16.
 */
public interface DeviceRecognitionInterface {

    JSObjectResponse getDFPJavaScriptSrc(SAAccess saAccess);

    DFPConfirmResponse confirmDeviceRecognition(SAAccess saAccess, String userid, String fingerprint_id);

    DFPValidateResponse validateNewDevice(SAAccess saAccess, String userid, String host_address, String jsonString);
}
