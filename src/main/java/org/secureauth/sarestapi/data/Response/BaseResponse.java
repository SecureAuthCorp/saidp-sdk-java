package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 5/8/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String status;
    private String message;
    @JsonProperty("user_id")
    private String userId;

    public BaseResponse() {
    }

    public BaseResponse(String status, String message, String user_id) {
        this.status = status;
        this.message = message;
        this.userId = user_id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
