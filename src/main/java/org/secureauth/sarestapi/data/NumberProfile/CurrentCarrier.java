package org.secureauth.sarestapi.data.NumberProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 12/27/16.
 */
@XmlRootElement(name="currentCarrier")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentCarrier {
    private String carrierCode;
    private String carrier;
    private String countryCode;
    private String networkType;
    private CarrierStatus carrierStatus;

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public CarrierStatus getCarrierStatus() {
        return carrierStatus;
    }

    public void setCarrierStatus(CarrierStatus carrierStatus) {
        this.carrierStatus = carrierStatus;
    }
}
