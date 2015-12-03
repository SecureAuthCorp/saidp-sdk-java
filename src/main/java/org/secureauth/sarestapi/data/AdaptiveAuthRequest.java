package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdaptiveAuthRequest extends AuthRequest {

    private AAParameters parameters;

    public AdaptiveAuthRequest(String user_id, String ip) {
    	this.user_id = user_id;
    	parameters = new AAParameters();
    	parameters.setIp_address(ip);
    }
    
	public AAParameters getParameters() {
		return parameters;
	}

	public void setParameters(AAParameters parameters) {
		this.parameters = parameters;
	}
    
    @XmlRootElement
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    public static class AAParameters {
    	private String ip_address;

		public String getIp_address() {
			return ip_address;
		}

		public void setIp_address(String ip_address) {
			this.ip_address = ip_address;
		}
    }
}
