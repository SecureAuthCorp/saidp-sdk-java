package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.BehavioralBio.BehaveBioResult;
import org.secureauth.sarestapi.util.JSONUtil;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@XmlRootElement (name="BehaviorBioResults")
@XmlSeeAlso(BehaveBioResult.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResponse extends BaseResponse{

    private double TotalScore;
    private double TotalConfidence;
    private String Device;
    private List<BehaveBioResult> Results = new ArrayList<BehaveBioResult>();

    public double getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(double totalScore) {
        TotalScore = totalScore;
    }

    public double getTotalConfidence() {
        return TotalConfidence;
    }

    public void setTotalConfidence(double totalConfidence) {
        TotalConfidence = totalConfidence;
    }

    public void setResults(List<BehaveBioResult> results) {
        Results = results;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public List<BehaveBioResult> getResults() {
        return Results;
    }

    public void setResults(ArrayList<BehaveBioResult> results) {
        Results = results;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
