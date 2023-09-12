package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.util.JSONUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PreferredMFA {

    private String factorId;
    private String capability;

    public String getFactorId() {
        return factorId;
    }
    public void setFactorId(String factorId) {
        this.factorId = factorId;
    }
    public String getCapability() {
        return capability;
    }
    public void setCapability(String capability) {
        this.capability = capability;
    }
    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }

}
