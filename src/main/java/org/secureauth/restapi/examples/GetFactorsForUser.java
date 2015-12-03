package org.secureauth.restapi.examples;


import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.FactorsResponse;


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
public class GetFactorsForUser {

    //Define our User Variables
    private static String user = "lding";

    //Required for connectivity to Appliance
    private static String applianceHost = "qaportal2.gosecureauth.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
	private static String realm = "secureauth33";
	private static String applicationID = "7635d6e3be694291b08c7243bb9e2db5";
	private static String applicationKey = "2714b243644a50565fc6b318f2b50463c6d1da066dd83dd71f093b923decd025";


    public static void main(String[] args) throws MalformedURLException, URISyntaxException{

        //Create Instance of SAAccess Object
        SAAccess saAccess = new SAAccess(applianceHost,appliancePort,applianceSSL,realm, applicationID, applicationKey);

        System.out.println("Start Test++++++++++++++++++");
        //Grab all available Factors for a user
        getFactors(saAccess, user);
        
        System.out.println("End Test++++++++++++++++++++");

    }

    private static FactorsResponse getFactors(SAAccess saAccess,String userid){
        //Return Factors
        FactorsResponse factorsResponse = saAccess.factorsByUser(userid);
        if(!factorsResponse.getStatus().equalsIgnoreCase("invalid")){
            System.out.println("FACTORS +++++++++++++++++\n" + factorsResponse.toString());
            System.out.println("END FACTORS++++++++++++++");
            }else{
            System.out.println("Failed to get factors " + factorsResponse.getMessage());
        }
        return factorsResponse;
    }

}
