package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 5/2/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewUserProfile {
    private String userId;
    private String password;
    private NewUserProfileProperties properties;
    private UserProfileKBAKBQ knowledgeBase;
    private UserProfileGroups groups;
    private UserProfileAccessHistories accessHistories;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NewUserProfileProperties getProperties() {
        return properties;
    }

    public void setProperties(NewUserProfileProperties properties) {
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
}
