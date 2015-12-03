package org.secureauth.restapi.examples;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.ResponseObject;

public class AdaptiveAuth {

	// Define our User Variables
	private static String user = "lding";

	// Required for connectivity to Appliance
	private static String applianceHost = "qaportal2.gosecureauth.com";
	private static String appliancePort = "443";
	private static boolean applianceSSL = true;
	private static String realm = "secureauth11";
	private static String applicationID = "5e0f658a77484a0aa799bafd0f04c28c";
	private static String applicationKey = "5a264feaa95a348d8fa64bf038d8add50638bdc807f0940e817e1045c518d57d";

	public static void main(String[] args) throws Exception {
		// Create Instance of SAAccess Object
		SAAccess saAccess = new SAAccess(applianceHost, appliancePort,
				applianceSSL, realm, applicationID, applicationKey);
		ResponseObject ro = saAccess.adaptiveAuthQuery(user, "192.168.2.192");
		System.out.println(ro);
	}
}
