package org.secureauth.sarestapi.data.UserProfile;

/**
 * Created by rrowcliffe on 5/8/16.
 */
public class UserToGroups {
    private String[] groupNames;

    public UserToGroups(){}

    public UserToGroups(String... groupNames) {
        this.groupNames = groupNames;
    }

    public String[] getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(String... groupNames) {
        this.groupNames = groupNames;
    }
}
