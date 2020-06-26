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
import org.secureauth.sarestapi.data.Response.GroupAssociationResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.UserProfile.UserToGroups;
import org.secureauth.sarestapi.data.UserProfile.UsersToGroup;
import org.secureauth.sarestapi.queries.IDMQueries;
import org.secureauth.sarestapi.queries.StatusQuery;
import org.secureauth.sarestapi.resources.SAExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SAAccessTest {

	private static Logger logger = LoggerFactory.getLogger(SAAccessTest.class);

	@Mock
	public SAExecuter mockedSAExecuter;

	private final static String realm = "realm1";
	private final static String applicationID = "000860c9324d4c79817d1edeeecce8ff";
	private final static String applicationKey = "e29e3f4f8a3150f579ea14bde65c9012aac65859ee1404d70c0ed655842b194b";
	private final static String host = "foobar.com";
	private final static String port = "443";
	private final static String userId = "foobar";
	private final static String passCode = "foobar";
	private static SAAuth saAuth;
	private static ISAAccess saAccess;
	private static SABaseURL saBaseURL;


	@Before
	public void setup() {
		saAuth = new SAAuth(applicationID, applicationKey, realm);
		saBaseURL = new SABaseURL(host, port, true);
		saAccess = new SAAccess(saBaseURL, saAuth, mockedSAExecuter);
	}

	@Test
	public void getUserStatusWhenInvalidUser() throws Exception {

		String query = StatusQuery.queryStatus(saAuth.getRealm(), userId);

		BaseResponse invalidResponse = BaseResponseUtils.invalidUserResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), eq(saBaseURL.getApplianceURL() + query), any(), any()))
				.thenReturn(invalidResponse);

		BaseResponse response = saAccess.getUserStatus(userId);

		Assert.assertEquals(invalidResponse, response);

	}

	@Test
	public void getUserStatusWhenValidUser() throws Exception {

		String query = StatusQuery.queryStatus(saAuth.getRealm(), userId);

		BaseResponse validUserResponse = BaseResponseUtils.validUserResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), eq(saBaseURL.getApplianceURL() + query), any(), any()))
				.thenReturn(validUserResponse);

		BaseResponse response = saAccess.getUserStatus(userId);

		Assert.assertEquals(validUserResponse, response);

	}

	@Test
	public void setUserStatusWhenValidStatus() throws Exception {
		String query = StatusQuery.queryStatus(saAuth.getRealm(), userId);
		String status = "new status";

		BaseResponse successResponse = BaseResponseUtils.successResponse(userId);
		//when
		when(mockedSAExecuter.executePostRawRequest(any(), eq(saBaseURL.getApplianceURL() + query),
				any(), any(), any())).thenReturn(successResponse);

		BaseResponse response = saAccess.setUserStatus(userId, status);

		Assert.assertEquals(successResponse, response);
	}

	@Test
	public void addUserToGroupWhenValid() throws Exception {
		String groupName = "newGroup";

		ResponseObject validResponse = BaseResponseUtils.validResponse();
		//when
		when(mockedSAExecuter.executeSingleUserToSingleGroup(any(),
				eq(saBaseURL.getApplianceURL() + IDMQueries.queryUserToGroup(saAuth.getRealm(), userId, groupName)),
				any(), eq(ResponseObject.class))).thenReturn(validResponse);

		ResponseObject response = saAccess.addUserToGroup(userId, groupName);

		Assert.assertEquals(validResponse, response);
	}

	@Test
	public void addUsersToGroupWhenValid() throws Exception {

		String groupName = "newGroup", groupName2 = "otherGroup";

		GroupAssociationResponse validResponse = BaseResponseUtils.validGroupAssociationResponse();

		UsersToGroup usersToGroup = new UsersToGroup(new String[]{groupName, groupName2});
		//when
		when(mockedSAExecuter.executeGroupToUsersRequest(any(),
				eq(saBaseURL.getApplianceURL() + IDMQueries.queryGroupToUsers(saAuth.getRealm(), groupName)),
				eq(usersToGroup), any(), eq(GroupAssociationResponse.class))).thenReturn(validResponse);

		GroupAssociationResponse response = saAccess.addUsersToGroup(usersToGroup, groupName);

		Assert.assertEquals(validResponse, response);
	}

	@Test
	public void addGroupToUserWhenValid() throws Exception {
		String groupName = "newGroup";

		GroupAssociationResponse validResponse = BaseResponseUtils.validGroupAssociationResponse();

		UsersToGroup usersToGroup = new UsersToGroup(new String[]{groupName});
		//when
		when(mockedSAExecuter.executeSingleGroupToSingleUser(any(),
				eq(saBaseURL.getApplianceURL() + IDMQueries.queryGroupToUser(saAuth.getRealm(), userId, groupName)), any(),
				eq(GroupAssociationResponse.class))).thenReturn(validResponse);

		GroupAssociationResponse response = saAccess.addGroupToUser(groupName, userId);

		Assert.assertEquals(validResponse, response);
	}

	@Test
	public void addUserToGroupsWhenValid() throws Exception {
		String groupName = "newGroup";

		GroupAssociationResponse validResponse = BaseResponseUtils.validGroupAssociationResponse();

		UserToGroups usersToGroup = new UserToGroups(new String[]{groupName});
		//when
		when(mockedSAExecuter.executeUserToGroupsRequest(any(), eq(saBaseURL.getApplianceURL() + IDMQueries.queryUserToGroups(saAuth.getRealm(),userId)),
				eq(usersToGroup), any(), eq(GroupAssociationResponse.class))).thenReturn(validResponse);

		GroupAssociationResponse response = saAccess.addUserToGroups(userId, usersToGroup);

		Assert.assertEquals(validResponse, response);
	}
}