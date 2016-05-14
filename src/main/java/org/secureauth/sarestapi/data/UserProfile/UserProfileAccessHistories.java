package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="accessHistories")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileAccessHistories {
    private ArrayList<UserProfileAccessHistory> accessHistories = new ArrayList<>();

    public ArrayList<UserProfileAccessHistory> getAccessHistories() {
        return accessHistories;
    }

    public void setAccessHistories(ArrayList<UserProfileAccessHistory> accessHistories) {
        this.accessHistories = accessHistories;
    }
}
