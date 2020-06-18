package org.secureauth.restapi.test;

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
    private static String realm = "secureauth1";
    private static String applicationID = "#######################";
    private static String applicationKey = "################################";

    public static void main(String[] args) {

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost, appliancePort, applianceSSL, selfSigned, realm, applicationID, applicationKey);

    }

}