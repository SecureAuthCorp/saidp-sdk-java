package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="knowledgeBase")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileKBAKBQ {

    private HashMap<String,UserProfileKB> knowledgeBase = new HashMap<>();


    public HashMap<String, UserProfileKB> getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(HashMap<String, UserProfileKB> knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }
}
