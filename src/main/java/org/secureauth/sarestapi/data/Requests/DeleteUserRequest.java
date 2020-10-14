package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteUserRequest {

	@JsonProperty("user_id")
	private String userId;
	private Boolean deleteAllRelatedData;
	private String domain;

	public DeleteUserRequest() {
	}

	public DeleteUserRequest(String userId, Boolean deleteAllRelatedData, String domain) {
		this.userId = userId;
		this.deleteAllRelatedData = deleteAllRelatedData;
		this.domain = domain;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getDeleteAllRelatedData() {
		return deleteAllRelatedData;
	}

	public void setDeleteAllRelatedData(Boolean deleteAllRelatedData) {
		this.deleteAllRelatedData = deleteAllRelatedData;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
