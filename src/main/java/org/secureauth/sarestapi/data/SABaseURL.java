package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.resources.s;
/**
 * @author rrowcliffe@secureauth.com
 *
 * <p>
 * Copyright 2015 SecureAuth Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */
public class SABaseURL {
    private String appliance = "localhost";
    private String port = "443";
    private boolean useSSL = true;


    public String getApplianceURL(){
        StringBuilder stringBuilder = new StringBuilder();
        if(useSSL){stringBuilder.append(s.HTTPS);}
        else{ stringBuilder.append(s.HTTP);}

        stringBuilder.append(appliance).append(s.COLON).append(port).append(s.SLASH);

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

}
