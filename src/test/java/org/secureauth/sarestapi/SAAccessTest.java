package org.secureauth.sarestapi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.secureauth.sarestapi.data.Requests.StatusRequest;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.UserProfileResponse;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.queries.AuthQuery;
import org.secureauth.sarestapi.queries.StatusQuery;
import org.secureauth.sarestapi.resources.SAExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SAAccessTest {

	private static Logger logger = LoggerFactory.getLogger(SAAccessTest.class);
	final private static String PROPERTIES_FILE_PATH="test.properties";

	//User
	private static String VALID_USERNAME;
	private static String VALID_PIN;
	private static String VALID_PASSWORD;
	private static final String UNEXISTING_USERNAME = "unexisting-user";

	//User OATH-OTP
	private static String VALID_FACTOR_ID_FOR_OATH_OTP;

	//Response messages
	private static final String FOUND_MESSAGE = "found";
	private static final String NOT_FOUND_MESSAGE = "not_found";

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
	private static SAAccess saAccess;
	private static SABaseURL saBaseURL;

	@Before
	public void setup() {
		saAuth = new SAAuth(applicationID,applicationKey,realm);
		saBaseURL = new SABaseURL(host, port,true);
		saAccess = new SAAccess(saBaseURL, saAuth, mockedSAExecuter);

		Properties properties = new Properties();
		try (InputStream inputStream = SAAccessTest.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH)) {
			properties.load(inputStream);

			VALID_USERNAME = properties.getProperty("user.username");
			VALID_PIN = properties.getProperty("user.pin");
			VALID_PASSWORD = properties.getProperty("user.password");

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot load " + PROPERTIES_FILE_PATH + ": " + e.getMessage());
		}
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

		BaseResponse validUserResponse = validUserResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), eq(saBaseURL.getApplianceURL() + query), any(), any()))
				.thenReturn(validUserResponse);

		BaseResponse response = saAccess.getUserStatus(userId);

		Assert.assertEquals(validUserResponse, response);

	}

	@Test
	public void setUserStatus() throws Exception {
		String query = StatusQuery.queryStatus(saAuth.getRealm(), userId);
		String status = "new status";

		BaseResponse successResponse = successResponse(userId);
		//payload
		StatusRequest statusRequestPayload = new StatusRequest(status);
		//when
		//header,saBaseURL.getApplianceURL() + query, statusRequestPayload, BaseResponse.class, ts
		when(mockedSAExecuter.executePutRequest(any(), eq(saBaseURL.getApplianceURL() + query),
				any(), any(), any()))
				.thenReturn(successResponse);

		BaseResponse response = saAccess.setUserStatus(userId, status);

		Assert.assertEquals(successResponse, response);
	}

	@Test
	public void testUserPINWithInvalidStrings() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "invalid",
			  "message" : "PIN is invalid."
			}
		 */
		String invalidStringForPin = "invalid";

		BaseResponse validUserInvalidAnswerResponse = validUserInvalidAnswerResponse(userId);
		//when
		when(mockedSAExecuter.executeValidateUserPin(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validUserInvalidAnswerResponse);


		BaseResponse response = saAccess.validateUserPin(VALID_USERNAME, invalidStringForPin);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), invalidStringForPin);
		assertTrue(response.getMessage().contains("PIN is invalid."));
	}

	@Test
	public void testUserPINWithValidValues() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "valid",
			  "message" : ""
			}
		 */
		BaseResponse validUserResponseWithOuthMessage = validUserResponseWithOuthMessage(userId);
		//when
		when(mockedSAExecuter.executeValidateUserPin(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validUserResponseWithOuthMessage);

		BaseResponse response = saAccess.validateUserPin(VALID_USERNAME, VALID_PIN);
		System.out.println("response: " + response.toString());
		assertNotNull(response);
		assertEquals("valid", response.getStatus());
		assertTrue(response.getMessage().isEmpty());

	}

	@Test
	public void testGetPropertiesWithValidUser() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "found",
			  "message" : "",
			  "userId" : "jsmith",
			  "properties" : {
			    "firstName" : {
			      "value" : "John",
			      "isWritable" : "false"
			    },
			    "lastName" : {
			      "value" : "Smith",
			      "isWritable" : "false"
			    },
			    "email1" : {
			      "value" : "jsmith@secureauth.com",
			      "isWritable" : "false"
			    }
			  },
			  "knowledgeBase" : { },
			  "groups" : [ ],
			  "accessHistories" : [ {
			    "userAgent" : "Jersey/2.5.1 (HttpUrlConnection 1.8.0_202)",
			    "ipAddress" : "11.1.1.2",
			    "timeStamp" : "2019-07-12T18:13:17.921501Z",
			    "authState" : "Success"
			  },
			  {...} ]
			}
		 */

		UserProfileResponse userProfileResponse = validUserProfileResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), any(), any(), any()))
				.thenReturn(userProfileResponse);

		UserProfileResponse response = saAccess.getUserProfile(VALID_USERNAME);
		logger.debug("response: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), FOUND_MESSAGE);
		assertTrue(response.getMessage().isEmpty());
	}

	@Test
	public void testGetPropertiesWithUnexistingUser() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "User Id was not found.",
			  "user_id" : "unexisting-user",
			  "properties" : { },
			  "knowledgeBase" : { },
			  "groups" : [ ],
			  "accessHistories" : [ ]
			}
		 */

		UserProfileResponse userProfileResponse = invalidUserProfileResponse(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), any(), any(), any()))
				.thenReturn(userProfileResponse);

		UserProfileResponse response = saAccess.getUserProfile(UNEXISTING_USERNAME);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), NOT_FOUND_MESSAGE);
		assertEquals(response.getMessage(), "User Id was not found.");
	}

	@Test
	public void testValidateOathOTPWithValidCode() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "valid",
			  "message" : ""
			}
		 */
		BaseResponse validUserResponseWithOuthMessage = validUserResponseWithOuthMessage(userId);
		//when
		when(mockedSAExecuter.executeValidateOath(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validUserResponseWithOuthMessage);


		BaseResponse response = saAccess.validateOath(VALID_USERNAME, passCode, VALID_FACTOR_ID_FOR_OATH_OTP);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), "valid");
		assertTrue(response.getMessage().isEmpty());
	}

	@Test
	public void testValidateOathOTPWithInvalidFactorId() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "invalid",
			  "message" : "Request validation failed with: Unknown factor id 'zzzz0000b4504a15acec016d7ef9f42b'."
			}
		 */

		String invalidFactorId = "zzzz0000z0000a00zzzz000z0zz0z00z";
		BaseResponse validUserResponseOtp = invalidUserOTP(userId);
		//when
		when(mockedSAExecuter.executeValidateOath(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validUserResponseOtp);


		BaseResponse response = saAccess.validateOath(VALID_USERNAME, "000123", invalidFactorId);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), "invalid");
		assertEquals(response.getMessage(), "Request validation failed with: Unknown factor id 'zzzz0000z0000a00zzzz000z0zz0z00z'.");
	}

	@Test
	public void testValidateOathOTPWithInvalidCode() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "invalid",
			  "message" : "OTP is invalid."
			}
		 */
		BaseResponse invalidOTP = invalidOTP(userId);
		//when
		when(mockedSAExecuter.executeValidateOath(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(invalidOTP);

		BaseResponse response = saAccess.validateOath(VALID_USERNAME, "000123", VALID_FACTOR_ID_FOR_OATH_OTP);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), "invalid");
		assertEquals(response.getMessage(), "OTP is invalid.");
	}

	@Test
	public void testValidatePasswordWithValidCredentials() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "valid",
			  "message" : ""
			}
		 */

		BaseResponse validUserResponseWithOuthMessage = validUserResponseWithOuthMessage(userId);
		//when
		when(mockedSAExecuter.executeValidateUserPassword(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validUserResponseWithOuthMessage);

		BaseResponse response = saAccess.validateUserPassword(VALID_USERNAME, VALID_PASSWORD);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), "valid");
		assertTrue(response.getMessage().isEmpty());
	}

	@Test
	public void testValidatePasswordWithInvalidPassword() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "invalid",
			  "message" : "User Id or password is invalid."
			}
		 */
		String invalidPassword = "invalidPassword";

		BaseResponse InvalidPassword = InvalidPassword(userId);
		//when
		when(mockedSAExecuter.executeValidateUserPassword(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(InvalidPassword);

		BaseResponse response = saAccess.validateUserPassword(VALID_USERNAME, invalidPassword);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), "invalid");
		assertEquals(response.getMessage(), "User Id or password is invalid.");
	}

	@Test
	public void testValidatePasswordFromUnexistingUser() throws Exception {
		String somePassword = "password";

		BaseResponse invalidUserProfileResponse = invalidUserProfileResponse(userId);
		//when
		when(mockedSAExecuter.executeValidateUserPassword(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(invalidUserProfileResponse);

		BaseResponse response = saAccess.validateUserPassword(UNEXISTING_USERNAME, somePassword);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), NOT_FOUND_MESSAGE);
		assertEquals(response.getMessage(), "User Id was not found.");
	}

	@Test
	public void testGetFactorsFromValidUser() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "found",
			  "message" : "",
			  "user_id" : "jsmith",
			  "factors" : [ {
			    "type" : "email",
			    "id" : "Email1",
			    "value" : "jsmith@secureauth.com"
			  }, {
			    "type" : "push",
			    "id" : "8020890sxt414974b2235e31f4192785",
			    "value" : "SM-J7",
			    "capabilities" : [ "push", "push_accept" ]
			  }, {
			    "type" : "oath",
			    "id" : "7edec5b5e3f553150f1e90261a03b8fd",
			    "value" : "Windows"
			  }, {
			    "type" : "oath",
			    "id" : "6f669edbb4504a15acec016d7ef9f42b",
			    "value" : "SM-J7"
			  } ]
			}
		 */


		FactorsResponse validFactorsFromValidUser = validFactorsFromValidUser(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), any(), any(), any()))
				.thenReturn(validFactorsFromValidUser);

		FactorsResponse response = saAccess.factorsByUser(VALID_USERNAME);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), FOUND_MESSAGE);
		assertTrue(response.getMessage().isEmpty());
	}

	@Test
	public void testGetFactorsFromUnexistingUser() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "User Id was not found.",
			  "user_id" : "unexisting-user",
			  "factors" : [ ]
			}
		 */
		FactorsResponse invalidFactorsFromValidUser = invalidFactorsFromValidUser(userId);
		//when
		when(mockedSAExecuter.executeGetRequest(any(), any(), any(), any()))
				.thenReturn(invalidFactorsFromValidUser);

		FactorsResponse response = saAccess.factorsByUser(UNEXISTING_USERNAME);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), NOT_FOUND_MESSAGE);
		assertEquals(response.getMessage(), "User Id was not found.");
	}

	@Test
	public void testUserPINWithInvalidNumbers() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "invalid",
			  "message" : "PIN is invalid."
			}
		 */
		String invalidNumbersForPin = "000000";
		BaseResponse validUserInvalidAnswerResponse = validUserInvalidAnswerResponse(userId);
		//when
		when(mockedSAExecuter.executeValidateUserPin(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validUserInvalidAnswerResponse);

		BaseResponse response = saAccess.validateUserPin(VALID_USERNAME, invalidNumbersForPin);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), "invalid");
		assertTrue(response.getMessage().contains("PIN is invalid."));
	}

	@Test
	public void testValidateUserWithValidInfo() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "found",
			  "message" : "User Id found"
			}
		 */

		BaseResponse validateUserWithValidInfo = validateUserWithValidInfo(userId);
		//when
		when(mockedSAExecuter.executeValidateUser(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validateUserWithValidInfo);

		BaseResponse response = saAccess.validateUser(VALID_USERNAME);
		System.out.println("BaseResponse: " + response.toString());
		assertNotNull(response);
		assertEquals(response.getStatus(), FOUND_MESSAGE);
		assertEquals(response.getMessage(), "User Id found");
	}

	@Test
	public void testValidateUserNotFound() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "User Id was not found.",
			  "user_id" : "unexisting-user"
			}
		 */
		BaseResponse validateUserNotFound = validateUserNotFound(userId);
		//when
		when(mockedSAExecuter.executeValidateUser(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validateUserNotFound);

		BaseResponse response = saAccess.validateUser(UNEXISTING_USERNAME);
		assertNotNull(response);
		assertEquals(response.getStatus(), NOT_FOUND_MESSAGE);
		assertEquals(response.getMessage(), "User Id was not found.");
	}
	@Test
	public void testValidateUserWithInvalidKey() throws Exception{
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "The requested resource cannot be found."
			}
		 */
		BaseResponse validateUserWithInvalidKey = validateUserWithInvalidKey(userId);
		//when
		when(mockedSAExecuter.executeValidateUser(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validateUserWithInvalidKey);

		BaseResponse response = saAccess.validateUser(VALID_USERNAME);
		assertNotNull(response);
		assertEquals("invalid", response.getStatus());
		assertTrue(response.getMessage().contains("Invalid credentials."));
	}

	@Test
	public void testValidateUserWithInvalidID() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "The requested resource cannot be found."
			}
		 */
		BaseResponse validateUserWithInvalidID = validateUserWithInvalidID(userId);
		//when
		when(mockedSAExecuter.executeValidateUser(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validateUserWithInvalidID);

		BaseResponse response = saAccess.validateUser(VALID_USERNAME);
		assertNotNull(response);
		assertEquals("invalid", response.getStatus());
		assertTrue(response.getMessage().contains("AppId is unknown."));
	}

	@Test
	//@Ignore("Slow test")
	public void testValidateUserWithInvalidHost() throws Exception  {
		BaseResponse validateUserWithInvalidHost = validateUserWithInvalidHost(userId);
		//when
		when(mockedSAExecuter.executeValidateUser(any(), eq(saBaseURL.getApplianceURL() + AuthQuery.queryAuth(saAuth.getRealm())), any(), any()))
				.thenReturn(validateUserWithInvalidHost);

		BaseResponse response = saAccess.validateUser(VALID_USERNAME);
		assertNull("Invalid host returns null response", response);
	}

	public BaseResponse invalidUserResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id not found.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("not_found");
		return invalidResponse;
	}

	public BaseResponse validUserResponse(String userId){
		BaseResponse validResponse = new BaseResponse();
		validResponse.setMessage("User Id not found.");
		validResponse.setUser_id(userId);
		validResponse.setStatus("valid");
		return validResponse;
	}

	public BaseResponse successResponse(String userId){
		BaseResponse validResponse = new BaseResponse();
		validResponse.setMessage("User Status update complete");
		validResponse.setUser_id(userId);
		validResponse.setStatus("success");
		return validResponse;
	}

	public BaseResponse validUserInvalidAnswerResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("PIN is invalid.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public BaseResponse validUserResponseWithOuthMessage(String userId){
		BaseResponse validResponse = new BaseResponse();
		validResponse.setMessage("");
		validResponse.setUser_id(userId);
		validResponse.setStatus("valid");
		return validResponse;
	}

	public UserProfileResponse validUserProfileResponse(String userId){
		UserProfileResponse validResponse = new UserProfileResponse();
		validResponse.setStatus("found");
		validResponse.setMessage("");
		return validResponse;
	}

	public UserProfileResponse invalidUserProfileResponse(String userId){
		UserProfileResponse invalidResponse = new UserProfileResponse();
		invalidResponse.setStatus("not_found");
		invalidResponse.setMessage("User Id was not found.");
		return invalidResponse;
	}

	public BaseResponse invalidUserOTP(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("Request validation failed with: Unknown factor id 'zzzz0000z0000a00zzzz000z0zz0z00z'.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public BaseResponse invalidOTP(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("OTP is invalid.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public BaseResponse InvalidPassword(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id or password is invalid.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public FactorsResponse validFactorsFromValidUser(String userId){
		FactorsResponse validFactorsFromValidUser = new FactorsResponse();
		validFactorsFromValidUser.setStatus("found");
		validFactorsFromValidUser.setMessage("");
		return validFactorsFromValidUser;
	}

	public FactorsResponse invalidFactorsFromValidUser(String userId){
		FactorsResponse invalidFactorsFromValidUser = new FactorsResponse();
		invalidFactorsFromValidUser.setStatus("not_found");
		invalidFactorsFromValidUser.setMessage("User Id was not found.");
		return invalidFactorsFromValidUser;
	}

	public FactorsResponse validateUserWithValidInfo(String userId){
		FactorsResponse testValidateUserWithValidInfo = new FactorsResponse();
		testValidateUserWithValidInfo.setStatus("found");
		testValidateUserWithValidInfo.setMessage("User Id found");
		return testValidateUserWithValidInfo;
	}

	public BaseResponse validateUserNotFound(String userId){
		BaseResponse validateUserNotFound = new BaseResponse();
		validateUserNotFound.setStatus("not_found");
		validateUserNotFound.setMessage("User Id was not found.");
		return validateUserNotFound;
	}

	public BaseResponse validateUserWithInvalidKey(String userId){
		BaseResponse validateUserWithInvalidKey = new BaseResponse();
		validateUserWithInvalidKey.setStatus("invalid");
		validateUserWithInvalidKey.setMessage("Invalid credentials.");
		return validateUserWithInvalidKey;
	}

	public BaseResponse validateUserWithInvalidID(String userId){
		BaseResponse validateUserWithInvalidID = new BaseResponse();
		validateUserWithInvalidID.setStatus("invalid");
		validateUserWithInvalidID.setMessage("AppId is unknown.");
		return validateUserWithInvalidID;
	}

	public BaseResponse validateUserWithInvalidHost(String userId){
		BaseResponse validateUserWithInvalidHost = new BaseResponse();
		return null;
	}
}