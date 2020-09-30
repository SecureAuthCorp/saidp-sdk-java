package org.secureauth.sarestapi.queries;

import org.secureauth.sarestapi.resources.Resource;

/**
 *
 */
public class StatusQuery {

	public static String queryStatus(String realm, String userName){
		return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_STATUS;
	}

//	This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
	public static String queryStatusQP(String realm){
		return realm + Resource.APPLIANCE_USERS + Resource.APPLIANCE_STATUS;
	}
}
