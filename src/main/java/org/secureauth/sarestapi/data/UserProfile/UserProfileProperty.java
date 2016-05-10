package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileProperty {
    private String value;
    private String isWritable;
    private String displayName;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIsWritable() {
        return isWritable;
    }

    public void setIsWritable(String isWritable) {
        this.isWritable = isWritable;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
