package org.secureauth.sarestapi.queries;

import org.secureauth.sarestapi.resources.Resource;

public final class ThrottleQuery {

	private ThrottleQuery(){}

	public static String queryThrottles(String realm, String userName){
		return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_THROTTLE;
	}

// This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
	public static String queryThrottlesQP(String realm){
		return realm + removeLastChar(Resource.APPLIANCE_USERS) +  Resource.APPLIANCE_THROTTLE;
	}

	private static String removeLastChar (String query){
		return query.substring(0, query.length()-1);
	}

}
