package org.secureauth.sarestapi.data;

/**
 * Created by rrowcliffe on 5/1/16.
 */
public class UserProfileProperty {
    private String value;
    private boolean isWritable;
    private String displayName;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isWritable() {
        return isWritable;
    }

    public void setWritable(boolean writable) {
        isWritable = writable;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
