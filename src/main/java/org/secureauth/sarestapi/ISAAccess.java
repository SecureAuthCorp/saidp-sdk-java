package org.secureauth.sarestapi;

import org.secureauth.sarestapi.data.IPEval;
import org.secureauth.sarestapi.data.PushAcceptStatus;
import org.secureauth.sarestapi.data.Requests.AccessHistoryRequest;
import org.secureauth.sarestapi.data.Requests.DFPScoreRequest;
import org.secureauth.sarestapi.data.Response.AdaptiveAuthResponse;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.BehaveBioResponse;
import org.secureauth.sarestapi.data.Response.DFPConfirmResponse;
import org.secureauth.sarestapi.data.Response.DFPValidateResponse;
import org.secureauth.sarestapi.data.Response.FactorsResponse;
import org.secureauth.sarestapi.data.Response.GroupAssociationResponse;
import org.secureauth.sarestapi.data.Response.JSObjectResponse;
import org.secureauth.sarestapi.data.Response.NumberProfileResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.Response.ThrottleResponse;
import org.secureauth.sarestapi.data.Response.UserProfileResponse;
import org.secureauth.sarestapi.data.Response.ValidateOTPResponse;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfile;
import org.secureauth.sarestapi.data.UserProfile.UserToGroups;
import org.secureauth.sarestapi.data.UserProfile.UsersToGroup;

public interface ISAAccess {

	/**
	 * <p>
	 *     Returns IP Risk Evaluation from the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param ipAddress The IP Address of the user making the request for access
	 * @return {@link org.secureauth.sarestapi.data.IPEval}
	 *
	 */
	IPEval iPEvaluation(String userId, String ipAddress);

	/**
	 * <p>
	 *     Returns the list of Factors available for the specified user
	 * </p>
	 * @param userId the userid of the identity you wish to have a list of possible second factors
	 * @return {@link FactorsResponse}
	 */
	FactorsResponse factorsByUser(String userId);

	/**
	 * <p>
	 *     Send push to accept request asynchronously
	 * </p>
	 *
	 * @param userId  the user id of the identity
	 * @param factorId the P2A Id to be compared against
	 * @param endUserIP The End Users IP Address
	 * @param clientCompany The Client Company Name
	 * @param clientDescription The Client Description
	 * @return {@link FactorsResponse}
	 */
	ResponseObject sendPushToAcceptReq(String userId, String factorId, String endUserIP, String clientCompany, String clientDescription);

	ResponseObject sendPushToAcceptSymbolReq(String userId, String factorId, String endUserIP, String clientCompany, String clientDescription);

	/**
	 * <p>
	 *     Send push to accept biometric request asynchronously
	 * </p>
	 *
	 * @param biometricType fingerprint, face
	 * @param userId  the user id of the identity
	 * @param factorId the P2A Id to be compared against
	 * @param endUserIP The End Users IP Address
	 * @param clientCompany The Client Company Name
	 * @param clientDescription The Client Description
	 * @return {@link FactorsResponse}
	 */
	ResponseObject sendPushBiometricReq(String biometricType, String userId, String factorId, String endUserIP, String clientCompany, String clientDescription);

	/**
	 * <p>
	 *     Perform adaptive auth query
	 * </p>
	 * @param userId the user id of the identity
	 * @param endUserIP the IP of requesting client
	 * @return {@link FactorsResponse}
	 */
	AdaptiveAuthResponse adaptiveAuthQuery(String userId, String endUserIP);

	PushAcceptStatus queryPushAcceptStatus(String refId);

	/**
	 *
	 * <p>
	 *     Checks if the Username exists within the datastore within SecureAuth
	 * </p>
	 * @param userId the userid of the identity
	 * @return {@link ResponseObject}
	 */
	BaseResponse validateUser(String userId);

	/**
	 * the OTP throttling count to 0 after the end-user successfully authenticates;
	 * the attempt count is stored in a directory attribute configured in the Web Admin
	 * @param userId id of user
	 * @return base answer
	 */
	ThrottleResponse resetThrottleReq(String userId);

	/**
	 * GET the end-user's current count of OTP usage attempts
	 * @param userId id of user
	 * @return base answer
	 */
	ThrottleResponse getThrottleReq(String userId);

	/**
	 * <p>
	 *     Checks the users password against SecureAuth Datastore
	 * </p>
	 * @param userId the userid of the identity
	 * @param password The password of the user to validate
	 * @return {@link ResponseObject}
	 */
	BaseResponse validateUserPassword(String userId, String password);

	/**
	 * <p>
	 *     Checks the users pin against SecureAuth Datastore
	 * </p>
	 * @param userId the userid of the identity
	 * @param pin The pin of the user to validate
	 * @return {@link ResponseObject}
	 */
	BaseResponse validateUserPin(String userId, String pin);

	/**
	 * <p>
	 *     Validate the users Answer to a KB Question
	 * </p>
	 * @param userId the userid of the identity
	 * @param answer The answer to the KBA
	 * @param factorId the KB Id to be compared against
	 * @return {@link ResponseObject}
	 */
	BaseResponse validateKba(String userId, String answer, String factorId);

	/**
	 *<p>
	 *     Validate the Oath Token
	 *</p>
	 * @param userId the userid of the identity
	 * @param otp The One Time Passcode to validate
	 * @param factorId The Device Identifier
	 * @return {@link ResponseObject}
	 */
	BaseResponse validateOath(String userId, String otp, String factorId);

	/**
	 * <p>
	 *     Send One Time Passcode by Phone
	 * </p>
	 * @param userId the userid of the identity
	 * @param factorId  Phone Property   "Phone1"
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverOTPByPhone(String userId, String factorId);

	/**
	 * <p>
	 *     Send One Time Passcode by Phone Ad Hoc
	 * </p>
	 * @param userId the userid of the identity
	 * @param phoneNumber  Phone Number to call
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverAdHocOTPByPhone(String userId, String phoneNumber);

	/**
	 * <p>
	 *     Send One Time Passcode by SMS to Registered User
	 * </p>
	 * @param userId the userid of the identity
	 * @param factorId  Phone Property   "Phone1"
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverOTPBySMS(String userId, String factorId);

	/**
	 * <p>
	 *     Validate One Time Passcode sent by SMS
	 * </p>
	 * @param userId the userid of the identity
	 * @param otp  OTP Value to compare against what was sent
	 * @return {@link ValidateOTPResponse}
	 */
	ValidateOTPResponse validateOTP(String userId, String otp);

	/**
	 * <p>
	 *     Send One Time Passcode by SMS Ad Hoc
	 * </p>
	 * @param userId the userid of the identity
	 * @param phoneNumber  Phone Number to send SMS to
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverAdHocOTPBySMS(String userId, String phoneNumber);

	/**
	 * <p>
	 *     Send One Time Passcode by Email to Help Desk
	 * </p>
	 * @param userId the userid of the identity
	 * @param factorId  Help Desk Property   "HelpDesk1"
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverHelpDeskOTPByEmail(String userId, String factorId);

	/**
	 * <p>
	 *     Send One Time Passcode by Email
	 * </p>
	 * @param userId the userid of the identity
	 * @param factorId  Email Property   "Email1"
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverOTPByEmail(String userId, String factorId);

	/**
	 * <p>
	 *     Send One Time Passcode by Email Ad Hoc
	 * </p>
	 * @param userId the userid of the identity
	 * @param emailAddress  Email Address
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverAdHocOTPByEmail(String userId, String emailAddress);

	/**
	 * <p>
	 *     Send One Time Passcode by Push
	 * </p>
	 * @param userId the userid of the identity
	 * @param factorId  Device Property   "z0y9x87wv6u5t43srq2p1on"
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverOTPByPush(String userId, String factorId);

	/**
	 * <p>
	 *     Send One Time Passcode by Helpdesk
	 * </p>
	 * @param userId the userid of the identity
	 * @param factorId  Help Desk Property   "HelpDesk1"
	 * @return {@link ResponseObject}
	 */
	ResponseObject deliverOTPByHelpDesk(String userId, String factorId);

	/**
	 * <p>
	 *     Returns response to Access History Post Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param ipAddress The IP Address of the user to be stored in the Datastore for use when evaluating Geo-Velocity
	 * @return {@link AccessHistoryRequest}
	 *
	 */
	ResponseObject accessHistory(String userId, String ipAddress);

	/**
	 * <p>
	 *     Confirm the DFP data from Client using the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param fingerprintId The ID of the finger print to check against the data store
	 * @return {@link DFPConfirmResponse}
	 *
	 */
	DFPConfirmResponse DFPConfirm(String userId, String fingerprintId);

	/**
	 * <p>
	 *     Validate the DFP data from Client using the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param hostAddress The ID of the finger print to check against the data store
	 * @param jsonString The JSON String provided by the Java Script
	 * @return {@link DFPValidateResponse}
	 *
	 */
	DFPValidateResponse DFPValidateNewFingerprint(String userId, String hostAddress, String jsonString);

	/**
	 * <p>
	 *     Returns the url for the JavaScript Source for DFP
	 * </p>
	 * @return {@link JSObjectResponse}
	 */
	JSObjectResponse javaScriptSrc();

	/**
	 * <p>
	 *     Returns the url for the JavaScript Source for BehaveBioMetrics
	 * </p>
	 * @return {@link JSObjectResponse}
	 */
	JSObjectResponse BehaveBioJSSrc();

	/**
	 * <p>
	 *     Submit Behave Bio Profile using the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param behaviorProfile The Behavioral Profile of the user
	 * @param hostAddress The IP Address of the user
	 * @param userAgent  The Browser User Agent of the user
	 *
	 * @return {@link BehaveBioResponse}
	 *
	 */
	BehaveBioResponse BehaveBioProfileSubmit(String userId, String behaviorProfile, String hostAddress, String userAgent);

	/**
	 * <p>
	 *     Submit Reset Request to Behave Bio Profile using the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param fieldName The Behavioral FieldName to Reset
	 * @param fieldType The Behavioral FieldType to Reset
	 * @param deviceType  The Behavioral DeviceType to Reset
	 *
	 * @return {@link ResponseObject}
	 *
	 */
	ResponseObject BehaveBioProfileReset(String userId, String fieldName, String fieldType, String deviceType);

	/**
	 * <p>
	 *     Creates User / Profile
	 * </p>
	 * @param newUserProfile The newUserProfile Object
	 * @return {@link ResponseObject}
	 */
	ResponseObject createUser(NewUserProfile newUserProfile);

	/**
	 * <p>
	 *     Update User / Profile
	 * </p>
	 * @param userId the UserID tied to the Profile Object
	 * @param userProfile The User'sProfile Object to be updated
	 * @return {@link ResponseObject}
	 */
	ResponseObject updateUser(String userId, NewUserProfile userProfile);

	/**
	 * <p>
	 *     Associate User to Group
	 * </p>
	 * @param userId the user id of the identity
	 * @param groupName The Name of the group to associate the user to
	 * @return {@link GroupAssociationResponse}
	 */
	ResponseObject addUserToGroup(String userId, String groupName);

	/**
	 * <p>
	 *     Associate Group to Users
	 * </p>
	 * @param usersToGroup The Users to Group object holding the userIds
	 * @param groupName The Name of the group to associate the user to
	 * @return {@link GroupAssociationResponse}
	 */
	GroupAssociationResponse addUsersToGroup(UsersToGroup usersToGroup, String groupName);

	/**
	 * <p>
	 *     Associate Group to User
	 * </p>
	 * @param groupName the Group Name
	 * @param userId The userId to associate to the group
	 * @return {@link GroupAssociationResponse}
	 */
	GroupAssociationResponse addGroupToUser(String groupName, String userId);

	/**
	 * <p>
	 *     Associate User To Groups
	 * </p>
	 * @param userId The UserId we are going to assign Groups to
	 * @param userToGroups The UserToGroups Object holding the list of groups to associate to the user
	 * @return {@link GroupAssociationResponse}
	 */
	GroupAssociationResponse addUserToGroups(String userId, UserToGroups userToGroups);

	/**
	 * <p>
	 *     Returns the UserProfile for the specified user
	 * </p>
	 * @param userId the userid of the identity you wish to have a list of possible second factors
	 * @return {@link UserProfileResponse}
	 */
	UserProfileResponse getUserProfile(String userId);

	/**
	 * <p>
	 *     Administrative Password Reset for the specified user
	 * </p>
	 * @param userId the userid of the identity you wish to have a list of possible second factors
	 * @param password the users new password
	 * @return {@link ResponseObject}
	 */
	ResponseObject passwordReset(String userId, String password);

	/**
	 * <p>
	 *     Self Service Password Reset for the specified user
	 * </p>
	 * @param userId the userid of the identity you wish to have a list of possible second factors
	 * @param currentPassword the users Current password
	 * @param newPassword the users new Password
	 * @return {@link ResponseObject}
	 */
	ResponseObject passwordChange(String userId, String currentPassword, String newPassword);

	/**
	 * <p>
	 *     Submit User Name and Phone Number to the Phone Number Profiling service using the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param phoneNumber The Phone number to get a profile on
	 *
	 * @return {@link NumberProfileResponse}
	 *
	 */
	NumberProfileResponse PhoneNumberProfileSubmit(String userId, String phoneNumber);

	/**
	 * <p>
	 *     Submit Update to Phone Number Profiling Service using the Rest API
	 * </p>
	 * @param userId The User ID that you want to validate from
	 * @param phoneNumber user phone number provided
	 * @param portedStatus user phone status for the option to block phone numbers that recently changed carriers (not_ported, ported)
	 * @param carrierCode 6-digit number or a concatenation of the country code and phone type
	 * @param carrier name of the carrier or a concatenation of the country code and phone type
	 * @param countryCode  2-character country code
	 * @param networkType phone connection source (landline, tollfree, mobile, virtual, unknown, landline_tollfree)
	 *
	 * @return {@link BaseResponse}
	 *
	 */
	BaseResponse UpdatePhoneNumberProfile(String userId, String phoneNumber, String portedStatus, String carrierCode, String carrier, String countryCode, String networkType);

	/**
	 * Retrieves the user's status from the username in the endpoint URL and returns a response.
	 * @param userId The User ID that you want to validate
	 * @return {@link BaseResponse}
	 */
	BaseResponse getUserStatus(String userId);

	/**
	 * Method invokes a status to the user Id.
	 * @param userId The User ID that you want to change status
	 * @param status The new status [lock, unlock, enable, disable]
	 * @return {@link BaseResponse}
	 */
	BaseResponse setUserStatus(String userId, String status);

	/**
	 * Retrieves score from fingerprint, user and host.
	 * @param userId User ID provided
	 * @param hostAddress host
	 * @param fingerprintId GUID of the profile
	 * @param fingerPrintJSON Descriptive name derived from the user_agent string
	 * @return {@link DFPValidateResponse}
	 */
	DFPValidateResponse DFPScoreFingerprint(String userId, String hostAddress, String fingerprintId, String fingerPrintJSON);

	/**
	 * Method to complete the user account profile in the directory
	 * @param userId User ID provided
	 * @param hostAddress host
	 * @param fingerprintId GUID of the profile
	 * @param fingerPrintJSON Descriptive name derived from the user_agent string
	 * @return {@link DFPConfirmResponse}
	 */
	DFPValidateResponse DFPSaveFingerprint(String userId, String hostAddress, String fingerprintId, String fingerPrintJSON);

}
