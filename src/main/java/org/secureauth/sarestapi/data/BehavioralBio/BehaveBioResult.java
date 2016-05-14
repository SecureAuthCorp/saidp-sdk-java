package org.secureauth.sarestapi.data.BehavioralBio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResult {
    @JsonProperty("ControlID")
    private String controlID;
    @JsonProperty("Score")
    private double score;
    @JsonProperty("Confidence")
    private double confidence;
    @JsonProperty("Count")
    private int count;

    public String getControlID() {
        return controlID;
    }

    public void setControlID(String controlID) {
        this.controlID = controlID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
