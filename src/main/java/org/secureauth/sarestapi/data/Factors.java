package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.secureauth.sarestapi.resources.s;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author rrowcliffe@secureauth.com
 *
 *
 * Copyright 2018 SecureAuth Corporation
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
 *
 */
@XmlRootElement(name= s.FACTORS)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Factors {

    private String type;
    private String id;
    private String value;
    private ArrayList<String> capabilities;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ArrayList<String> capabilities) {
        this.capabilities = capabilities;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\t").append("Type:").append(type);
        stringBuilder.append("\n\t\t").append("ID:").append(id);
        stringBuilder.append("\n\t\t").append("Value:").append(value);
        stringBuilder.append("\n\t\t").append("Capabilities:");
        if(capabilities != null) {
            for (String capability : capabilities) {
                stringBuilder.append("\n\t\t\t").append(capability);
            }
        }
        return stringBuilder.toString();
    }
}
