package org.secureauth.restapi.test;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.secureauth.sarestapi.ISAAccess;
import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.DFPValidateResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.Response.UserProfileResponse;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.data.SABaseURL;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfile;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfileProperties;
import org.secureauth.sarestapi.data.UserProfile.UserProfileKB;
import org.secureauth.sarestapi.exception.SARestAPIException;
import org.secureauth.sarestapi.guid.GUIDStrategy;
import org.secureauth.sarestapi.resources.SAExecuter;
import org.secureauth.sarestapi.util.Property;
import org.secureauth.sarestapi.util.RetrievePropertiesUtils;
import org.secureauth.sarestapi.util.SAFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SAAccessTDD {

	private static Logger logger = LoggerFactory.getLogger(SAAccessTDD.class);
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

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
	private final static String UNEXISTING_USERNAME_QP = UNEXISTING_USERNAME + "+~.!@$%^&*'_";

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
	public void testUserPINWithValidValuesIncludingAXRequestID() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "valid",
			  "message" : ""
			}
		 */
		final GUIDStrategy guidStrategy = UUID::randomUUID;
		SAAccess saAccessWithXRequestID = new SAAccess( saBaseURL, saAuth, new SAExecuter( saBaseURL, guidStrategy) );
		BaseResponse response = saAccessWithXRequestID.validateUserPin(validUsername, validPin);
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
	public void testGetPropertiesWithValidUserQP() throws Exception {
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

		UserProfileResponse response = saAccess.getUserProfileQP(validUsername);
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
		assertEquals(NOT_FOUND_MESSAGE, response.getStatus());
		assertEquals( "User Id was not found.", response.getMessage());
	}

	@Test
	public void testGetPropertiesWithUnexistingUserQP() throws Exception {
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

		UserProfileResponse response = saAccess.getUserProfileQP(UNEXISTING_USERNAME_QP);
		assertNotNull(response);
		// If the special characters are not being recognised then we should get some sort of reject instead of a NOT_FOUND_MESSAGE here
		assertEquals(NOT_FOUND_MESSAGE, response.getStatus());
		assertEquals( "User Id was not found.", response.getMessage());
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
		assertEquals(FOUND_MESSAGE, response.getStatus());
		assertTrue(response.getMessage().isEmpty());
	}

	@Test
	public void testGetFactorsFromValidUserQP() throws Exception {
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

		FactorsResponse response = saAccess.factorsByUserQP(validUsername);
		assertNotNull(response);
		assertEquals(FOUND_MESSAGE, response.getStatus());
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
		assertEquals(NOT_FOUND_MESSAGE, response.getStatus());
		assertEquals("User Id was not found.", response.getMessage());
	}

	@Test
	public void testGetFactorsFromUnexistingUserQP() throws Exception {
		/*
		 * Response would return:
			{
			  "status" : "not_found",
			  "message" : "User Id was not found.",
			  "user_id" : "unexisting-user",
			  "factors" : [ ]
			}
		 */

		FactorsResponse response = saAccess.factorsByUserQP(UNEXISTING_USERNAME_QP);
		assertNotNull(response);
		assertEquals(NOT_FOUND_MESSAGE, response.getStatus());
		assertEquals("User Id was not found.", response.getMessage());
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

		BaseResponse response = saAccess.validateUser(UNEXISTING_USERNAME_QP);
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
	public void testUpdateUserProfileKBQKBAOrderedValid() throws Exception {
		NewUserProfile newUserProfile = new NewUserProfile();
		newUserProfile.getKnowledgeBase().put("nonFormated1", new UserProfileKB("kbq1", "kba1"));
		newUserProfile.getKnowledgeBase().put("nonFormated3", new UserProfileKB("kbq3", "kba3"));
		newUserProfile.getKnowledgeBase().put("nonFormated2", new UserProfileKB("kbq2", "kba2"));
		newUserProfile.setPassword(validPassword);
		NewUserProfileProperties newUserProfileProperties = new NewUserProfileProperties();
		newUserProfileProperties.setEmail4("email@email.com");
		newUserProfileProperties.setAuxId10("aux10");
		newUserProfileProperties.setPhone4("123-456-7890");
		newUserProfile.setProperties(newUserProfileProperties);

		ResponseObject responseObj = saAccess.updateUser(validUsername, newUserProfile);

		assertNotNull(responseObj);
		assertEquals("success", responseObj.getStatus());
		assertEquals("", responseObj.getMessage());
	}

	@Test
	public void testCreateUserProfileKBQKBAOrderedValid() throws Exception {

		NewUserProfile newUserProfile = new NewUserProfile();
		newUserProfile.getKnowledgeBase().put("NonFormated2", new UserProfileKB("Kbq2", "kba2"));
		newUserProfile.getKnowledgeBase().put("nOnFormated3", new UserProfileKB("kbq3", "kba3"));
		newUserProfile.getKnowledgeBase().put("nonFormated1", new UserProfileKB("kBq1", "kba1"));
		newUserProfile.setPassword(validPassword);
		newUserProfile.setUserId(String.valueOf(randomNumberBetween(1, 1000)));
		NewUserProfileProperties newUserProfileProperties = new NewUserProfileProperties();
		newUserProfileProperties.setEmail1("email@email.com");
		newUserProfileProperties.setAuxId10("aux10");
		newUserProfileProperties.setPhone4("123-456-7890");
		newUserProfileProperties.setFirstName("foo");
		newUserProfileProperties.setLastName("foo");
		newUserProfile.setProperties(newUserProfileProperties);

		ResponseObject responseObj = saAccess.createUser(newUserProfile);

		assertNotNull(responseObj);
		assertEquals("success", responseObj.getStatus());
		assertEquals("", responseObj.getMessage());
	}

	@Test(expected = SARestAPIException.class)
	public void testCreateUserProfileKBQKBAOrderedInValid() throws Exception {

		NewUserProfile newUserProfile = new NewUserProfile();
		newUserProfile.getKnowledgeBase().put("nonFormated1", new UserProfileKB("kbq1", "kba1"));
		newUserProfile.getKnowledgeBase().put("nonFormated2", new UserProfileKB("kbq2", "kba2"));
		newUserProfile.getKnowledgeBase().put("nonFormated3", new UserProfileKB("kbq3", "kba3"));
		newUserProfile.setPassword("");
		newUserProfile.setUserId("");
		NewUserProfileProperties newUserProfileProperties = new NewUserProfileProperties();
		newUserProfileProperties.setEmail1("email@email.com");
		newUserProfileProperties.setAuxId10("aux10");
		newUserProfileProperties.setPhone4("123-456-7890");
		newUserProfileProperties.setFirstName("foo");
		newUserProfileProperties.setLastName("foo");
		newUserProfile.setProperties(newUserProfileProperties);

		ResponseObject responseObj = saAccess.createUser(newUserProfile);

		exceptionRule.expect(SARestAPIException.class);
		exceptionRule.expectMessage("User and password are required to create a new user");
		assertEquals(null, responseObj);
	}

	private double randomNumberBetween(int min, int max){
		return (Math.random() * ((max - min) + 1)) + min;
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
	public void testDFPScoreWithValidFoundData() throws Exception {
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
		String fingerprintJSON = "{\n" +
				"        \"fingerprint\" : {\"uaString\" : \"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0\",\n" +
				"        \"uaBrowser\" : {\n" +
				"            \"name\" : \"Firefox\",\n" +
				"            \"version\" : \"52.0\",\n" +
				"            \"major\" : \"52\"\n" +
				"        },\n" +
				"        \"uaDevice\" : {\n" +
				"            \"model\" : \"testmodel\",\n" +
				"            \"type\" : \"testtype\",\n" +
				"            \"vendor\" : \"testvendor\"\n" +
				"        },\n" +
				"        \"uaEngine\" : {\n" +
				"            \"name\" : \"Gecko\",\n" +
				"            \"version\" : \"52.0\"\n" +
				"        },\n" +
				"        \"uaOS\" : {\n" +
				"            \"name\" : \"Windows\",\n" +
				"            \"version\" : \"8.1\"\n" +
				"        },\n" +
				"        \"uaCPU\" : {\n" +
				"            \"architecture\" : \"amd64\"\n" +
				"        },\n" +
				"        \"uaPlatform\" : \"Win32\",\n" +
				"        \"language\" : \"en-US\",\n" +
				"        \"colorDepth\" : 24,\n" +
				"        \"pixelRatio\" : 1.0,\n" +
				"        \"screenResolution\" : \"2560x1440\",\n" +
				"        \"availableScreenResolution\" : \"2560x1400\",\n" +
				"        \"timezone\" : \"America/Los_Angeles\",\n" +
				"        \"timezoneOffset\" : 420,\n" +
				"        \"localStorage\" : true,\n" +
				"        \"sessionStorage\" : true,\n" +
				"        \"indexedDb\" : true,\n" +
				"        \"addBehavior\" : false,\n" +
				"        \"openDatabase\" : false,\n" +
				"        \"cpuClass\" : null,\n" +
				"        \"platform\" : \"Win32\",\n" +
				"        \"doNotTrack\" : \"unspecified\",\n" +
				"        \"plugins\" : \"\",\n" +
				"        \"canvas\" : \"812446969\",\n" +
				"        \"webGl\" : \"-1928114666\",\n" +
				"        \"adBlock\" : false,\n" +
				"        \"userTamperLanguage\" : false,\n" +
				"        \"userTamperScreenResolution\" : false,\n" +
				"        \"userTamperOS\" : false,\n" +
				"        \"userTamperBrowser\" : false,\n" +
				"        \"touchSupport\" : {\n" +
				"            \"maxTouchPoints\" : 0,\n" +
				"            \"touchEvent\" : false,\n" +
				"            \"touchStart\" : false\n" +
				"        },\n" +
				"        \"cookieSupport\" : true,\n" +
				"        \"fonts\" : \"Aharoni,Andalus,Angsana New,AngsanaUPC,Aparajita,Arabic Typesetting,Arial,Batang,BatangChe,Bauhaus 93,Bodoni 72,Bodoni 72 Oldstyle,Bodoni 72 Smallcaps,Bookshelf Symbol 7,Browallia New,BrowalliaUPC,Calibri,Cambria,Cambria Math,Candara,Comic Sans MS,Consolas,Constantia,Corbel,Cordia New,CordiaUPC,DaunPenh,David,DFKai-SB,DilleniaUPC,DokChampa,Dotum,DotumChe,Ebrima,English 111 Vivace BT,Estrangelo Edessa,EucrosiaUPC,Euphemia,FangSong,Franklin Gothic,FrankRuehl,FreesiaUPC,Gabriola,Gautami,Georgia,GeoSlab 703 Lt BT,GeoSlab 703 XBd BT,Gisha,Gulim,GulimChe,Gungsuh,GungsuhChe,Helvetica,Humanst 521 Cn BT,Impact,IrisUPC,Iskoola Pota,JasmineUPC,KaiTi,Kalinga,Kartika,Khmer UI,KodchiangUPC,Kokila,Lao UI,Latha,Leelawadee,Levenim MT,LilyUPC,Lucida Console,Lucida Sans Unicode,Malgun Gothic,Mangal,Marlett,Meiryo,Meiryo UI,Microsoft Himalaya,Microsoft JhengHei,Microsoft New Tai Lue,Microsoft PhagsPa,Microsoft Sans Serif,Microsoft Tai Le,Microsoft Uighur,Microsoft YaHei,Microsoft Yi Baiti,MingLiU,MingLiU_HKSCS,MingLiU_HKSCS-ExtB,MingLiU-ExtB,Miriam,Miriam Fixed,Modern No. 20,Mongolian Baiti,MoolBoran,MS Gothic,MS Mincho,MS PGothic,MS PMincho,MS Sans Serif,MS Serif,MS UI Gothic,MV Boli,Narkisim,NSimSun,Nyala,Palatino Linotype,Plantagenet Cherokee,PMingLiU,PMingLiU-ExtB,Raavi,Rod,Roman,Sakkal Majalla,Segoe Print,Segoe Script,Segoe UI,Segoe UI Symbol,Shonar Bangla,Shruti,SimHei,Simplified Arabic,Simplified Arabic Fixed,SimSun,SimSun-ExtB,Small Fonts,Sylfaen,Tahoma,Times,Times New Roman,Traditional Arabic,Trebuchet MS,Tunga,Univers CE 55 Medium,Utsaah,Vani,Verdana,Vijaya,Vrinda,Wingdings,Wingdings 2,Wingdings 3\",\n" +
				"        \"id\" : \"a31332450f284e9bbb1572e7c1c4927a\",\n" +
				"        \"userId\" : \"atest\",\n" +
				"        \"displayName\" : \"Windows - 8.1 - Firefox\",\n" +
				"        \"httpHeaders\" : {\n" +
				"            \"Accept\" : \"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\",\n" +
				"            \"AcceptCharSet\" : \"\",\n" +
				"            \"AcceptEncoding\" : \"gzip, deflate, br\",\n" +
				"            \"AcceptLanguage\" : \"en-US,en;q=0.5\"\n" +
				"        },\n" +
				"        \"hostAddress\" : \"172.16.17.171\",\n" +
				"        \"mobileDeviceId\" : \"\",\n" +
				"        \"mobileDeviceName\" : \"\",\n" +
				"        \"mobileDeviceComment\" : \"\",\n" +
				"        \"lastAccess\" : \"2017-05-08T20:28:18.4333144+00:00\",\n" +
				"        \"createdOn\" : \"2017-05-08T20:28:18.4333144+00:00\"},\n" +
				"        \"accept\" : \"asdasdf\",\n" +
				"        \"acceptCharset\" : \"bbbbbb\",\n" +
				"        \"acceptEncoding\" : \"cccc\",\n" +
				"        \"acceptLanguage\" : \"eeee\",\n" +
				"        \"deviceId\" : \"aaa\",\n" +
				"        \"deviceName\" : \"asdf\",\n" +
				"        \"deviceComment\" : \"\"\n" +
				"    }";

		DFPValidateResponse response = saAccess.DFPScoreFingerprint(validUsername, validHostAddress, validFingerprintId, fingerprintJSON);
		assertNotNull(response);
		assertEquals("found", response.getStatus());
		assertEquals(100.0, response.getScore(), 1);
		assertEquals(90.0, response.getUpdate_score(), 1);
		assertEquals(89.0, response.getMatch_score(), 1);
	}

	@Test
	public void testDFPSaveWithValidFound() throws Exception {
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
		String fingerprintJSON = "{\n" +
				"        \"fingerprint\" : {\"uaString\" : \"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0\",\n" +
				"        \"uaBrowser\" : {\n" +
				"            \"name\" : \"Firefox\",\n" +
				"            \"version\" : \"52.0\",\n" +
				"            \"major\" : \"52\"\n" +
				"        },\n" +
				"        \"uaDevice\" : {\n" +
				"            \"model\" : \"testmodel\",\n" +
				"            \"type\" : \"testtype\",\n" +
				"            \"vendor\" : \"testvendor\"\n" +
				"        },\n" +
				"        \"uaEngine\" : {\n" +
				"            \"name\" : \"Gecko\",\n" +
				"            \"version\" : \"52.0\"\n" +
				"        },\n" +
				"        \"uaOS\" : {\n" +
				"            \"name\" : \"Windows\",\n" +
				"            \"version\" : \"8.1\"\n" +
				"        },\n" +
				"        \"uaCPU\" : {\n" +
				"            \"architecture\" : \"amd64\"\n" +
				"        },\n" +
				"        \"uaPlatform\" : \"Win32\",\n" +
				"        \"language\" : \"en-US\",\n" +
				"        \"colorDepth\" : 24,\n" +
				"        \"pixelRatio\" : 1.0,\n" +
				"        \"screenResolution\" : \"2560x1440\",\n" +
				"        \"availableScreenResolution\" : \"2560x1400\",\n" +
				"        \"timezone\" : \"America/Los_Angeles\",\n" +
				"        \"timezoneOffset\" : 420,\n" +
				"        \"localStorage\" : true,\n" +
				"        \"sessionStorage\" : true,\n" +
				"        \"indexedDb\" : true,\n" +
				"        \"addBehavior\" : false,\n" +
				"        \"openDatabase\" : false,\n" +
				"        \"cpuClass\" : null,\n" +
				"        \"platform\" : \"Win32\",\n" +
				"        \"doNotTrack\" : \"unspecified\",\n" +
				"        \"plugins\" : \"\",\n" +
				"        \"canvas\" : \"812446969\",\n" +
				"        \"webGl\" : \"-1928114666\",\n" +
				"        \"adBlock\" : false,\n" +
				"        \"userTamperLanguage\" : false,\n" +
				"        \"userTamperScreenResolution\" : false,\n" +
				"        \"userTamperOS\" : false,\n" +
				"        \"userTamperBrowser\" : false,\n" +
				"        \"touchSupport\" : {\n" +
				"            \"maxTouchPoints\" : 0,\n" +
				"            \"touchEvent\" : false,\n" +
				"            \"touchStart\" : false\n" +
				"        },\n" +
				"        \"cookieSupport\" : true,\n" +
				"        \"fonts\" : \"Aharoni,Andalus,Angsana New,AngsanaUPC,Aparajita,Arabic Typesetting,Arial,Batang,BatangChe,Bauhaus 93,Bodoni 72,Bodoni 72 Oldstyle,Bodoni 72 Smallcaps,Bookshelf Symbol 7,Browallia New,BrowalliaUPC,Calibri,Cambria,Cambria Math,Candara,Comic Sans MS,Consolas,Constantia,Corbel,Cordia New,CordiaUPC,DaunPenh,David,DFKai-SB,DilleniaUPC,DokChampa,Dotum,DotumChe,Ebrima,English 111 Vivace BT,Estrangelo Edessa,EucrosiaUPC,Euphemia,FangSong,Franklin Gothic,FrankRuehl,FreesiaUPC,Gabriola,Gautami,Georgia,GeoSlab 703 Lt BT,GeoSlab 703 XBd BT,Gisha,Gulim,GulimChe,Gungsuh,GungsuhChe,Helvetica,Humanst 521 Cn BT,Impact,IrisUPC,Iskoola Pota,JasmineUPC,KaiTi,Kalinga,Kartika,Khmer UI,KodchiangUPC,Kokila,Lao UI,Latha,Leelawadee,Levenim MT,LilyUPC,Lucida Console,Lucida Sans Unicode,Malgun Gothic,Mangal,Marlett,Meiryo,Meiryo UI,Microsoft Himalaya,Microsoft JhengHei,Microsoft New Tai Lue,Microsoft PhagsPa,Microsoft Sans Serif,Microsoft Tai Le,Microsoft Uighur,Microsoft YaHei,Microsoft Yi Baiti,MingLiU,MingLiU_HKSCS,MingLiU_HKSCS-ExtB,MingLiU-ExtB,Miriam,Miriam Fixed,Modern No. 20,Mongolian Baiti,MoolBoran,MS Gothic,MS Mincho,MS PGothic,MS PMincho,MS Sans Serif,MS Serif,MS UI Gothic,MV Boli,Narkisim,NSimSun,Nyala,Palatino Linotype,Plantagenet Cherokee,PMingLiU,PMingLiU-ExtB,Raavi,Rod,Roman,Sakkal Majalla,Segoe Print,Segoe Script,Segoe UI,Segoe UI Symbol,Shonar Bangla,Shruti,SimHei,Simplified Arabic,Simplified Arabic Fixed,SimSun,SimSun-ExtB,Small Fonts,Sylfaen,Tahoma,Times,Times New Roman,Traditional Arabic,Trebuchet MS,Tunga,Univers CE 55 Medium,Utsaah,Vani,Verdana,Vijaya,Vrinda,Wingdings,Wingdings 2,Wingdings 3\",\n" +
				"        \"id\" : \"a31332450f284e9bbb1572e7c1c4927a\",\n" +
				"        \"userId\" : \"atest\",\n" +
				"        \"displayName\" : \"Windows - 8.1 - Firefox\",\n" +
				"        \"httpHeaders\" : {\n" +
				"            \"Accept\" : \"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\",\n" +
				"            \"AcceptCharSet\" : \"\",\n" +
				"            \"AcceptEncoding\" : \"gzip, deflate, br\",\n" +
				"            \"AcceptLanguage\" : \"en-US,en;q=0.5\"\n" +
				"        },\n" +
				"        \"hostAddress\" : \"172.16.17.171\",\n" +
				"        \"mobileDeviceId\" : \"\",\n" +
				"        \"mobileDeviceName\" : \"\",\n" +
				"        \"mobileDeviceComment\" : \"\",\n" +
				"        \"lastAccess\" : \"2017-05-08T20:28:18.4333144+00:00\",\n" +
				"        \"createdOn\" : \"2017-05-08T20:28:18.4333144+00:00\"},\n" +
				"        \"accept\" : \"asdasdf\",\n" +
				"        \"acceptCharset\" : \"bbbbbb\",\n" +
				"        \"acceptEncoding\" : \"cccc\",\n" +
				"        \"acceptLanguage\" : \"eeee\",\n" +
				"        \"deviceId\" : \"aaa\",\n" +
				"        \"deviceName\" : \"asdf\",\n" +
				"        \"deviceComment\" : \"\"\n" +
				"    }";

		DFPValidateResponse response = saAccess.DFPSaveFingerprint(validUsername, validHostAddress, validFingerprintId, fingerprintJSON);
		assertNotNull(response);
		assertEquals("found", response.getStatus());
		assertEquals(100.0, response.getScore(), 1);
		assertEquals(89.0, response.getUpdate_score(), 1);
		assertEquals(90.0, response.getMatch_score(), 1);
		assertEquals(validFingerprintId, response.getFingerprintId());
		assertEquals("Windows - 8.1 - Firefox", response.getFingerprintName());
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

	// This tests the creation of a real user
	@Test
	public void testCreateUserWithSpecialCharacters() {
		NewUserProfile userProfile = new NewUserProfile();
		userProfile.setUserId(UNEXISTING_USERNAME_QP);
		userProfile.setPassword("1234");

		BaseResponse response = saAccess.createUser(userProfile);
		assertEquals("OK", response.getStatus());
	}

	@Test
	public void testGetCreatedUserWithSpecialCharacters() {
		String userName = UNEXISTING_USERNAME_QP;
		BaseResponse response = saAccess.getUserProfileQP(userName);

		assertEquals("found", response.getStatus());
	}

	@Test
	public void testGetCreatedFactorsUserWithSpecialCharacters() {
		String userName = UNEXISTING_USERNAME_QP;
		BaseResponse response = saAccess.factorsByUserQP(userName);

		assertEquals("found", response.getStatus());
	}

	@Test
	public void testGetCreatedThrottleUserWithSpecialCharacters() {
		String userName = UNEXISTING_USERNAME_QP;
		BaseResponse response = saAccess.getThrottleReqQP(userName);

		assertEquals("found", response.getStatus());
	}

	@Test
	public void testLinkToAcceptEmail() {
		ResponseObject response = saAccess.emailLink(validUsername, "Email1");

		assertEquals("valid", response.getStatus());
	}

	@Test
	public void testLinkToAcceptSMS() {
		ResponseObject response = saAccess.smsLink(validUsername, "Phone1");

		assertEquals("valid", response.getStatus());
	}

	@Test
	public void testLinkToAcceptVerify() {
		ResponseObject linkResponse = saAccess.emailLink(validUsername, "Email1");
		ResponseObject response = saAccess.verifyLinkToAcceptStatus(linkResponse.getReference_id());

		assertEquals("found", response.getStatus());
	}
}
