package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.resources.Resource;

/**
 * @author rrowcliffe@secureauth.com
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
public class SABaseURL {
    private String appliance = "localhost";
    private String port = "443";
    private boolean useSSL = true;
    private boolean selfSigned = false;


    public String getApplianceURL(){
        StringBuilder stringBuilder = new StringBuilder();
        if(useSSL){stringBuilder.append(Resource.HTTPS);}
        else{ stringBuilder.append(Resource.HTTP);}

        stringBuilder.append(appliance).append(Resource.COLON).append(port).append(Resource.SLASH);

        return stringBuilder.toString();
    }

    public SABaseURL(String appliance, String port){
        this.appliance=appliance;
        this.port=port;
    }

    public SABaseURL(String appliance, String port, boolean useSSL){
        this.appliance=appliance;
        this.port=port;
        this.useSSL=useSSL;
    }

    public SABaseURL(String appliance, String port, boolean useSSL, boolean selfSigned){
        this.appliance=appliance;
        this.port=port;
        this.useSSL=useSSL;
        this.selfSigned=selfSigned;
    }


    public String getAppliance() {
        return appliance;
    }

    public void setAppliance(String appliance) {
        this.appliance = appliance;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public boolean isSelfSigned() {
        return selfSigned;
    }

    public void setSelfSigned(boolean selfSigned) {
        this.selfSigned = selfSigned;
    }
}
