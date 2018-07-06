package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.DFPConfirmResponse;
import org.secureauth.sarestapi.data.Response.DFPValidateResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;
import org.secureauth.sarestapi.interfaces.DeviceRecognitionInterface;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class DeviceRecognitionImpl implements DeviceRecognitionInterface {

    public DeviceRecognitionImpl(){}

    @Override
    public JSObjectResponse getDFPJavaScriptSrc(SAAccess saAccess) {
        System.out.println("START JavaScript Get URL Request ++++++++");
        JSObjectResponse jsObject = saAccess.javaScriptSrc();
        if(jsObject != null){
            System.out.println(jsObject.getSrc());
        }else{
            System.out.println("Null Object Response");
        }

        System.out.println("END JavaScript Get URL Request ++++++++");
        return jsObject;
    }

    @Override
    public DFPConfirmResponse confirmDeviceRecognition(SAAccess saAccess, String userid, String fingerprint_id) {
        System.out.println("START Confirm DeviceRecognition " + userid + " Request ++++++++");
        DFPConfirmResponse dfpConfirmResponse = saAccess.DFPConfirm(userid,fingerprint_id);
        if(dfpConfirmResponse != null){
            System.out.println(dfpConfirmResponse.toString());
            System.out.println("END Confirm DeviceRecognition " + userid + " Request ++++++++");
            return dfpConfirmResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Confirm DeviceRecognition " + userid + " Request ++++++++");
        return null;
    }

    @Override
    public DFPValidateResponse validateNewDevice(SAAccess saAccess, String userid, String host_address, String jsonString) {
        System.out.println("START Validate DeviceRecognition " + userid + " Request ++++++++");
        DFPValidateResponse dfpValidateResponse = saAccess.DFPValidateNewFingerprint(userid,host_address,jsonString);
        if(dfpValidateResponse != null){
            System.out.println(dfpValidateResponse.toString());
            System.out.println("END Validate DeviceRecognition " + userid + " Request ++++++++");
            return dfpValidateResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Validate DeviceRecognition " + userid + " Request ++++++++");
        return null;
    }
}
