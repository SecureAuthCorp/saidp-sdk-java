package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.BehaveBioResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.interfaces.BehaveBioInterface;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class BehaveBioImpl implements BehaveBioInterface {

    public BehaveBioImpl(){}

    @Override
    public JSObjectResponse getBehaveBioJavaScriptSrc(SAAccess saAccess) {
        System.out.println("START BehaveBio JavaScript Get URL Request ++++++++");
        JSObjectResponse jsObject = saAccess.BehaveBioJSSrc();
        if(jsObject != null){
            System.out.println(jsObject.getSrc());
        }else{
            System.out.println("Null Object Response");
        }

        System.out.println("END BehaveBio JavaScript Get URL Request ++++++++");
        return jsObject;
    }

    @Override
    public BehaveBioResponse submitBehaveBioProfile(SAAccess saAccess, String userid, String behaviorProfile, String hostAddress, String userAgent) {
        System.out.println("START Submit BehaveBio " + userid + " Request ++++++++");
        BehaveBioResponse userBioProfile = saAccess.BehaveBioProfileSubmit(userid,behaviorProfile,hostAddress,userAgent);
        if(userBioProfile != null){
            System.out.println(userBioProfile.toString());
            System.out.println("END Submit BehaveBio " + userid + " Request ++++++++");
            return userBioProfile;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Submit BehaveBio " + userid + " Request ++++++++");
        return null;
    }

    @Override
    public ResponseObject resetBehaveBioProfile(SAAccess saAccess, String userid, String fieldName, String fieldType, String deviceType) {
        System.out.println("START Submit BehaveBio reset " + userid + " Request ++++++++");
        ResponseObject userBioProfile = saAccess.BehaveBioProfileReset(userid,fieldName,fieldType,deviceType);
        if(userBioProfile != null){
            System.out.println(userBioProfile.toString());
            System.out.println("END Submit BehaveBio Reset " + userid + " Request ++++++++");
            return userBioProfile;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Submit BehaveBio Reset " + userid + " Request ++++++++");
        return null;
    }
}
