package org.secureauth.sarestapi.data.Response;

/**
 * @author rrowcliffe@secureauth.com
 *
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.data.PreferredMFA;
import org.secureauth.sarestapi.util.JSONUtil;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(Factors.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorsResponse extends BaseResponse{

    private ArrayList<Factors> factors = new ArrayList<Factors>();
    public PreferredMFA preferred_mfa = new PreferredMFA();
    public ArrayList<Factors> getFactors() {
        return factors;
    }
    public void setFactors(ArrayList<Factors> factors) {
        this.factors = factors;
    }
    public PreferredMFA getPreferred_mfa() {
        return preferred_mfa;
    }
    public void setPreferred_mfa(PreferredMFA preferred_mfa) {
        this.preferred_mfa = preferred_mfa;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
