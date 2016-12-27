package org.secureauth.sarestapi.data.NumberProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="carrierStatus")
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * Created by rrowcliffe on 12/27/16.
 */
public class CarrierStatus {
    private String status;
    private String[] reason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getReason() {
        return reason;
    }

    public void setReason(String[] reason) {
        this.reason = reason;
    }
}
