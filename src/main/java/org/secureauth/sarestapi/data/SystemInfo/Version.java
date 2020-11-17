package org.secureauth.sarestapi.data.SystemInfo;

public class Version{
	private String majorVersion;
	private String minorVersion;
	private String buildVersion;

	public String printableVersion(){
		return majorVersion + "." + minorVersion + "." + buildVersion;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public String getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(String minorVersion) {
		this.minorVersion = minorVersion;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}
}