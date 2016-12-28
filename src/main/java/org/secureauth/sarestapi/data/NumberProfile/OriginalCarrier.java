package org.secureauth.sarestapi.data.NumberProfile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.secureauth.sarestapi.util.JSONUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "carrierCode",
        "carrier",
        "countryCode",
        "networkType",
        "carrierStatus"
})
public class OriginalCarrier {

    @JsonProperty("carrierCode")
    private String carrierCode;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("networkType")
    private String networkType;
    @JsonProperty("carrierStatus")
    private Object carrierStatus;

    /**
     * No args constructor for use in serialization
     *
     */
    public OriginalCarrier() {
    }

    /**
     *
     * @param networkType
     * @param carrierStatus
     * @param countryCode
     * @param carrier
     * @param carrierCode
     */
    public OriginalCarrier(String carrierCode, String carrier, String countryCode, String networkType, Object carrierStatus) {
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

    public OriginalCarrier withCarrierCode(String carrierCode) {
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

    public OriginalCarrier withCarrier(String carrier) {
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

    public OriginalCarrier withCountryCode(String countryCode) {
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

    public OriginalCarrier withNetworkType(String networkType) {
        this.networkType = networkType;
        return this;
    }

    @JsonProperty("carrierStatus")
    public Object getCarrierStatus() {
        return carrierStatus;
    }

    @JsonProperty("carrierStatus")
    public void setCarrierStatus(Object carrierStatus) {
        this.carrierStatus = carrierStatus;
    }

    public OriginalCarrier withCarrierStatus(Object carrierStatus) {
        this.carrierStatus = carrierStatus;
        return this;
    }
    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
