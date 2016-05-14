package org.secureauth.sarestapi.data.BehavioralBio;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioRequest {

    private String userId;
    private String behaviorProfile;
    private String hostAddress;
    private String userAgent;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBehaviorProfile() {
        return behaviorProfile;
    }

    public void setBehaviorProfile(String behaviorProfile) {
        this.behaviorProfile = behaviorProfile;
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

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
