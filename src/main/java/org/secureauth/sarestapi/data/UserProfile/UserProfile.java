package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * Created by rrowcliffe on 5/2/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfile {
    private HashMap<String,UserProfileProperty> properties = new HashMap<>();
    private UserProfileKBAKBQ knowledgeBase;
    private UserProfileGroups groups;
    private UserProfileAccessHistories accessHistories;

    public HashMap<String, UserProfileProperty> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, UserProfileProperty> properties) {
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
