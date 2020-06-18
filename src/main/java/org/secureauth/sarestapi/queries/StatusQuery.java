package org.secureauth.sarestapi.queries;

import org.secureauth.sarestapi.resources.Resource;

/**
 *
 */
public class StatusQuery {

	/**
	 *
	 * @param realm
	 * @param userName
	 * @return
	 */
	public static String queryStatus(String realm, String userName){
		return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_STATUS;
	}
}
