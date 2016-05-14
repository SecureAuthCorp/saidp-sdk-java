package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="properties")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  UserProfileProperties {

    private HashMap<String,UserProfileProperty> properties = new HashMap<>();

    public HashMap<String, UserProfileProperty> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, UserProfileProperty> properties) {
        this.properties = properties;
    }
}
