package org.secureauth.restapi.test;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.secureauth.sarestapi.ISAAccess;
import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.DFPValidateResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.UserProfileResponse;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.resources.SAExecuter;
import org.secureauth.sarestapi.util.Property;
import org.secureauth.sarestapi.util.RetrievePropertiesUtils;
import org.secureauth.sarestapi.util.SAFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SAAccessTDD {

	private static Logger logger = LoggerFactory.getLogger(SAAccessTDD.class);

	private static SAAuth saAuth;
	private static ISAAccess saAccess;
	private static SABaseURL saBaseURL;
	private static SAExecuter saExecuter;

	private static String realm;
	private static String apiApplicationId;
	private static String apiApplicationKey;
	private static String host;
	private static String port;
	private static Boolean ssl;

	//User
	private static String validUsername;
	private static String validPin;
	private static String validPassword;
	private final static String UNEXISTING_USERNAME = "unexisting-user";

	//DFP
	private static String validFingerprintId;
	private static String validHostAddress;


	//Response messages
	private static final String FOUND_MESSAGE = "found";
	private static final String NOT_FOUND_MESSAGE = "not_found";

	//User OATH-OTP
	private static String validFactorIdForOathOtp;
	private static String validUserOtp;
	private static String validUserOtpOath;
	private static RetrievePropertiesUtils retrievePropertiesUtils;

	private static Boolean assumeTest;

	@Before
	public void setup() {
		setupStrings();
		Assume.assumeTrue(assumeTest);
		saAuth = new SAAuth(apiApplicationId, apiApplicationKey, realm);
		saBaseURL = new SABaseURL(host, port, ssl, true);
		saExecuter = new SAExecuter(saBaseURL);
		saAccess = SAFactory.of(saBaseURL, saAuth, saExecuter);
	}


	private void setupStrings(){
		retrievePropertiesUtils = new RetrievePropertiesUtils();
		apiApplicationId = getValue(Property.API_APPLICATION_ID);
		apiApplicationKey = getValue(Property.API_APPLICATION_KEY);
		realm = getValue(Property.REALM);
		host = getValue(Property.HOST);
		port = getValue(Property.PORT);
		ssl = Boolean.valueOf(getValue(Property.SSL));
		validUsername = getValue(Property.VALID_USERNAME);
		validPin = getValue(Property.VALID_PIN);
		validPassword = getValue(Property.VALID_PASSWORD);
		validFactorIdForOathOtp = getValue(Property.VALID_FACTOR_ID_FOR_OATH_OTP);
		validUserOtp = getValue(Property.VALID_OTP_PIN_CODE);
		validUserOtpOath = getValue(Property.VALID_OTP_OATH_CODE);
		validHostAddress = getValue(Property.VALID_HOST_ADDRESS);
		validHostAddress = getValue(Property.VALID_HOST_ADDRESS);
		validFingerprintId = getValue(Property.VALID_FINGERPRINT_ID);
 		assumeTest = Boolean.valueOf(getValue(Property.ASSUME_TEST));

	}

	private String getValue(Property property){
		return retrievePropertiesUtils.getValueFromProperty(property);
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
		BaseResponse response = saAccess.validateUserPin(validUsername, validPin);
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

		UserProfileResponse response = saAccess.getUserProfile(validUsername);
		assertNotNull(response);
		assertEquals(FOUND_MESSAGE, response.getStatus());
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

		UserProfileResponse response = saAccess.getUserProfile(UNEXISTING_USERNAME);
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

		BaseResponse response = saAccess.validateOath(validUsername, validUserOtpOath, validFactorIdForOathOtp);
		assertNotNull(response);
		assertEquals("valid", response.getStatus());
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

		BaseResponse response = saAccess.validateOath(validUsername, validUserOtp, invalidFactorId);
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

		BaseResponse response = saAccess.validateOath(validUsername, validUserOtp, validFactorIdForOathOtp);
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

		BaseResponse response = saAccess.validateUserPassword(validUsername, validPassword);
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

		BaseResponse response = saAccess.validateUserPassword(validUsername, invalidPassword);
		assertNotNull(response);
		assertEquals(response.getStatus(), "invalid");
		assertEquals(response.getMessage(), "User Id or password is invalid.");
	}

	@Test
	public void testValidatePasswordFromUnexistingUser() throws Exception {
		String somePassword = "password";

		BaseResponse response = saAccess.validateUserPassword(UNEXISTING_USERNAME, somePassword);
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

		FactorsResponse response = saAccess.factorsByUser(validUsername);
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

		FactorsResponse response = saAccess.factorsByUser(UNEXISTING_USERNAME);
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

		BaseResponse response = saAccess.validateUserPin(validUsername, invalidNumbersForPin);
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

		BaseResponse response = saAccess.validateUser(validUsername);
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

		SAAuth invalidAuth = new SAAuth(apiApplicationId, "de141d3f532be6035c3206083df96c8d1b645220094705aaa3ff9765b0a1a81e", realm);
		SAAccess invalidAuthAccess = new SAAccess(saBaseURL, invalidAuth, saExecuter);
		BaseResponse response = invalidAuthAccess.validateUser(validUsername);
		assertNotNull(response);
		assertEquals("invalid", response.getStatus());
		assertTrue(response.getMessage().contains("Invalid credentials."));
	}

	@Test
	public void testValidateUserWithValidID() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "The requested resource cannot be found."
			}
		 */

		SAAuth invalidAuth = new SAAuth("invalidID", apiApplicationKey, realm);
		SAAccess invalidAuthAccess = new SAAccess(saBaseURL, invalidAuth, saExecuter);
		BaseResponse response = invalidAuthAccess.validateUser(validUsername);
		assertNotNull(response);
		assertEquals("invalid", response.getStatus());
		assertTrue(response.getMessage().contains("AppId is unknown."));
	}

	@Test
	//@Ignore("Slow test")
	public void testValidateUserWithInvalidHost() throws Exception  {
		SABaseURL invalidBase = new SABaseURL("invalidHost", port, ssl, true);
		SAAccess invalidAuthAccess = new SAAccess(invalidBase, saAuth, saExecuter);
		BaseResponse response = invalidAuthAccess.validateUser(validUsername);
		assertNull("Already connected", response);
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

		BaseResponse response = saAccess.validateUserPin(validUsername, invalidStringForPin);
		assertNotNull(response);
		assertEquals(response.getStatus(), invalidStringForPin);
		assertTrue(response.getMessage().contains("PIN is invalid."));
	}

	@Test
	public void testDFPScoreWithValidNotFound() throws Exception {
		/*
		 * Response would return:
			{
			    "fingerprint_id": "",
			    "fingerprint_name": "",
			    "score": "0.00",
			    "match_score": "0.00",
			    "update_score": "0.00",
			    "status": "not_found",
			    "message": ""
			 }
		 */
		String emptyFingerprintJSON = "{}";

		DFPValidateResponse response = saAccess.DFPScoreFingerprint(validUsername, validHostAddress, validFingerprintId, emptyFingerprintJSON);
		assertNotNull(response);
		assertEquals("not_found", response.getStatus());
		assertEquals(0.0, response.getScore(), 0.1);
		assertEquals(0.0, response.getUpdate_score(), 0.1);
	}

	@Test
	public void testDFPSaveWithValidNotFound() throws Exception {
		/*
		 * Response would return:
			{
			    "fingerprint_id": "",
			    "fingerprint_name": "",
			    "score": "0.00",
			    "match_score": "0.00",
			    "update_score": "0.00",
			    "status": "not_found",
			    "message": ""
			 }
		 */
		String emptyFingerprintJSON = "{}";

		DFPValidateResponse response = saAccess.DFPSaveFingerprint(validUsername, validHostAddress, validFingerprintId, emptyFingerprintJSON);
		assertNotNull(response);
		assertEquals("not_found", response.getStatus());
		assertEquals(0.0, response.getScore(), 0.1);
		assertEquals(0.0, response.getUpdate_score(), 0.1);
		assertEquals("", response.getFingerprintId());
		assertEquals("", response.getFingerprintName());
	}

	@Test
	public void testNotifySuccessAuthenticatedResult() {
		BaseResponse response = saAccess.notifyAuthenticated( validUsername, "success", "EMAIL" );
		assertEquals( "Request has been processed.", response.getMessage() );
	}

	@Test
	public void testNotifyAbortedAuthenticatedResult() {
		BaseResponse response = saAccess.notifyAuthenticated( validUsername, "aborted", "EMAIL" );
		assertEquals( "Request has been processed.", response.getMessage() );
	}

	@Test
	public void testNotifyCancelledAuthenticatedResult() {
		BaseResponse response = saAccess.notifyAuthenticated( validUsername, "cancelled", "EMAIL" );
		assertEquals( "Request has been processed.", response.getMessage() );
	}

	@Test
	public void testNotifyWrongAuthenticatedResult() {
		BaseResponse response = saAccess.notifyAuthenticated( validUsername, "wrong", "NONE" );
		assertEquals( "Request has been processed.", response.getMessage() );
	}

	@Test
	public void testWhenUserIdIsNotValidThenNotifyAuthenticatedResultFail() {
		BaseResponse response = saAccess.notifyAuthenticated( UNEXISTING_USERNAME, "success", "NONE" );
		assertEquals( "User Id was not found.", response.getMessage() );
	}
}
