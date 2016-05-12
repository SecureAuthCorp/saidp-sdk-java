package org.secureauth.sarestapi.data.BehavioralBio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrowcliffe on 5/12/16.
 */
@XmlRootElement(name="BehaviorBioResults")
@XmlSeeAlso(BehaveBioResult.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResults {
    @JsonProperty("TotalScore")
    private double totalScore;
    @JsonProperty("TotalConfidence")
    private double totalConfidence;
    @JsonProperty("Device")
    private String device;

    @JsonProperty("Results")
    private List<BehaveBioResult> results = new ArrayList<BehaveBioResult>();

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getTotalConfidence() {
        return totalConfidence;
    }

    public void setTotalConfidence(double totalConfidence) {
        this.totalConfidence = totalConfidence;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public List<BehaveBioResult> getResults() {
        return results;
    }

    public void setResults(List<BehaveBioResult> results) {
        this.results = results;
    }
}
