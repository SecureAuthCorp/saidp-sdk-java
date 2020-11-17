package org.secureauth.sarestapi.data.SystemInfo;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IdsInfo{
	@JsonProperty("ids_hosts")
	private List<String> idsHost;

	public List<String> getIdsHost() {
		return idsHost;
	}

	public void setIdsHost(List<String> idsHost) {
		this.idsHost = idsHost;
	}
}
