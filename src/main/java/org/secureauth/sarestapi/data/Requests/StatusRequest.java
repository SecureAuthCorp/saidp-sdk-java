package org.secureauth.sarestapi.data.Requests;

public class StatusRequest {

	private String status;

	public StatusRequest(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
