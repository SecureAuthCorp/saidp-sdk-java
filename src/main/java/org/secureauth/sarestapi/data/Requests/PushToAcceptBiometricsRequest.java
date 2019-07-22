package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushToAcceptBiometricsRequest extends PushToAcceptRequest {

    private String biometricType;

    public void setBiometricType(String biometricType) {
        this.biometricType = biometricType;
    }
}

