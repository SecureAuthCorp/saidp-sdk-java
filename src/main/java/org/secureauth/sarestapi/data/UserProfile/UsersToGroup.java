package org.secureauth.sarestapi.data.UserProfile;

import java.util.Arrays;

/**
 * Created by rrowcliffe on 5/8/16.
 */
public class UsersToGroup {
    private String[] userIds;

    public UsersToGroup(){}

    public UsersToGroup(String... userIds) {
        this.userIds = userIds;
    }

    public String[] getUserIds() {
        return Arrays.copyOf(userIds, userIds.length);
    }

    public void setUserIds(String... userIds) {
        this.userIds = userIds;
    }
}
