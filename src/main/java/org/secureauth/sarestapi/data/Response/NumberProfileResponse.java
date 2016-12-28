package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.secureauth.sarestapi.data.NumberProfile.NumberProfileResult;
import org.secureauth.sarestapi.util.JSONUtil;

/**
 * Created by rrowcliffe on 12/27/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "numberProfileResult",
        "status",
        "message"
})
public class NumberProfileResponse extends BaseResponse{

    @JsonProperty("numberProfileResult")
    private NumberProfileResult numberProfileResult;


    /**
     * No args constructor for use in serialization
     *
     */
    public NumberProfileResponse() {
    }

    /**
     *
     * @param numberProfileResult Result of request The Actual Object of Data
     */
    public NumberProfileResponse(NumberProfileResult numberProfileResult) {
        super();
        this.numberProfileResult = numberProfileResult;

    }

    @JsonProperty("numberProfileResult")
    public NumberProfileResult getNumberProfileResult() {
        return numberProfileResult;
    }

    @JsonProperty("numberProfileResult")
    public void setNumberProfileResult(NumberProfileResult numberProfileResult) {
        this.numberProfileResult = numberProfileResult;
    }

    public NumberProfileResponse withNumberProfileResult(NumberProfileResult numberProfileResult) {
        this.numberProfileResult = numberProfileResult;
        return this;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }

}
