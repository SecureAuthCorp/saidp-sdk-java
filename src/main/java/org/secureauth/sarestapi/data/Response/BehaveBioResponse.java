package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.secureauth.sarestapi.data.BehavioralBio.BehaveBioResult;
import org.secureauth.sarestapi.data.BehavioralBio.BehaveBioResults;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResponse extends BaseResponse{
    @JsonProperty("BehaviorBioResults")
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
