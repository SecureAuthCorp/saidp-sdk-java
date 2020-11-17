package org.secureauth.sarestapi.data.SystemInfo;

public class RealmSysInfo {
	private int realmId;
	private Version version;
	private Version productVersion;
	private Summary hotfixSummary;

	public int getRealmId() {
		return realmId;
	}

	public void setRealmId(int realmId) {
		this.realmId = realmId;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public Version getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(Version productVersion) {
		this.productVersion = productVersion;
	}

	public Summary getHotfixSummary() {
		return hotfixSummary;
	}

	public void setHotfixSummary(Summary hotfixSummary) {
		this.hotfixSummary = hotfixSummary;
	}
}