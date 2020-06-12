package org.secureauth.sarestapi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.queries.StatusQuery;
import org.secureauth.sarestapi.resources.SAExecuter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SAAccessTest {

	@Mock
	public SAExecuter mockedSAExecuter;

	private final static String realm = "realm1";
	private final static String applicationID = "applicationID";
	private final static String applicationKey = "applicationKey";
	private final static String host = "foobar.com";
	private final static String port = "443";
	private final static String userId = "foobar";
	private static SAAuth saAuth;
	private static SAAccess saAccess;
	private static SABaseURL saBaseURL;


	@Before
	public void setup() {

		saAuth = new SAAuth(applicationID,applicationKey,realm);
		saBaseURL = new SABaseURL(host, port,true);
		saAccess = new SAAccess(saBaseURL, saAuth, mockedSAExecuter);
	}

	@Test
	public void getUserStatusWhenInvalidUser() throws Exception {

		String query = StatusQuery.queryStatus(saAuth.getRealm(), userId);

		BaseResponse invalidResponse = invalidUserResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), eq(saBaseURL.getApplianceURL() + query), any(), any()))
				.thenReturn(invalidResponse);

		BaseResponse response = saAccess.getUserStatus(userId);

		Assert.assertEquals(invalidResponse, response);

	}

	@Test
	public void getUserStatusWhenValidUser() throws Exception {

		String query = StatusQuery.queryStatus(saAuth.getRealm(), userId);

		BaseResponse invalidResponse = validUserResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), eq(saBaseURL.getApplianceURL() + query), any(), any()))
				.thenReturn(invalidResponse);

		BaseResponse response = saAccess.getUserStatus(userId);

		Assert.assertEquals(invalidResponse, response);

	}

	public void setUserStatus() {

	}

	public BaseResponse invalidUserResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id not found.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("not_found");
		return invalidResponse;
	}

	public BaseResponse validUserResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id not found.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("valid");
		return invalidResponse;
	}
}