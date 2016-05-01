package org.secureauth.sarestapi.data;

/**
 * Created by rrowcliffe on 5/1/16.
 */
public class UserProfileAccessHistory {

    private String userAgent;
    private String ipAddress;
    private String timeStamp;
    private String authState;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAuthState() {
        return authState;
    }

    public void setAuthState(String authState) {
        this.authState = authState;
    }
}
