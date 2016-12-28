package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.NumberProfile.CurrentCarrier;
import org.secureauth.sarestapi.data.NumberProfile.OriginalCarrier;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 12/27/16.
 */
@XmlRootElement(name="numberProfileResult")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberProfileResponse extends BaseResponse{
    private String providerRequestId;
    private String internationalFormat;
    private String nationalFormat;
    private String countryPrefix;
    private String countryCode;
    private String countryCodeISO3;
    private String country;
    private String portedStatus;
    private String validNumber;
    private String reachable;
    private String roamingInfo;
    private CurrentCarrier currentCarrier;
    private OriginalCarrier originalCarrier;
    private String ipInfo;
    private String ipWarning;

    public String getProviderRequestId() {
        return providerRequestId;
    }

    public void setProviderRequestId(String providerRequestId) {
        this.providerRequestId = providerRequestId;
    }

    public String getInternationalFormat() {
        return internationalFormat;
    }

    public void setInternationalFormat(String internationalFormat) {
        this.internationalFormat = internationalFormat;
    }

    public String getNationalFormat() {
        return nationalFormat;
    }

    public void setNationalFormat(String nationalFormat) {
        this.nationalFormat = nationalFormat;
    }

    public String getCountryPrefix() {
        return countryPrefix;
    }

    public void setCountryPrefix(String countryPrefix) {
        this.countryPrefix = countryPrefix;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCodeISO3() {
        return countryCodeISO3;
    }

    public void setCountryCodeISO3(String countryCodeISO3) {
        this.countryCodeISO3 = countryCodeISO3;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPortedStatus() {
        return portedStatus;
    }

    public void setPortedStatus(String portedStatus) {
        this.portedStatus = portedStatus;
    }

    public String getValidNumber() {
        return validNumber;
    }

    public void setValidNumber(String validNumber) {
        this.validNumber = validNumber;
    }

    public String getReachable() {
        return reachable;
    }

    public void setReachable(String reachable) {
        this.reachable = reachable;
    }

    public String getRoamingInfo() {
        return roamingInfo;
    }

    public void setRoamingInfo(String roamingInfo) {
        this.roamingInfo = roamingInfo;
    }

    public CurrentCarrier getCurrentCarrier() {
        return currentCarrier;
    }

    public void setCurrentCarrier(CurrentCarrier currentCarrier) {
        this.currentCarrier = currentCarrier;
    }

    public OriginalCarrier getOriginalCarrier() {
        return originalCarrier;
    }

    public void setOriginalCarrier(OriginalCarrier originalCarrier) {
        this.originalCarrier = originalCarrier;
    }

    public String getIpInfo() {
        return ipInfo;
    }

    public void setIpInfo(String ipInfo) {
        this.ipInfo = ipInfo;
    }

    public String getIpWarning() {
        return ipWarning;
    }

    public void setIpWarning(String ipWarning) {
        this.ipWarning = ipWarning;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
