package org.secureauth.restapi.test;


import org.secureauth.restapi.test.Impl.*;
import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.UserProfile.*;
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
    private static String password = "PassWord";
    private static String otp = "#####";

    //Required for connectivity to Appliance
    private static String applianceHost = "host.domain.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static boolean selfSigned = true;
    private static String realm = "secureauth1";
    private static String applicationID = "#######################";
    private static String applicationKey = "################################";

    public static void main(String[] args) {

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost, appliancePort, applianceSSL, selfSigned, realm, applicationID, applicationKey);



        //Get Interface Implementations
        AuthenticationImpl authentication = new AuthenticationImpl();
        AdaptiveAuthenticationImpl adaptiveAuthentication = new AdaptiveAuthenticationImpl();
        DeviceRecognitionImpl deviceRecognition = new DeviceRecognitionImpl();
        BehaveBioImpl behaveBio = new BehaveBioImpl();
        IDMImpl idm = new IDMImpl();
        AdHocImpl adHoc = new AdHocImpl();
        PhoneNumberProfileImpl phoneNumberProfile = new PhoneNumberProfileImpl();

        System.out.println("Start Test++++++++++++++++++");


        /**
         * Start Ad Hoc Tests
         */

        //Phone Call
        adHoc.sendAdHocPhoneOTP(saAccess,"user","##########");

        //SMS
        adHoc.sendAdHocSmsOTP(saAccess,"user", "##########");

        //Email
        adHoc.sendAdHocEmailOTP(saAccess, "UserID", "user@domian.com");

        /**
         * End Ad Hoc Tests
         */

        /**
         * Start Phone Number Profile Service Tests
         */

        //Run Number through the Service
        phoneNumberProfile.submitPhoneNumber(saAccess,"User", "##########");

        //Update the users Phone and carrier Options
        phoneNumberProfile.updatePhoneNumberProfile(saAccess,"User","#######","not_ported", "US-FIXED","O2", "UK", "mobile");

        /**
         * End Phone Number Profile Service Tests
         */


        /**
         * Start Authentication Tests
         */

        //Validate User
        authentication.validateUser(saAccess, user);

        //Validate User & Password
        authentication.validatePassword(saAccess,user,password);

        //Grab all available Factors for a user
        FactorsResponse factorsResponse = authentication.getFactorsForUser(saAccess,user);

        //Loop through Factors and test OTP Delivery
        for (Factors factor : factorsResponse.getFactors()) {


            //Validate OATH Token (Hard, Soft Tokens)
            if(factor.getType().equalsIgnoreCase("oath")){
                authentication.validateOath(saAccess,user,otp,factor.getId());
            }




            //TestPush Delivery
            if(factor.getType().equalsIgnoreCase("push")){
                authentication.validatePushOTP(saAccess,user,factor.getId());
            }

          //Test Push to Accept
            try {
                if(factor.getType().equalsIgnoreCase("push")){
                    for (String capability : factor.getCapabilities()){
                        if (capability.equalsIgnoreCase("push_accept")) {
                            authentication.PushToAccept(saAccess,user,factor.getId(),"192.168.2.192");
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
                      if(factor.getId().equalsIgnoreCase("phone1")) {
                          authentication.sendSmsOTP(saAccess, user, factor.getId());
                      }
                    }

                    //Test Telephony Delivery
                    if (capability.equalsIgnoreCase("call")) {
                        if(factor.getId().equalsIgnoreCase("phone2")){
                            authentication.sendPhoneOTP(saAccess, user, factor.getId());
                        }

                    }
                }
            }




            //Test email delivery
            if(factor.getType().equalsIgnoreCase("email")){
                authentication.sendEmailOTP(saAccess, user, factor.getId());
            }





            //Test KBQ / KBA
            if(factor.getType().equalsIgnoreCase("kbq")){
                if(factor.getId().equalsIgnoreCase("KBQ1")){
                    authentication.validateKBQ(saAccess, user, "zzzzzzzzzzzzzz", factor.getId());
                }
            }

        }

        /*
        Adaptive Authentication Tests
        */

        //Grab Threat Feed data based on user and IP Address
        adaptiveAuthentication.ipThreatCheck(saAccess, user,"162.247.72.201");

        //Test Adaptive Auth
        adaptiveAuthentication.submitAdaptiveAuth(saAccess,user,"162.247.72.201");

        //Access Histories Test
        adaptiveAuthentication.updateAccessHistories(saAccess,user,"192.168.1.185");

        /*
        Device Recognition Test
        */
        // Get Java Script Source URL
        deviceRecognition.getDFPJavaScriptSrc(saAccess);


        /**
        * Start IDM Test
        */

        idm.getUserProfile(saAccess,user);

        idm.passwordChange(saAccess,user,"Password", "Password");
        idm.passwordReset(saAccess,user,"Password");


        //Create User test
        NewUserProfile newUserProfile = new NewUserProfile();
        newUserProfile.setUserId("restuser");
        newUserProfile.setPassword("password");
        NewUserProfileProperties newUserProfileProperties = new NewUserProfileProperties();
        newUserProfileProperties.setEmail1("user@domain.com");
        newUserProfileProperties.setFirstName("RestAPI");
        newUserProfileProperties.setLastName("SDK");
        newUserProfileProperties.setPhone1("111-222-4444");
        newUserProfile.setProperties(newUserProfileProperties);

        idm.createUser(saAccess, newUserProfile);


        //Update User Test
        NewUserProfile updateUserProfile = new NewUserProfile();
        NewUserProfileProperties updatedProfileProperties = new NewUserProfileProperties();
        updatedProfileProperties.setPhone2("111-222-4444");
        UserProfileKB kb = new UserProfileKB();
        kb.setQuestion("What is your Favorite Color on a Car?");
        kb.setAnswer("Red");

        updateUserProfile.getKnowledgeBase().put("kbq1",kb);
        updateUserProfile.setProperties(updatedProfileProperties);

        idm.updateUser(saAccess,"restuser",updateUserProfile);


        //Add User To Group Test
        idm.addUserToGroup(saAccess,"restuser","Group C");

        //Add User to Multiple Groups
        UserToGroups userToGroups = new UserToGroups();
        String[] groupList = new String[]{"Group A","Group B"};
        userToGroups.setGroupNames(groupList);
        idm.addUserToGroups(saAccess,"restuser",userToGroups);


        //Group Add Single User
        idm.addGroupToUser(saAccess,"Group D","restuser");


        //Create Multiple Users
        String[] userNames = new String[]{"restUser100","restUser101","restUser102"};

        for (String userName : userNames){
            NewUserProfile newUsersProfile = new NewUserProfile();
            newUsersProfile.setUserId(userName);
            newUsersProfile.setPassword("93$q!SAT");

            NewUserProfileProperties newUsersProfileProperties = new NewUserProfileProperties();
            newUsersProfileProperties.setEmail1("user@domain.com");
            newUsersProfileProperties.setFirstName(userName);
            newUsersProfileProperties.setLastName("SDK");
            newUsersProfileProperties.setPhone1("1112224444");
            newUsersProfile.setProperties(newUserProfileProperties);

            idm.createUser(saAccess, newUsersProfile);

        }


        //Add Multipe Users to group test
        UsersToGroup usersToGroup = new UsersToGroup();
        usersToGroup.setUserIds(userNames);

        idm.addUsersToGroup(saAccess, usersToGroup, "Group E");



        /**
         * END IDM Test
         /*


        *//*
         Start Behave Bio Test
         */

        behaveBio.getBehaveBioJavaScriptSrc(saAccess);

        //Submit a Test Profile
        String bProfile = "  [" +
                "    [\"m\"," +
                "     \"n\"," +
                "     {\"mozPay\":null," +
                "      \"mozContacts\":{}," +
                "      \"mozApps\":{}," +
                "      \"doNotTrack\":\"unspecified\"," +
                "      \"battery\":{}," +
                "      \"oscpu\":\"Intel+Mac+OS+X+10.10\"," +
                "      \"vendor\":\"\"," +
                "      \"vendorSub\":\"\"," +
                "      \"productSub\":\"20100101\"," +
                "      \"cookieEnabled\":true," +
                "      \"buildID\":\"20160210153822\"," +
                "      \"mediaDevices\":{}," +
                "      \"serviceWorker\":{" +
                "        \"controller\":null" +
                "        }," +
                "        \"geolocation\":{}," +
                "        \"appCodeName\":\"Mozilla\"," +
                "        \"appName\":\"Netscape\"," +
                "        \"appVersion\":\"5.0+(Macintosh)\"," +
                "        \"platform\":\"MacIntel\"," +
                "        \"userAgent\":\"Mozilla/5.0+(Macintosh;+Intel+Mac+OS+X+10.10;+rv:44.0)+Gecko/20100101+Firefox/44.0\"," +
                "        \"product\":\"Gecko\"," +
                "        \"language\":\"en-US\"," +
                "        \"languages\":[\"en-US\",\"en\"]," +
                "        \"onLine\":true" +
                "      }" +
                "    ]," +
                "        [\"m\"," +
                "         \"s\"," +
                "         {\"availWidth\":1440," +
                "         \"availHeight\":823," +
                "         \"width\":1440," +
                "         \"height\":900," +
                "         \"colorDepth\":24," +
                "         \"pixelDepth\":24," +
                "         \"top\":0," +
                "         \"left\":0," +
                "         \"availTop\":23," +
                "         \"availLeft\":0," +
                "         \"mozOrientation\":\"landscape-primary\"," +
                "         \"onmozorientationchange\":null," +
                "         \"orientation\":{}" +
                "         }" +
                "        ]," +
                "        [\"m\",\"v\",250]," +
                "        [\"w\"," +
                "          [" +
                "            {\"text#UserID\":7}," +
                "            {\"password#Password\":12}," +
                "            {\"submit#Submitclick\":7}" +
                "            ]" +
                "        ]," +
                "          [\"f\"," +
                "          \"text#UserID\"," +
                "          [" +
                "            [0,65,14285]," +
                "            [0,68,14362]," +
                "            [1,65,14459]," +
                "            [0,77,14466]," +
                "            [1,68,14562]," +
                "            [1,77,14643]," +
                "            [0,73,14682]," +
                "            [0,78,14818]," +
                "            [1,73,14882]," +
                "            [1,78,14946]," +
                "            [0,49,15018]," +
                "            [1,49,15194]," +
                "            [0,48,15202]," +
                "            [1,48,15282]," +
                "            [0,9,15410]" +
                "          ]" +
                "        ]," +
                "        [\"fa\"," +
                "          \"password#Password\"," +
                "          [" +
                "            [0,0,15804]," +
                "            [0,1,15850]," +
                "            [0,2,15915]," +
                "            [1,0,15955]," +
                "            [0,3,15970]," +
                "            [1,1,16010]," +
                "            [1,2,16066]," +
                "            [1,3,16106]," +
                "            [0,4,16282]," +
                "            [0,4,16411]," +
                "            [0,5,16459]," +
                "            [0,6,16530]," +
                "            [0,7,16586]," +
                "            [1,4,16634]," +
                "            [1,5,16674]," +
                "            [1,4,16714]," +
                "            [1,6,16730]," +
                "            [1,7,16770]," +
                "            [0,8,17219]," +
                "            [0,9,17283]," +
                "            [0,10,17339]," +
                "            [0,11,17395]," +
                "            [1,8,17426]," +
                "            [1,9,17434]," +
                "            [1,10,17466]," +
                "            [1,11,17498]" +
                "            ]" +
                "          ]" +
                "        ]";



        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:44.0) Gecko/20100101 Firefox/44.0";
        String hostIP = "192.1.1.22";
        bProfile.replaceAll("\\s+","");
        bProfile.replaceAll("\\\\","");
        behaveBio.submitBehaveBioProfile(saAccess,"restuser",bProfile.replaceAll("\\s+",""),hostIP,userAgent);

        //Global Reset
        //behaveBio.resetBehaveBioProfile(saAccess,"restuser","ALL","ALL","ALL");

        /*
         END Behave Bio Test
         */

        System.out.println("End Test+++++++++++++++++++");

    }


}