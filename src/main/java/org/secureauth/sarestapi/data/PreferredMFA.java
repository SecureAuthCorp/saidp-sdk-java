package org.secureauth.sarestapi.data;

import org.secureauth.sarestapi.util.JSONUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreferredMFA {
	 
	private String factorId;
	private String capability;
    private String type;
    private String mode;
    private String available_choosen_mode;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getAvailable_choosen_mode() {
        return available_choosen_mode;
    }
    public void setAvailable_choosen_mode(String available_choosen_mode) {
        this.available_choosen_mode = available_choosen_mode;
    }
    
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
