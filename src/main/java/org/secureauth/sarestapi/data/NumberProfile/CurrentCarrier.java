package org.secureauth.sarestapi.data.NumberProfile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.secureauth.sarestapi.data.NumberProfile.CarrierStatus;
import org.secureauth.sarestapi.util.JSONUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "carrierCode",
        "carrier",
        "countryCode",
        "networkType",
        "carrierStatus"
})
public class CurrentCarrier {

    @JsonProperty("carrierCode")
    private String carrierCode;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("networkType")
    private String networkType;
    @JsonProperty("carrierStatus")
    private CarrierStatus carrierStatus;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrentCarrier() {
    }

    /**
     *
     * @param networkType
     * @param carrierStatus
     * @param countryCode
     * @param carrier
     * @param carrierCode
     */
    public CurrentCarrier(String carrierCode, String carrier, String countryCode, String networkType, CarrierStatus carrierStatus) {
        super();
        this.carrierCode = carrierCode;
        this.carrier = carrier;
        this.countryCode = countryCode;
        this.networkType = networkType;
        this.carrierStatus = carrierStatus;
    }

    @JsonProperty("carrierCode")
    public String getCarrierCode() {
        return carrierCode;
    }

    @JsonProperty("carrierCode")
    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public CurrentCarrier withCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
        return this;
    }

    @JsonProperty("carrier")
    public String getCarrier() {
        return carrier;
    }

    @JsonProperty("carrier")
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public CurrentCarrier withCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public CurrentCarrier withCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    @JsonProperty("networkType")
    public String getNetworkType() {
        return networkType;
    }

    @JsonProperty("networkType")
    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public CurrentCarrier withNetworkType(String networkType) {
        this.networkType = networkType;
        return this;
    }

    @JsonProperty("carrierStatus")
    public CarrierStatus getCarrierStatus() {
        return carrierStatus;
    }

    @JsonProperty("carrierStatus")
    public void setCarrierStatus(CarrierStatus carrierStatus) {
        this.carrierStatus = carrierStatus;
    }

    public CurrentCarrier withCarrierStatus(CarrierStatus carrierStatus) {
        this.carrierStatus = carrierStatus;
        return this;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
