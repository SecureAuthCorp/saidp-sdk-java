package org.secureauth.restapi.test;


import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.FactorsResponse;
import org.secureauth.sarestapi.data.IPEval;
import org.secureauth.sarestapi.data.ResponseObject;


/**
 * @author rrowcliffe@secureauth.com
 * <p>
 *     SAAccess is a class that allows access to the SecureAuth REST API. The intention is to provide an easy method to access
 *     the Secureauth Authentication Rest Services.
 * </p>
 *
 * <p>
 * Copyright 2015 SecureAuth Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */
public class TestRestAPI {

    //Define our User Variables
    private static String user = "user";
    private static String password = "password";
    //private static String otp = "033994";

    //Required for connectivity to Appliance
    private static String applianceHost = "example.domain.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static String realm = "secureauth1";
    private static String applicationID = "5c1904cd5965480981c03a3e0db022b2";
    private static String applicationKey = "416968246ce72d55ec3c6b1e91e81671e3a89349f71d79cd801c2c97e1494aca";
    /*
    private static String applicationID = "2c30646d6ec64d6bae351c0728cf594d";
    private static String applicationKey = "6f03fdc29ff4dc17b95067bba0b68009d269193f77663d8a0a63c6a07079d626";
    */

    public static void main(String[] args){

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost,appliancePort,applianceSSL,realm, applicationID, applicationKey);

        System.out.println("Start Test++++++++++++++++++");

        //Validate User
        testUser(saAccess, user);

        //Validate User & Password
        testPassword(saAccess,user,password);

        //Grab all available Factors for a user
        FactorsResponse factorsResponse = getFactors(saAccess, user);

        //Loop through Factors and test OTP Delivery
        for(Factors factor : factorsResponse.getFactors()){

            //Validate OATH Token (Hard, Soft Tokens)
            /*
            if(factor.getType().equalsIgnoreCase("oath")){
               testOath(saAccess,otp,factor.getId());
            }
*/

            //TestPush Delivery
            if(factor.getType().equalsIgnoreCase("push")){
                testPush(saAccess,factor.getId());
            }
            //Test Delivery by All Registered Phones

            if(factor.getType().equalsIgnoreCase("phone")) {
                for (String capability : factor.getCapabilities()) {
                    //Test SMS Delivery
                    if (capability.equalsIgnoreCase("sms")) {
                        testSmsOTP(saAccess, user, factor.getId());
                    }

                    //Test Telephony Delivery
                    if (capability.equalsIgnoreCase("call")) {
                        testPhoneOTP(saAccess, user, factor.getId());
                    }
                }
            }
            /*
            //Test email delivery
            if(factor.getType().equalsIgnoreCase("email")){
                testEmailOTP(saAccess, user, factor.getId());
            }

            //Test KBQ / KBA
            /*
            if(factor.getType().equalsIgnoreCase("kbq")){
                if(factor.getId().equalsIgnoreCase("KBQ1")){
                    testKBQ(saAccess, user, "SB", factor.getId());
                }
            }
            */
        }

        //Grab IPViking data based on user and IP Address
        testIP(saAccess, user,"116.211.0.90");

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
