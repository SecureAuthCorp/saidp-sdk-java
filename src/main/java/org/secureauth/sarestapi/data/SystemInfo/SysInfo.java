package org.secureauth.sarestapi.data.SystemInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SysInfo{
	private RealmSysInfo realmSysInfo;
	private String deploymentType;
	@JsonProperty("cloud_sysinfo")
	private CloudInfo cloudInfo;
	@JsonProperty("ids_info")
	private IdsInfo idsList;

	public CloudInfo getCloudInfo() {
		return cloudInfo;
	}

	public void setCloudInfo(CloudInfo cloudInfo) {
		this.cloudInfo = cloudInfo;
	}

	public IdsInfo getIdsList() {
		return idsList;
	}

	public void setIdsList(IdsInfo idsList) {
		this.idsList = idsList;
	}

	public RealmSysInfo getRealmSysInfo() {
		return realmSysInfo;
	}

	public void setRealmSysInfo(RealmSysInfo realmSysInfo) {
		this.realmSysInfo = realmSysInfo;
	}

	public String getDeploymentType() {
		return deploymentType;
	}

	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}
}