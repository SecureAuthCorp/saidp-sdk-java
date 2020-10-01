package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="knowledgeBase")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileKBAKBQ {

    private Map<String,UserProfileKB> knowledgeBase = new LinkedHashMap<>();


    public Map<String, UserProfileKB> getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(Map<String, UserProfileKB> knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }
}
