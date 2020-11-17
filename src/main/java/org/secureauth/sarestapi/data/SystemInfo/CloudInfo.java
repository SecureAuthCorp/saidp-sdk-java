package org.secureauth.sarestapi.data.SystemInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CloudInfo{
	@JsonProperty("license_package")
	private String license;
	@JsonProperty("sa_risk_provider")
	private boolean riskProvider;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public boolean isRiskProvider() {
		return riskProvider;
	}

	public void setRiskProvider(boolean riskProvider) {
		this.riskProvider = riskProvider;
	}
}