package org.secureauth.sarestapi.queries;

import org.secureauth.sarestapi.resources.Resource;

public class ThrottleQuery {

	public static String queryThrottles(String realm, String userName){
		return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_THROTTLE;
	}

// This method supports special characters.
	public static String queryThrottlesWithSpecialCharacters(String realm){
		return realm + Resource.APPLIANCE_USERS_WITH_SPECIAL_CHARACTERS +  Resource.APPLIANCE_THROTTLE;
	}

}
