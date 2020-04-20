package org.secureauth.sarestapi.data.Response;

/**
 * The value of the integer corresponding to the number of the successful / failed attempt within the rolling time period
 *
 */
public class ThrottleResponse extends BaseResponse {

	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
