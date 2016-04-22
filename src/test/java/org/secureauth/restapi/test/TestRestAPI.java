package org.secureauth.restapi.test;


import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.*;
import org.secureauth.sarestapi.util.JSONUtil;
import org.secureauth.sarestapi.util.XMLUtil;


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
    private static String password = "xxxxxxxxx";
    //private static String otp = "584233";

    //Required for connectivity to Appliance
    private static String applianceHost = "host.example.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static String realm = "secureauth1";
    private static String applicationID = ".................";
    private static String applicationKey = "................";

    public static void main(String[] args) {

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost, appliancePort, applianceSSL, realm, applicationID, applicationKey);

        System.out.println("Start Test++++++++++++++++++");

        // Get Java Script Source URL
        getJSSrc(saAccess);


        //Validate User
        testUser(saAccess, user);


        //Validate User & Password
        testPassword(saAccess,user,password);

        //Grab all available Factors for a user
        FactorsResponse factorsResponse = getFactors(saAccess, user);

        //Loop through Factors and test OTP Delivery
        for (Factors factor : factorsResponse.getFactors()) {

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


            //Test Push to Accept
            try {
                if(factor.getType().equalsIgnoreCase("push")){
                    for (String capability : factor.getCapabilities()){
                        if (capability.equalsIgnoreCase("push_accept")) {
                            testPushToAccept(saAccess,user,factor.getId(),"192.168.2.192");
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Test Delivery by All Registered Phones

            if(factor.getType().equalsIgnoreCase("phone")) {
                for (String capability : factor.getCapabilities()) {
                    //Test SMS Delivery
                    if (capability.equalsIgnoreCase("sms")) {
                      if(factor.getId().equalsIgnoreCase("phone2")) {
                          testSmsOTP(saAccess, user, factor.getId());
                      }
                    }

                    //Test Telephony Delivery
                    if (capability.equalsIgnoreCase("call")) {
                        if(factor.getId().equalsIgnoreCase("phone2")){
                            testPhoneOTP(saAccess, user, factor.getId());
                        }

                    }
                }
            }

/*
            //Test email delivery
            if(factor.getType().equalsIgnoreCase("email")){
                testEmailOTP(saAccess, user, factor.getId());
            }
*/
            //Test KBQ / KBA

/*            if(factor.getType().equalsIgnoreCase("kbq")){
                if(factor.getId().equalsIgnoreCase("KBQ1")){
                    testKBQ(saAccess, user, "zzzzzzzzzzzzzz", factor.getId());
                }
            }
*/
        }

        //Grab Threat Feed data based on user and IP Address
        testIP(saAccess, user,"162.247.72.201");

        //Test Adaptive Auth
        testAdaptiveAuth(saAccess,user,"162.247.72.201");

        //Access Histories Test
        testAccessHistories(saAccess,user,"162.247.72.201");

        System.out.println("End Test+++++++++++++++++++");

    }


    private static ResponseObject testUser(SAAccess saAccess, String userid) {
        //Validate the User Exists
        ResponseObject responseObject = saAccess.validateUser(userid);
        if (responseObject != null) {

            if (responseObject.getStatus().equalsIgnoreCase("found")) {
                System.out.println("We matched User");

                System.out.println(responseObject.toString());


            }

        }
        return responseObject;
    }

    private static ResponseObject testPassword(SAAccess saAccess, String userid, String password) {
        ResponseObject passObject = saAccess.validateUserPassword(userid, password);
        if (passObject != null) {

            System.out.println("Password Test+++++++");
            System.out.println(passObject.toString());
            System.out.println("END Password Test+++++++");
        }
        return passObject;
    }

    private static ResponseObject testPhoneOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject phoneOTP = saAccess.deliverOTPByPhone(userid, factorId);
        System.out.println("PHONE OTP is: " + phoneOTP.getOtp());
        return phoneOTP;
    }

    private static ResponseObject testSmsOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject smsOTP = saAccess.deliverOTPBySMS(userid, factorId);
        System.out.println("SMS OTP is: " + smsOTP.getOtp());
        return smsOTP;
    }

    private static ResponseObject testEmailOTP(SAAccess saAccess, String userid, String factorId) {
        ResponseObject emailOTP = saAccess.deliverOTPByEmail(userid, factorId);
        System.out.println("Email OTP is: " + emailOTP.getOtp());
        return emailOTP;
    }

    private static ResponseObject testKBQ(SAAccess saAccess, String userid, String answer, String factorId) {
        System.out.println("TEST KBQ:  " + factorId);
        ResponseObject kba = saAccess.validateKba(userid, answer, factorId);
        System.out.println(kba.toString());
        System.out.println("END KBQ +++++++++++");

        return kba;
    }


    private static FactorsResponse getFactors(SAAccess saAccess, String userid) {
        //Return Factors
        FactorsResponse factorsResponse = saAccess.factorsByUser(userid);
        System.out.println(factorsResponse.getMessage());
        if (!factorsResponse.getStatus().equalsIgnoreCase("invalid")) {
            System.out.println("FACTORS +++++++++++++++++\n" + factorsResponse.toString());
            System.out.println("END FACTORS++++++++++++++");
        }
        return factorsResponse;
    }

    private static ResponseObject testOath(SAAccess saAccess, String otp, String factorId) {
        ResponseObject responseObject = saAccess.validateOath(user, otp, factorId);
        System.out.println("Start OATH Test+++++++++++++++");
        System.out.println(responseObject.toString());
        System.out.println("End OATH Test+++++++++++++++");
        return responseObject;
    }

    private static ResponseObject testPush(SAAccess saAccess, String factorId) {
        ResponseObject responseObject = saAccess.deliverOTPByPush(user, factorId);
        System.out.println("Start Push Notification +++++++++++++++++");
        System.out.println(responseObject.toString());
        System.out.println("End Push Notification +++++++++++++++++");
        return responseObject;
    }

    private static IPEval testIP(SAAccess saAccess, String userid, String ip_address) {
        IPEval ipEval = saAccess.iPEvaluation(userid, ip_address);
        System.out.println("Start IPEvaluation  +++++++++++++++++");
        if (ipEval != null) {
            System.out.println(JSONUtil.convertObjectToJSON(ipEval));
            System.out.println(XMLUtil.convertObjectToXML(ipEval));
        } else {
            System.out.println("If you See this then Something went Wrong!!");
        }
        System.out.println("End IPEvaluation  +++++++++++++++++");
        return ipEval;
    }

    private static JSObjectResponse getJSSrc(SAAccess saAccess) {
        System.out.println("START JavaScript Get URL Request ++++++++");
        JSObjectResponse jsObject = saAccess.javaScriptSrc();
        System.out.println(jsObject.getSrc());
        System.out.println("END JavaScript Get URL Request ++++++++");
        return jsObject;
    }

    private static ResponseObject testAccessHistories(SAAccess saAccess, String user, String ip_address) {
        System.out.println("START Access Histories test +++++++++");
        ResponseObject ahistories = saAccess.accessHistory(user, ip_address);
        System.out.println(ahistories.toString());
        System.out.println("END Access Histories test +++++++++++");
        return ahistories;
    }

    private static void testPushToAccept(SAAccess saAccess, String user, String factorID, String ipAddress) throws InterruptedException {

        System.out.println("Start Push 2 Accept Test ++++++++++++++++++");
        ResponseObject ro = saAccess.sendPushToAcceptReq(user, factorID, ipAddress, null, null);
        System.out.println(ro);
        String refId = ro.getReference_id();
        PushAcceptStatus status;
        do {
            Thread.sleep(2000);
            status = saAccess.queryPushAcceptStatus(refId);
            System.out.println(status);
        } while ("PENDING".equals(status.getMessage()));
        //break;
        System.out.println("END Push 2 Accept Test+++++++++++++++++++");
    }

    private static ResponseObject testAdaptiveAuth(SAAccess saAccess, String user, String ipAddress){
        System.out.println("Start Adaptive Test ++++++++++++++++");
        ResponseObject responseObject = saAccess.adaptiveAuthQuery(user,ipAddress);
        System.out.println(responseObject.toString());
        System.out.println("End Adaptive Test ++++++++++++++++++");
        return responseObject;
    }
}