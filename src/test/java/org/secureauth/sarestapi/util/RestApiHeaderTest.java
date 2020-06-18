package org.secureauth.sarestapi.util;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.secureauth.sarestapi.data.Requests.StatusRequest;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.queries.StatusQuery;
import org.secureauth.sarestapi.resources.Resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RestApiHeaderTest {

	private final static String realm = "realm1";
	private final static String applicationID = "applicationID";
	private final static String applicationKey = "applicationKey";
	private static SAAuth saAuth;

	@Before
	public void setup() {
		saAuth = new SAAuth(applicationID,applicationKey,realm);
	}

	@Test
	void getAuthorizationHeaderWithoutPayload() {
		String query = StatusQuery.queryStatus(saAuth.getRealm(), "userId");

		String header = RestApiHeader.getAuthorizationHeader(saAuth, Resource.METHOD_GET, query, getServerTime());

		assertEquals(header, "Basic YXBwbGljYXRpb25JRDo=");
	}

	@Test
	void testGetAuthorizationHeaderWithPayload() {
		StatusRequest statusRequest = new StatusRequest("some status");

		String query = StatusQuery.queryStatus(saAuth.getRealm(), "userId");

		String header = RestApiHeader.getAuthorizationHeader(saAuth, Resource.METHOD_POST, query, statusRequest, getServerTime());

		assertEquals(header, "Basic YXBwbGljYXRpb25JRDo=");
	}


	private String getServerTime() {
		LocalDateTime fixLocalDateTime = LocalDateTime.of(2020, 6, 12, 0,0);
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		return formatter.format(fixLocalDateTime);
	}
}