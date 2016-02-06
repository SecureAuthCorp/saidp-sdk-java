package org.secureauth.restapi.examples;

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
