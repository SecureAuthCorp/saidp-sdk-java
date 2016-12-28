package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.*;
import org.secureauth.sarestapi.interfaces.BehaveBioInterface;
import org.secureauth.sarestapi.interfaces.PhoneNumberProfileInterface;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class PhoneNumberProfileImpl implements PhoneNumberProfileInterface {

    public PhoneNumberProfileImpl(){}


    @Override
    public NumberProfileResponse submitPhoneNumber(SAAccess saAccess, String userid, String phone_number) {
        System.out.println("START Submit PhoneNumber " + userid + " Request ++++++++");
        NumberProfileResponse userPhoneNumberProfile = saAccess.PhoneNumberProfileSubmit(userid,phone_number);
        if(userPhoneNumberProfile != null){
            System.out.println(userPhoneNumberProfile.toString());
            System.out.println("END Submit PhoneNumber " + userid + " Request ++++++++");
            return userPhoneNumberProfile;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Submit PhoneNumeber " + userid + " Request ++++++++");
        return null;
    }

    @Override
    public BaseResponse updatePhoneNumberProfile(SAAccess saAccess, String userid, String phone_number, String portedStatus, String carrierCode, String carrier, String countryCode, String networkType) {
        System.out.println("START Submit PhoneNumber Update " + userid + " Request ++++++++");
        BaseResponse userPhoneNumberProfile = saAccess.UpdatePhoneNumberProfile(userid,phone_number,portedStatus,carrierCode,carrier,countryCode,networkType);
        if(userPhoneNumberProfile != null){
            System.out.println(userPhoneNumberProfile.toString());
            System.out.println("END Submit PhoneNumber Update " + userid + " Request ++++++++");
            return userPhoneNumberProfile;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Submit PhoneNumber Update " + userid + " Request ++++++++");
        return null;
    }
}
