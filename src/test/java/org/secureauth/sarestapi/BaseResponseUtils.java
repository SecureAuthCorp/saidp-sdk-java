package org.secureauth.sarestapi;

import org.secureauth.sarestapi.data.Response.*;

public class BaseResponseUtils {

	public static BaseResponse invalidUserResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id not found.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("not_found");
		return invalidResponse;
	}

	public static BaseResponse validUserResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id not found.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("valid");
		return invalidResponse;
	}

	public static BaseResponse successResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Status update complete");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("success");
		return invalidResponse;
	}

	public static ResponseObject validResponse(){
		ResponseObject responseObject = new ResponseObject();
		responseObject.setMessage("success");
		responseObject.setStatus("valid");
		return responseObject;
	}

	public static GroupAssociationResponse validGroupAssociationResponse(){
		GroupAssociationResponse responseObject = new GroupAssociationResponse();
		responseObject.setMessage("success");
		responseObject.setStatus("valid");
		return responseObject;
	}

	public static BaseResponse validUserInvalidAnswerResponse(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("PIN is invalid.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public static BaseResponse validUserResponseWithOuthMessage(String userId){
		BaseResponse validResponse = new BaseResponse();
		validResponse.setMessage("");
		validResponse.setUser_id(userId);
		validResponse.setStatus("valid");
		return validResponse;
	}

	public static UserProfileResponse validUserProfileResponse(String userId){
		UserProfileResponse validResponse = new UserProfileResponse();
		validResponse.setStatus("found");
		validResponse.setMessage("");
		return validResponse;
	}

	public static UserProfileResponse invalidUserProfileResponse(String userId){
		UserProfileResponse invalidResponse = new UserProfileResponse();
		invalidResponse.setStatus("not_found");
		invalidResponse.setMessage("User Id was not found.");
		return invalidResponse;
	}

	public static BaseResponse invalidUserOTP(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("Request validation failed with: Unknown factor id 'zzzz0000z0000a00zzzz000z0zz0z00z'.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public static BaseResponse invalidOTP(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("OTP is invalid.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public static BaseResponse InvalidPassword(String userId){
		BaseResponse invalidResponse = new BaseResponse();
		invalidResponse.setMessage("User Id or password is invalid.");
		invalidResponse.setUser_id(userId);
		invalidResponse.setStatus("invalid");
		return invalidResponse;
	}

	public static FactorsResponse validFactorsFromValidUser(String userId){
		FactorsResponse validFactorsFromValidUser = new FactorsResponse();
		validFactorsFromValidUser.setStatus("found");
		validFactorsFromValidUser.setMessage("");
		return validFactorsFromValidUser;
	}

	public static FactorsResponse invalidFactorsFromValidUser(String userId){
		FactorsResponse invalidFactorsFromValidUser = new FactorsResponse();
		invalidFactorsFromValidUser.setStatus("not_found");
		invalidFactorsFromValidUser.setMessage("User Id was not found.");
		return invalidFactorsFromValidUser;
	}

	public static FactorsResponse validateUserWithValidInfo(String userId){
		FactorsResponse testValidateUserWithValidInfo = new FactorsResponse();
		testValidateUserWithValidInfo.setStatus("found");
		testValidateUserWithValidInfo.setMessage("User Id found");
		return testValidateUserWithValidInfo;
	}

	public static BaseResponse validateUserNotFound(String userId){
		BaseResponse validateUserNotFound = new BaseResponse();
		validateUserNotFound.setStatus("not_found");
		validateUserNotFound.setMessage("User Id was not found.");
		return validateUserNotFound;
	}

	public static BaseResponse validateUserWithInvalidKey(String userId){
		BaseResponse validateUserWithInvalidKey = new BaseResponse();
		validateUserWithInvalidKey.setStatus("invalid");
		validateUserWithInvalidKey.setMessage("Invalid credentials.");
		return validateUserWithInvalidKey;
	}

	public static BaseResponse validateUserWithInvalidID(String userId){
		BaseResponse validateUserWithInvalidID = new BaseResponse();
		validateUserWithInvalidID.setStatus("invalid");
		validateUserWithInvalidID.setMessage("AppId is unknown.");
		return validateUserWithInvalidID;
	}

	public static BaseResponse validateUserWithInvalidHost(String userId){
		BaseResponse validateUserWithInvalidHost = new BaseResponse();
		return null;
	}
}
