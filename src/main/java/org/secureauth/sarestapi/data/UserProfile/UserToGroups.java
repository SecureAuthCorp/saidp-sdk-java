package org.secureauth.sarestapi.data.UserProfile;

import java.util.Arrays;

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
        return Arrays.copyOf(groupNames, groupNames.length);
    }

    public void setGroupNames(String... groupNames) {
        this.groupNames = groupNames;
    }
}
