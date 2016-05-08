package org.secureauth.restapi.test;


import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.*;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.interfaces.AdaptiveAuthenticationInterface;
import org.secureauth.sarestapi.interfaces.AuthenticationInterface;
import org.secureauth.sarestapi.interfaces.JavascriptResource;
import org.secureauth.sarestapi.resources.s;
import org.secureauth.sarestapi.util.JSONUtil;
import org.secureauth.sarestapi.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
public class TestRestAPIInterfaces {
    private static Logger logger = LoggerFactory.getLogger(TestRestAPIInterfaces.class);
    //Define our User Variables
    private static String user = "user";
    private static String password = "xxxxxxxxx";
    //private static String otp = "584233";

    //Required for connectivity to Appliance
    private static String applianceHost = "host.example.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static String realm = "secureauth1";
    private static String applicationID = "...................";
    private static String applicationKey = "..............................";

    public static void main(String[] args) {

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost, appliancePort, applianceSSL,true, realm, applicationID, applicationKey);

        //Get Interface Implementation
        InterfaceTestImpl interfaceTest = new InterfaceTestImpl();

        System.out.println("Start Test++++++++++++++++++");

        // Get Java Script Source URL

        interfaceTest.getDFPJavaScriptSrc(saAccess);

        //Validate User
        interfaceTest.validateUser(saAccess, user);

        //Validate User & Password
        interfaceTest.validatePassword(saAccess,user,password);

        //Grab all available Factors for a user
        FactorsResponse factorsResponse = interfaceTest.getFactorsForUser(saAccess,user);

        //Loop through Factors and test OTP Delivery
        for (Factors factor : factorsResponse.getFactors()) {

/*
            //Validate OATH Token (Hard, Soft Tokens)
            if(factor.getType().equalsIgnoreCase("oath")){
                interfaceTest.validateOath(saAccess,user,otp,factor.getId());
            }
*/


            //TestPush Delivery
            if(factor.getType().equalsIgnoreCase("push")){
                interfaceTest.validatePushOTP(saAccess,user,factor.getId());
            }

            //Test Push to Accept
            try {
                if(factor.getType().equalsIgnoreCase("push")){
                    for (String capability : factor.getCapabilities()){
                        if (capability.equalsIgnoreCase("push_accept")) {
                            interfaceTest.PushToAccept(saAccess,user,factor.getId(),"192.168.2.192");
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
                          interfaceTest.sendSmsOTP(saAccess, user, factor.getId());
                      }
                    }

                    //Test Telephony Delivery
                    if (capability.equalsIgnoreCase("call")) {
                        if(factor.getId().equalsIgnoreCase("phone2")){
                            interfaceTest.sendPhoneOTP(saAccess, user, factor.getId());
                        }

                    }
                }
            }


/*
            //Test email delivery
            if(factor.getType().equalsIgnoreCase("email")){
                interfaceTest.sendEmailOTP(saAccess, user, factor.getId());
            }
*/

/*
            //Test KBQ / KBA
            if(factor.getType().equalsIgnoreCase("kbq")){
                if(factor.getId().equalsIgnoreCase("KBQ1")){
                    interfaceTest.validateKBQ(saAccess, user, "zzzzzzzzzzzzzz", factor.getId());
                }
            }
*/

        }

        //Grab Threat Feed data based on user and IP Address
        interfaceTest.ipThreatCheck(saAccess, user,"162.247.72.201");

        //Test Adaptive Auth
        interfaceTest.submitAdaptiveAuth(saAccess,user,"162.247.72.201");

        //Access Histories Test
        interfaceTest.updateAccessHistories(saAccess,user,"192.168.1.185");

        System.out.println("End Test+++++++++++++++++++");

    }


}