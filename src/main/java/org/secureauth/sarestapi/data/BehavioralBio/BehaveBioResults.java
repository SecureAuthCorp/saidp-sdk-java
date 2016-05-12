package org.secureauth.sarestapi.data.BehavioralBio;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.Response.BaseResponse;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResults extends BaseResponse{

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


}
