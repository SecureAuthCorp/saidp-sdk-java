package org.secureauth.sarestapi.data.Response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rrowcliffe on 5/8/16.
 */
public class FailureResponse extends BaseResponse {
    HashMap<String,ArrayList<String>> failures = new HashMap<>();

    public HashMap<String, ArrayList<String>> getFailures() {
        return failures;
    }

    public void setFailures(HashMap<String, ArrayList<String>> failures) {
        this.failures = failures;
    }
}
