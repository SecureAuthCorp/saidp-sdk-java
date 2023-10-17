package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author rrowcliffe@secureauth.com
 *
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.PreferredMFA;
import org.secureauth.sarestapi.util.JSONUtil;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(Factors.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactorsResponse extends BaseResponse {

	@JsonProperty("factors")
    private ArrayList<Factors> factors = new ArrayList<Factors>();

	@JsonProperty("preferred_mfa")
    public PreferredMFA preferredMfa = new PreferredMFA();
  
    public ArrayList<Factors> getFactors() {
        return factors;
    }
    public void setFactors(ArrayList<Factors> factors) {
        this.factors = factors;
    }
    public PreferredMFA getPreferredMfa() {
        return preferredMfa;
    }
    public void setPreferredMfa(PreferredMFA preferredMfa) {
        this.preferredMfa = preferredMfa;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
