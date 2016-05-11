package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rrowcliffe on 5/8/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupAssociationResponse extends BaseResponse {
    HashMap<String,ArrayList<String>> failures = new HashMap<>();

    public HashMap<String, ArrayList<String>> getFailures() {
        return failures;
    }

    public void setFailures(HashMap<String, ArrayList<String>> failures) {
        this.failures = failures;
    }
}
