package org.secureauth.sarestapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@XmlRootElement (name="BehaviorBioResults")
@XmlSeeAlso(BehaveBioResult.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResults {

    private double TotalScore;
    private double TotalConfidence;
    private String Device;
    private ArrayList<BehaveBioResult> Results = new ArrayList<BehaveBioResult>();

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

    public ArrayList<BehaveBioResult> getResults() {
        return Results;
    }

    public void setResults(ArrayList<BehaveBioResult> results) {
        Results = results;
    }
}
