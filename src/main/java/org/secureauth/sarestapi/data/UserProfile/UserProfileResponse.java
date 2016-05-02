package org.secureauth.sarestapi.data.UserProfile;

import org.secureauth.sarestapi.util.JSONUtil;

/**
 * Created by rrowcliffe on 5/1/16.
 */
public class UserProfileResponse {
    private String userId;
    private UserProfile userProfile;
    private String status;
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
