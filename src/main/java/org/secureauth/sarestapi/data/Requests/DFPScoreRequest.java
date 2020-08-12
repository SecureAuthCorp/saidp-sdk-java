package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DFPScoreRequest extends DFPValidateRequest {
	@JsonProperty("fingerprint_id")
	private String fingerprintId;

	public DFPScoreRequest(DFPConfirmRequest dfpConfirmRequest, DFPValidateRequest dfpValidateRequest) {
		this.setUser_id(dfpValidateRequest.getUser_id());
		this.setHost_address(dfpValidateRequest.getHost_address());
		this.setFingerprint(dfpValidateRequest.getFingerprint());
		this.setFingerprintId(dfpConfirmRequest.getFingerprint_id());
	}

	public DFPScoreRequest() {
	}

	public String getFingerprintId() {
		return fingerprintId;
	}

	public void setFingerprintId(String fingerprintId) {
		this.fingerprintId = fingerprintId;
	}
}
