package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DFPScoreRequest extends DFPValidateRequest {

	private DFPConfirmRequest dfpConfirmRequest;
	private String fingerprintId;

	public DFPScoreRequest(DFPConfirmRequest dfpConfirmRequest, DFPValidateRequest dfpValidateRequest) {
		this.dfpConfirmRequest = dfpConfirmRequest;
		this.setUser_id(dfpValidateRequest.getUser_id());
		this.setHost_address(dfpValidateRequest.getHost_address());
		this.setFingerprint(dfpValidateRequest.getFingerprint());
		this.setFingerprintId(dfpConfirmRequest.getFingerprint_id());
	}

	public DFPScoreRequest() {
	}

	public DFPConfirmRequest getDfpConfirmRequest() {
		return dfpConfirmRequest;
	}

	public void setDfpConfirmRequest(DFPConfirmRequest dfpConfirmRequest) {
		this.dfpConfirmRequest = dfpConfirmRequest;
	}


	public String getFingerprintId() {
		return fingerprintId;
	}

	public void setFingerprintId(String fingerprintId) {
		this.fingerprintId = fingerprintId;
	}
}
