package org.secureauth.sarestapi.data.UserProfile;

/**
 * Created by rrowcliffe on 5/2/16.
 */
public class UserProfile {
    private UserProfileProperties properties;
    private UserProfileKBAKBQ knowledgeBase;
    private UserProfileGroups groups;
    private UserProfileAccessHistories accessHistories;

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
}
