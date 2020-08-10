package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DFPScoreRequest {

	private DFPConfirmRequest dfpConfirmRequest;
	private DFPValidateRequest dfpValidateRequest;

	public DFPScoreRequest(DFPConfirmRequest dfpConfirmRequest, DFPValidateRequest dfpValidateRequest) {
		this.dfpConfirmRequest = dfpConfirmRequest;
		this.dfpValidateRequest = dfpValidateRequest;
	}

	public DFPScoreRequest() {
	}

	public DFPConfirmRequest getDfpConfirmRequest() {
		return dfpConfirmRequest;
	}

	public void setDfpConfirmRequest(DFPConfirmRequest dfpConfirmRequest) {
		this.dfpConfirmRequest = dfpConfirmRequest;
	}

	public DFPValidateRequest getDfpValidateRequest() {
		return dfpValidateRequest;
	}

	public void setDfpValidateRequest(DFPValidateRequest dfpValidateRequest) {
		this.dfpValidateRequest = dfpValidateRequest;
	}
}
