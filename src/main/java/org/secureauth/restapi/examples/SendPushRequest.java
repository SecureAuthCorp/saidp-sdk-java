package org.secureauth.restapi.examples;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.FactorsResponse;

public class SendPushRequest {

	// Define our User Variables
	private static String user = "lding";

	// Required for connectivity to Appliance
	private static String applianceHost = "qaportal2.gosecureauth.com";
	private static String appliancePort = "443";
	private static boolean applianceSSL = true;
	private static String realm = "secureauth33";
	private static String applicationID = "7635d6e3be694291b08c7243bb9e2db5";
	private static String applicationKey = "2714b243644a50565fc6b318f2b50463c6d1da066dd83dd71f093b923decd025";

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
                        String refId = saAccess.sendPushToAcceptReq(user, factor.getId(), "12.1.1.1", null, null).getReference_id();
                        Thread.sleep(500);
                        System.out.println(saAccess.queryPushAcceptStatus(refId));
                        break;
                    }
                }
            }

        }

		System.out.println("End Test++++++++++++++++++++");

	}
}
