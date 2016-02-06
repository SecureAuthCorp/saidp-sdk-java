package org.secureauth.restapi.examples;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.ResponseObject;

public class AdaptiveAuth {

	// Define our User Variables
	private static String user = "user";

	// Required for connectivity to Appliance
	private static String applianceHost = "host.example.com";
	private static String appliancePort = "443";
	private static boolean applianceSSL = true;
	private static String realm = "secureauth11";
	private static String applicationID = "...........";
	private static String applicationKey = "............";

	public static void main(String[] args) throws Exception {
		// Create Instance of SAAccess Object
		SAAccess saAccess = new SAAccess(applianceHost, appliancePort,
				applianceSSL, realm, applicationID, applicationKey);
		ResponseObject ro = saAccess.adaptiveAuthQuery(user, "192.168.2.192");
		System.out.println(ro);
	}
}
