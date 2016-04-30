package org.secureauth.sarestapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioRequest {

    private String userId;
    private String beviorProfile;
    private String hostAddress;
    private String userAgent;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBeviorProfile() {
        return beviorProfile;
    }

    public void setBeviorProfile(String beviorProfile) {
        this.beviorProfile = beviorProfile;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
