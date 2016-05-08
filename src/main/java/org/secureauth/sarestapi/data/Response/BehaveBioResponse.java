package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.BehavioralBio.BehaveBioResults;
import org.secureauth.sarestapi.data.Response.BaseResponse;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResponse extends BaseResponse{

    private BehaveBioResults behaveBioResults;


    public BehaveBioResults getBehaveBioResults() {
        return behaveBioResults;
    }

    public void setBehaveBioResults(BehaveBioResults behaveBioResults) {
        this.behaveBioResults = behaveBioResults;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
