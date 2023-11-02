package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.util.JSONUtil;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreferredMFA {
	
	@JsonProperty("factor_id")
	private String factorId;

	@JsonProperty("auth_type")
	private String type;
	
	@JsonProperty("biometric_type")
	private String biometricType;

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBiometricType() {
		return biometricType;
	}

	public void setBiometricType(String biometricType) {
		this.biometricType = biometricType;
	}

	@Override
	public String toString() {
		return JSONUtil.convertObjectToJSON(this);
	}

}
