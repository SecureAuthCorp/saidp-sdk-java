package org.secureauth.sarestapi.util;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.resources.SAExecuter;

public final class SAFactory {

	private SAFactory(){}

	private static SAAccess saAccess;

	/**
	 *<p>
	 *     Returns a SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *</p>
	 * @param host FQDN of the SecureAuth Appliance
	 * @param port The port used to access the web application on the Appliance.
	 * @param ssl Use SSL
	 * @param realm the Configured Realm that enables the RESTApi
	 * @param applicationID The Application ID from the Configured Realm
	 * @param applicationKey The Application Key from the Configured Realm
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess singletonOf(String host, String port, boolean ssl, String realm, String applicationID, String applicationKey){
		if(saAccess == null){
			SABaseURL saBaseURL =new SABaseURL(host,port,ssl);
			SAAuth saAuth = new SAAuth(applicationID,applicationKey,realm);
			SAExecuter saExecuter = new SAExecuter(saBaseURL);
			saAccess = new SAAccess(saBaseURL, saAuth, saExecuter);
		}
		return saAccess;
	}

	/**
	 *<p>
	 *     Returns a SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *     This Object will allow users to support selfSigned Certificates
	 *</p>
	 * @param host FQDN of the SecureAuth Appliance
	 * @param port The port used to access the web application on the Appliance.
	 * @param ssl Use SSL
	 * @param selfSigned  Support for SeflSigned Certificates. Setting to enable disable self signed cert support
	 * @param realm the Configured Realm that enables the RESTApi
	 * @param applicationID The Application ID from the Configured Realm
	 * @param applicationKey The Application Key from the Configured Realm
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess of(String host, String port,boolean ssl,boolean selfSigned, String realm, String applicationID, String applicationKey){
		if(saAccess == null){
			SABaseURL saBaseURL =new SABaseURL(host,port,ssl, selfSigned);
			SAAuth saAuth = new SAAuth(applicationID,applicationKey,realm);
			SAExecuter saExecuter = new SAExecuter(saBaseURL);
			saAccess = new SAAccess(saBaseURL, saAuth, saExecuter);
		}
		return saAccess;
	}
	
	/**
	 *<p>
	 *	   Singleton variant option.
	 *     Returns a SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *     This Object will allow users to support selfSigned Certificates
	 *</p>
	 * @param host FQDN of the SecureAuth Appliance
	 * @param port The port used to access the web application on the Appliance.
	 * @param ssl Use SSL
	 * @param selfSigned  Support for SeflSigned Certificates. Setting to enable disable self signed cert support
	 * @param realm the Configured Realm that enables the RESTApi
	 * @param applicationID The Application ID from the Configured Realm
	 * @param applicationKey The Application Key from the Configured Realm
	 * @param transactionId Transaction Id for trceability between components.
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess of(String host, String port,boolean ssl,boolean selfSigned, String realm,
			String applicationID, String applicationKey, String transactionId){
		if(saAccess == null){
			SABaseURL saBaseURL =new SABaseURL(host,port,ssl, selfSigned);
			SAAuth saAuth = new SAAuth(applicationID,applicationKey,realm);
			SAExecuter saExecuter = new SAExecuter(saBaseURL, transactionId);
			saAccess = new SAAccess(saBaseURL, saAuth, saExecuter);
		}
		return saAccess;
	}
	
	/**
	 *<p>
	 *     Returns a newly created SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *     This Object will allow users to support selfSigned Certificates
	 *</p>
	 * @param host FQDN of the SecureAuth Appliance
	 * @param port The port used to access the web application on the Appliance.
	 * @param ssl Use SSL
	 * @param selfSigned  Support for SeflSigned Certificates. Setting to enable disable self signed cert support
	 * @param realm the Configured Realm that enables the RESTApi
	 * @param applicationID The Application ID from the Configured Realm
	 * @param applicationKey The Application Key from the Configured Realm
	 * @param transactionId Transaction Id for trceability between components.
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess newOf(String host, String port,boolean ssl,boolean selfSigned, String realm,
		String applicationID, String applicationKey, String transactionId){
		SABaseURL saBaseURL =new SABaseURL(host,port,ssl, selfSigned);
		SAAuth saAuth = new SAAuth(applicationID,applicationKey,realm);
		SAExecuter saExecuter = new SAExecuter(saBaseURL, transactionId);
		return new SAAccess(saBaseURL, saAuth, saExecuter);
	}
	
	/** Singleton variant option
	 *<p>
	 *     Returns a SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *     This Object will allow users to support selfSigned Certificates
	 *</p>
	 * @param host FQDN of the SecureAuth Appliance
	 * @param port The port used to access the web application on the Appliance.
	 * @param ssl Use SSL
	 * @param selfSigned  Support for SeflSigned Certificates. Setting to enable disable self signed cert support
	 * @param realm the Configured Realm that enables the RESTApi
	 * @param applicationID The Application ID from the Configured Realm
	 * @param applicationKey The Application Key from the Configured Realm
	 * @param transactionId Transaction Id for trceability between components.
	 * @param timeoutInMillis Configure SAAccess Timeout in Milliseconds
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess of(String host, String port,boolean ssl,boolean selfSigned, String realm,
			String applicationID, String applicationKey, String transactionId, int timeoutInMillis){
		if(saAccess == null){
			SABaseURL saBaseURL =new SABaseURL(host,port,ssl, selfSigned);
			SAAuth saAuth = new SAAuth(applicationID,applicationKey,realm);
			SAExecuter saExecuter = new SAExecuter(saBaseURL, transactionId, timeoutInMillis);
			saAccess = new SAAccess(saBaseURL, saAuth, saExecuter);
		}
		return saAccess;
	}
	
	/**
	 *<p>
	 *     Returns a newly created SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *     This Object will allow users to support selfSigned Certificates
	 *</p>
	 * @param host FQDN of the SecureAuth Appliance
	 * @param port The port used to access the web application on the Appliance.
	 * @param ssl Use SSL
	 * @param selfSigned  Support for SeflSigned Certificates. Setting to enable disable self signed cert support
	 * @param realm the Configured Realm that enables the RESTApi
	 * @param applicationID The Application ID from the Configured Realm
	 * @param applicationKey The Application Key from the Configured Realm
	 * @param transactionId Transaction Id for trceability between components.
	 * @param timeoutInMillis Configure SAAccess Timeout in Milliseconds
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess newOf(String host, String port,boolean ssl,boolean selfSigned, String realm,
			String applicationID, String applicationKey, String transactionId, int timeoutInMillis){

		SABaseURL saBaseURL =new SABaseURL(host,port,ssl, selfSigned);
		SAAuth saAuth = new SAAuth(applicationID,applicationKey,realm);
		SAExecuter saExecuter = new SAExecuter(saBaseURL, transactionId, timeoutInMillis);
		saAccess = new SAAccess(saBaseURL, saAuth, saExecuter);
		return saAccess;
	}

	/**
	 *<p>
	 *     Returns a SAAccess Object that can be used to query the SecureAuth Rest API
	 *     This should be the default object used when setting up connectivity to the SecureAuth Appliance
	 *     This Object will allow users to support selfSigned Certificates
	 *</p>
	 * @param saBaseURL {@link org.secureauth.sarestapi.data.SABaseURL}
	 * @param saAuth {@link org.secureauth.sarestapi.data.SAAuth}
	 * @param saExecuter {@link org.secureauth.sarestapi.resources.SAExecuter}
	 *
	 * @return saAccess The created access
	 */
	public static synchronized SAAccess of(SABaseURL saBaseURL, SAAuth saAuth, SAExecuter saExecuter){
		if(saAccess == null){
			saAccess = new SAAccess(saBaseURL, saAuth, saExecuter);
		}
		return saAccess;
	}

}
