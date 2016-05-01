package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.util.JSONUtil;

/**
 * Created by rrowcliffe on 5/1/16.
 */
public class UserProfileResponse {
    private String userId;
    private UserProfileProperties properties;
    private UserProfileKBAKBQ knowledgeBase;
    private UserProfileGroups groups;
    private UserProfileAccessHistories accessHistories;
    private String status;
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserProfileProperties getProperties() {
        return properties;
    }

    public void setProperties(UserProfileProperties properties) {
        this.properties = properties;
    }

    public UserProfileKBAKBQ getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(UserProfileKBAKBQ knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public UserProfileGroups getGroups() {
        return groups;
    }

    public void setGroups(UserProfileGroups groups) {
        this.groups = groups;
    }

    public UserProfileAccessHistories getAccessHistories() {
        return accessHistories;
    }

    public void setAccessHistories(UserProfileAccessHistories accessHistories) {
        this.accessHistories = accessHistories;
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
