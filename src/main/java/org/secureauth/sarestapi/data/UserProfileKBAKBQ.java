package org.secureauth.sarestapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="knowledgeBase")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileKBAKBQ {

    private HashMap<String,UserProfileKB> knowledegeBase = new HashMap<>();

    public HashMap<String, UserProfileKB> getKnowledegeBase() {
        return knowledegeBase;
    }

    public void setKnowledegeBase(HashMap<String, UserProfileKB> knowledegeBase) {
        this.knowledegeBase = knowledegeBase;
    }
}
