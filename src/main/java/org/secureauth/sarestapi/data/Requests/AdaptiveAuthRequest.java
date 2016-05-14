package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.util.JSONUtil;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lding@secureauth.com
 *
Copyright (c) 2015, SecureAuth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AAParameters {
    	private String ip_address;

		public String getIp_address() {
			return ip_address;
		}

		public void setIp_address(String ip_address) {
			this.ip_address = ip_address;
		}
    }

	@Override
	public String toString(){
		return JSONUtil.convertObjectToJSON(this);
	}
}
