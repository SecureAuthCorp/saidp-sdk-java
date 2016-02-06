package org.secureauth.restapi.examples;


import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.FactorsResponse;
import org.secureauth.sarestapi.data.IPEval;
import org.secureauth.sarestapi.data.ResponseObject;


/**
 * @author rrowcliffe@secureauth.com
 *
Copyright (c) 2015, SecureAuth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class SendAndValidateSMS {

    //Define our User Variables
    private static String user = "user";

    //Required for connectivity to Appliance
    private static String applianceHost = "host.example.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static String realm = "secureauth1";
    private static String applicationID = "........";
    private static String applicationKey = ".......";


    public static void main(String[] args){

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost,appliancePort,applianceSSL,realm, applicationID, applicationKey);

        System.out.println("Start Test++++++++++++++++++");

        //Grab all available Factors for a user
        FactorsResponse factorsResponse = getFactors(saAccess, user);

        //Loop through Factors and test OTP Delivery
        for(Factors factor : factorsResponse.getFactors()){

            if(factor.getType().equalsIgnoreCase("phone") ) {
                for (String capability : factor.getCapabilities()) {
                    //Test SMS Delivery
                    if (capability.equalsIgnoreCase("sms")) {
                        testSmsOTP(saAccess, user, factor.getId());
                    }
                }
            }

        }

        //Grab IPViking data based on user and IP Address
        //testIP(saAccess, user,"116.211.0.90");

        System.out.println("End Test+++++++++++++++++++");

    }


    private static ResponseObject testUser(SAAccess saAccess,String userid){
        //Validate the User Exists
        ResponseObject responseObject = saAccess.validateUser(userid);
        if(responseObject != null){

            if(responseObject.getStatus().equalsIgnoreCase("found")){
                System.out.println("We matched User");

                System.out.println(responseObject.toString());


            }

        }
        return responseObject;
    }

    private static ResponseObject testPassword(SAAccess saAccess, String userid, String password){
        ResponseObject passObject = saAccess.validateUserPassword(userid, password);
        if(passObject != null){

            System.out.println("Password Test+++++++");
            System.out.println(passObject.toString());
            System.out.println("END Password Test+++++++");
        }
        return passObject;
    }

    private static ResponseObject testPhoneOTP(SAAccess saAccess,String userid, String factorId){
        ResponseObject phoneOTP = saAccess.deliverOTPByPhone(userid, factorId);
        System.out.println("PHONE OTP is: " + phoneOTP.getOtp());
        return phoneOTP;
    }

    private static ResponseObject testSmsOTP(SAAccess saAccess,String userid, String factorId){
        ResponseObject smsOTP = saAccess.deliverOTPBySMS(userid, factorId);
        System.out.println("SMS OTP is: " + smsOTP.getOtp());
        return smsOTP;
    }

    private static ResponseObject testEmailOTP(SAAccess saAccess, String userid, String factorId){
        ResponseObject emailOTP = saAccess.deliverOTPByEmail(userid,factorId);
        System.out.println("Email OTP is: " + emailOTP.getOtp());
        return emailOTP;
    }

    private static ResponseObject testKBQ(SAAccess saAccess,String userid,String answer,String factorId){
        System.out.println("TEST KBQ:  " + factorId);
        ResponseObject kba = saAccess.validateKba(userid,answer,factorId);
        System.out.println(kba.toString());
        System.out.println("END KBQ +++++++++++");

        return kba;
    }


    private static FactorsResponse getFactors(SAAccess saAccess,String userid){
        //Return Factors
        FactorsResponse factorsResponse = saAccess.factorsByUser(userid);
        if(!factorsResponse.getStatus().equalsIgnoreCase("invalid")){
            System.out.println("FACTORS +++++++++++++++++\n" + factorsResponse.toString());
            System.out.println("END FACTORS++++++++++++++");
        }
        return factorsResponse;
    }

    private static ResponseObject testOath(SAAccess saAccess, String otp, String factorId){
        ResponseObject responseObject = saAccess.validateOath(user,otp,factorId);
        System.out.println(responseObject.toString());
        return responseObject;
    }

    private static ResponseObject testPush(SAAccess saAccess, String factorId){
        ResponseObject responseObject = saAccess.deliverOTPByPush(user,factorId);
        System.out.println(responseObject.toString());
        return responseObject;
    }

    private static IPEval testIP(SAAccess saAccess,String userid, String ip_address){
        IPEval ipEval = saAccess.iPEvaluation(userid, ip_address);
        System.out.println(ipEval.toString());
        return ipEval;
    }

}
