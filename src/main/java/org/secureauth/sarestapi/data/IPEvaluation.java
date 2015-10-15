package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name="ip_evaluation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class IPEvaluation {

    private String method;
    private String ip;
    private int risk_factor;
    private String risk_color;
    private String risk_desc;
    private Geoloc geoloc;
    private Factoring factoring;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getRisk_factor() {
        return risk_factor;
    }

    public void setRisk_factor(int risk_factor) {
        this.risk_factor = risk_factor;
    }

    public String getRisk_color() {
        return risk_color;
    }

    public void setRisk_color(String risk_color) {
        this.risk_color = risk_color;
    }

    public String getRisk_desc() {
        return risk_desc;
    }

    public void setRisk_desc(String risk_desc) {
        this.risk_desc = risk_desc;
    }

    public Geoloc getGeoloc() {
        return geoloc;
    }

    public void setGeoloc(Geoloc geoloc) {
        this.geoloc = geoloc;
    }

    public Factoring getFactoring() {
        return factoring;
    }

    public void setFactoring(Factoring factoring) {
        this.factoring = factoring;
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\t").append("Method:").append(method);
        stringBuilder.append("\n\t\t").append("IP:").append(ip);
        stringBuilder.append("\n\t\t").append("Risk Factor:").append(risk_factor);
        stringBuilder.append("\n\t\t").append("Risk Color:").append(risk_color);
        stringBuilder.append("\n\t\t").append("Risk Description:").append(risk_desc);
        stringBuilder.append("\n\t\t").append("geoloc:").append(geoloc);
        stringBuilder.append("\n\t\t").append("factoring:").append(factoring);
        return stringBuilder.toString();
    }
}
