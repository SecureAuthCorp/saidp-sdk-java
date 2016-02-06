package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

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
