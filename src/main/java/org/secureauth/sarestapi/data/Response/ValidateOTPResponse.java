package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 5/8/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidateOTPResponse {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
