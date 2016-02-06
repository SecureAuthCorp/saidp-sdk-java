package org.secureauth.restapi.examples;

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


import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.FactorsResponse;
import org.secureauth.sarestapi.data.PushAcceptStatus;
import org.secureauth.sarestapi.data.ResponseObject;

public class SendPushRequest {

	// Define our User Variables
	private static String user = "user";

	// Required for connectivity to Appliance
	private static String applianceHost = "host.example.com";
	private static String appliancePort = "443";
	private static boolean applianceSSL = true;
	private static String realm = "secureauth11";
	private static String applicationID = "..........";
	private static String applicationKey = "...........";

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException, InterruptedException {

		// Create Instance of SAAccess Object
		SAAccess saAccess = new SAAccess(applianceHost, appliancePort,
				applianceSSL, realm, applicationID, applicationKey);

		System.out.println("Start Test++++++++++++++++++");
		FactorsResponse factorsResponse = saAccess.factorsByUser(user);
		//Loop through Factors
        for(Factors factor : factorsResponse.getFactors()){
            if(factor.getType().equalsIgnoreCase("push") ) {
                for (String capability : factor.getCapabilities()) {
                    //Test
                    if (capability.equalsIgnoreCase("push_accept")) {
                    	ResponseObject ro = saAccess.sendPushToAcceptReq(user, factor.getId(), "192.168.2.192", null, null);
                    	System.out.println(ro);
                        String refId = ro.getReference_id();
                        PushAcceptStatus status;
                        do {
	                        Thread.sleep(2000);
	                        status = saAccess.queryPushAcceptStatus(refId);
	                        System.out.println(status);
                        } while("PENDING".equals(status.getMessage()));
                        //break;
                    }
                }
            }
        }

		System.out.println("End Test++++++++++++++++++++");

	}
}
