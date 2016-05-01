package org.secureauth.sarestapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileGroups {

    private ArrayList<String> groups = new ArrayList<>();

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }
}
