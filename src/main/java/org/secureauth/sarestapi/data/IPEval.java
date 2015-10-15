package org.secureauth.sarestapi.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

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
@XmlRootElement(name="IPEval")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class IPEval {

    private String status;
    private String message;
    private IPEvaluation ip_evaluation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IPEvaluation getIp_evaluation() {
        return ip_evaluation;
    }

    public void setIp_evaluation(IPEvaluation ip_evaluation) {
        this.ip_evaluation = ip_evaluation;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t").append("Status:").append(status);
        stringBuilder.append("\n\t").append("Message:").append(message);
        stringBuilder.append("\n\t").append("IP Evaluation:").append(ip_evaluation);
        return stringBuilder.toString();
    }
}
