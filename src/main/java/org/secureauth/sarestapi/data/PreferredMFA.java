package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.util.JSONUtil;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreferredMFA {

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("mode")
	private String mode;
	
	@JsonProperty("available_choosen_mode")
	private String availableChoosenMode;
	
	@JsonProperty("factor_id")
	private String factorId;

	@JsonProperty("auth_type")
	private String authType;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAvailableChoosenMode() {
		return availableChoosenMode;
	}

	public void setAvailableChoosenMode(String availableChoosenMode) {
		this.availableChoosenMode = availableChoosenMode;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	@Override
	public String toString() {
		return JSONUtil.convertObjectToJSON(this);
	}

}
