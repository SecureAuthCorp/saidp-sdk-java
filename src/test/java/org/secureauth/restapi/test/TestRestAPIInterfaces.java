package org.secureauth.restapi.test;


import org.secureauth.restapi.test.Impl.*;
import org.secureauth.sarestapi.SAAccess;
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
    private static String user = "User";
    private static String password = "PassWord";
    private static String otp = "#####";

    //Required for connectivity to Appliance
    private static String applianceHost = "host.example.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static boolean selfSigned = true;
    private static String realm = "secureauth8";
    private static String applicationID = "";
    private static String applicationKey = "";

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
       // adHoc.sendAdHocPhoneOTP(saAccess,"user","##########");

        //SMS
       // adHoc.sendAdHocSmsOTP(saAccess,"user", "##########");

        //Email
       // adHoc.sendAdHocEmailOTP(saAccess, "UserID", "user@domian.com");

        /**
         * End Ad Hoc Tests
         */

        /**
         * Start Phone Number Profile Service Tests
         */

        //Run Number through the Service
       // phoneNumberProfile.submitPhoneNumber(saAccess,"User", "##########");

        //Update the users Phone and carrier Options
       // phoneNumberProfile.updatePhoneNumberProfile(saAccess,"User","#######","not_ported", "US-FIXED","O2", "UK", "mobile");

        /**
         * End Phone Number Profile Service Tests
         */


        /**
         * Start Authentication Tests
         */

        //Validate User
        //authentication.validateUser(saAccess, user);

        //Validate User & Password
        //authentication.validatePassword(saAccess,user,password);

        //Grab all available Factors for a user
        //FactorsResponse factorsResponse = authentication.getFactorsForUser(saAccess,user);

        //Loop through Factors and test OTP Delivery
        /*for (Factors factor : factorsResponse.getFactors()) {


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

    */    /*
        Adaptive Authentication Tests
        */

        //Grab Threat Feed data based on user and IP Address
  //      adaptiveAuthentication.ipThreatCheck(saAccess, user,"162.247.72.201");

        //Test Adaptive Auth
//        adaptiveAuthentication.submitAdaptiveAuth(saAccess,user,"162.247.72.201");

        //Access Histories Test
 //       adaptiveAuthentication.updateAccessHistories(saAccess,user,"192.168.1.185");

        /*
        Device Recognition Test
        */
        // Get Java Script Source URL
      //  deviceRecognition.getDFPJavaScriptSrc(saAccess);

        //Create the JSON String
        String jstring = "{\n" +
                "  \"fingerprint\": {\n" +
                "    \"uaBrowser\": {\n" +
                "      \"name\": \"IE\",\n" +
                "      \"version\": \"11.0\",\n" +
                "      \"major\": \"11\"\n" +
                "    },\n" +
                "    \"uaString\": \"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.3; wbx 1.0.0; rv:11.0) like Gecko\",\n" +
                "    \"uaDevice\": {\n" +
                "      \"model\": null,\n" +
                "      \"type\": null,\n" +
                "      \"vendor\": null\n" +
                "    },\n" +
                "    \"uaEngine\": {\n" +
                "      \"name\": \"Trident\",\n" +
                "      \"version\": \"7.0\"\n" +
                "    },\n" +
                "    \"uaOS\": {\n" +
                "      \"name\": \"Windows\",\n" +
                "      \"version\": \"7\"\n" +
                "    },\n" +
                "    \"uaCPU\": {\n" +
                "      \"architecture\": \"amd64\"\n" +
                "    },\n" +
                "    \"uaPlatform\": \"Win32\",\n" +
                "    \"language\": \"en-US\",\n" +
                "    \"colorDepth\": 24,\n" +
                "    \"pixelRatio\": 1,\n" +
                "    \"screenResolution\": \"1920x1080\",\n" +
                "    \"availableScreenResolution\": \"1920x1040\",\n" +
                "    \"timezone\": \"America/New_York\",\n" +
                "    \"timezoneOffset\": 240,\n" +
                "    \"localStorage\": true,\n" +
                "    \"sessionStorage\": true,\n" +
                "    \"indexedDb\": true,\n" +
                "    \"addBehavior\": false,\n" +
                "    \"openDatabase\": false,\n" +
                "    \"cpuClass\": \"x86\",\n" +
                "    \"platform\": \"Win32\",\n" +
                "    \"doNotTrack\": \"1\",\n" +
                "    \"plugins\": \"AcroPDF.PDF,Adodb.Stream,AgControl.AgControl,MacromediaFlashPaper.MacromediaFlashPaper,Msxml2.DOMDocument,Msxml2.XMLHTTP,Scripting.Dictionary,SWCtl.SWCtl,Shell.UIHelper,ShockwaveFlash.ShockwaveFlash,TDCCtl.TDCCtl,WMPlayer.OCX\",\n" +
                "    \"canvas\": \"-638268635\",\n" +
                "    \"webGl\": \"256484308\",\n" +
                "    \"adBlock\": false,\n" +
                "    \"userTamperLanguage\": false,\n" +
                "    \"userTamperScreenResolution\": false,\n" +
                "    \"userTamperOS\": false,\n" +
                "    \"userTamperBrowser\": false,\n" +
                "    \"touchSupport\": {\n" +
                "      \"maxTouchPoints\": 0,\n" +
                "      \"touchEvent\": false,\n" +
                "      \"touchStart\": false\n" +
                "    },\n" +
                "    \"cookieSupport\": true,\n" +
                "    \"fonts\": \"Agency FB,Aharoni,Algerian,Andalus,Angsana New,AngsanaUPC,Aparajita,Arabic Typesetting,Arial,Arial Black,Arial Narrow,Arial Rounded MT Bold,Arial Unicode MS,Baskerville Old Face,Batang,BatangChe,Bauhaus 93,Bell MT,Berlin Sans FB,Berlin Sans FB Demi,Bernard MT Condensed,Blackadder ITC,Bodoni MT,Bodoni MT Black,Bodoni MT Condensed,Bodoni MT Poster Compressed,Book Antiqua,Bookman Old Style,Bookshelf Symbol 7,Bradley Hand ITC,Britannic Bold,Broadway,Browallia New,BrowalliaUPC,Brush Script MT,Calibri,Californian FB,Calisto MT,Cambria,Cambria Math,Candara,Castellar,Century,Century Gothic,Century Schoolbook,Chiller,Colonna MT,Comic Sans MS,Consolas,Constantia,Cooper Black,Copperplate Gothic,Copperplate Gothic Bold,Copperplate Gothic Light,Corbel,Cordia New,CordiaUPC,Curlz MT,DaunPenh,David,DFKai-SB,DilleniaUPC,DokChampa,Dotum,DotumChe,Ebrima,Edwardian Script ITC,Elephant,Engravers MT,Eras Bold ITC,Eras Demi ITC,Eras Light ITC,Eras Medium ITC,Estrangelo Edessa,EucrosiaUPC,Euphemia,FangSong,Felix Titling,Footlight MT Light,Forte,Franklin Gothic,Franklin Gothic Book,Franklin Gothic Demi,Franklin Gothic Demi Cond,Franklin Gothic Heavy,Franklin Gothic Medium,Franklin Gothic Medium Cond,FrankRuehl,FreesiaUPC,Freestyle Script,French Script MT,Gabriola,Garamond,Gautami,Georgia,Gigi,Gill Sans MT,Gill Sans MT Condensed,Gill Sans MT Ext Condensed Bold,Gill Sans Ultra Bold,Gill Sans Ultra Bold Condensed,Gisha,Gloucester MT Extra Condensed,Goudy Old Style,Goudy Stout,Gulim,GulimChe,Gungsuh,GungsuhChe,Haettenschweiler,Harlow Solid Italic,Harrington,HELV,Helvetica,High Tower Text,Impact,Imprint MT Shadow,Informal Roman,IrisUPC,Iskoola Pota,JasmineUPC,Jokerman,Juice ITC,KaiTi,Kalinga,Kartika,Khmer UI,KodchiangUPC,Kokila,Kristen ITC,Kunstler Script,Lao UI,Latha,Leelawadee,Levenim MT,LilyUPC,Lucida Bright,Lucida Calligraphy,Lucida Console,Lucida Fax,Lucida Handwriting,Lucida Sans,Lucida Sans Typewriter,Lucida Sans Unicode,Magneto,Maiandra GD,Malgun Gothic,Mangal,Marlett,Matura MT Script Capitals,Meiryo,Meiryo UI,Microsoft Himalaya,Microsoft JhengHei,Microsoft New Tai Lue,Microsoft PhagsPa,Microsoft Sans Serif,Microsoft Tai Le,Microsoft Uighur,Microsoft YaHei,Microsoft Yi Baiti,MingLiU,MingLiU_HKSCS,MingLiU_HKSCS-ExtB,MingLiU-ExtB,Miriam,Miriam Fixed,Mistral,Modern No. 20,Mongolian Baiti,Monotype Corsiva,MoolBoran,MS Gothic,MS Mincho,MS Outlook,MS PGothic,MS PMincho,MS Reference Sans Serif,MS Reference Specialty,MS Sans Serif,MS Serif,MS UI Gothic,MT Extra,MV Boli,Narkisim,Niagara Engraved,Niagara Solid,NSimSun,Nyala,OCR A Extended,Old English Text MT,Onyx,Palace Script MT,Palatino Linotype,Papyrus,Parchment,Perpetua,Perpetua Titling MT,Plantagenet Cherokee,Playbill,PMingLiU,PMingLiU-ExtB,Poor Richard,Pristina,Raavi,Rage Italic,Ravie,Rockwell,Rockwell Condensed,Rockwell Extra Bold,Rod,Roman,Sakkal Majalla,Script,Script MT Bold,Segoe Print,Segoe Script,Segoe UI,Segoe UI Light,Segoe UI Semibold,Segoe UI Symbol,Shonar Bangla,Showcard Gothic,Shruti,SimHei,Simplified Arabic,Simplified Arabic Fixed,SimSun,SimSun-ExtB,Small Fonts,Snap ITC,Stencil,Sylfaen,System,Tahoma,Tempus Sans ITC,Times,Times New Roman,Traditional Arabic,Trebuchet MS,Tunga,Tw Cen MT,Tw Cen MT Condensed,Tw Cen MT Condensed Extra Bold,Utsaah,Vani,Verdana,Vijaya,Viner Hand ITC,Vivaldi,Vladimir Script,Vrinda,Wide Latin,Wingdings,Wingdings 2,Wingdings 3\"\n" +
                "  }\n" +
                "}";

        //Test Device FingerPrint Endpoint
        deviceRecognition.validateNewDevice(saAccess,user,"192.168.1.185",jstring);
        //deviceRecognition.confirmDeviceRecognition();

        //Test Validate EndPoint
       // authentication.validateOTP(saAccess,user,otp);


        /**
        * Start IDM Test
        */
/*

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



        */
/**
         * END IDM Test
         /*


        *//*
*/
/*
         Start Behave Bio Test
         *//*


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

        */
/*
         END Behave Bio Test
         *//*


*/
        System.out.println("End Test+++++++++++++++++++");

    }


}