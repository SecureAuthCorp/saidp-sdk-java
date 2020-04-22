package org.secureauth.sarestapi.data.Requests;

public class ThrottleRequest {
	int count;

	public ThrottleRequest(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
