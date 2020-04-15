package org.secureauth.sarestapi.queries;

import org.secureauth.sarestapi.resources.Resource;

public class ThrottleQuery {

	public static String queryThrottles(String realm, String userName){
		return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_THROTTLE;
	}

}
