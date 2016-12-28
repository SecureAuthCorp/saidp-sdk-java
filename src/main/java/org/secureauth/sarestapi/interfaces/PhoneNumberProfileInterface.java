package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.*;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public interface PhoneNumberProfileInterface {

    NumberProfileResponse submitPhoneNumber(SAAccess saAccess, String userid, String phone_number);

    BaseResponse updatePhoneNumberProfile(SAAccess saAccess, String userid, String phone_number, String portedStatus, String carrierCode, String carrier, String countryCode, String networkType);

}
