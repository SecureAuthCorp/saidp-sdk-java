package org.secureauth.sarestapi;

import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.data.Response.GroupAssociationResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;

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


}
