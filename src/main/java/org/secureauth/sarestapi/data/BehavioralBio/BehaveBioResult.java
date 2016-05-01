package org.secureauth.sarestapi.data.BehavioralBio;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 4/30/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BehaveBioResult {
    private String ControlID;
    private double Score;
    private double Confidence;
    private int Count;

    public String getControlID() {
        return ControlID;
    }

    public void setControlID(String controlID) {
        ControlID = controlID;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public double getConfidence() {
        return Confidence;
    }

    public void setConfidence(double confidence) {
        Confidence = confidence;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
