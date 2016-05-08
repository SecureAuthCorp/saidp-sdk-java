package org.secureauth.sarestapi.data.Response;

/**
 * @author rrowcliffe@secureauth.com
 *
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.Factors;
import org.secureauth.sarestapi.util.JSONUtil;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(Factors.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorsResponse extends BaseResponse{

    private String user_id;
    private ArrayList<Factors> factors = new ArrayList<Factors>();

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Factors> getFactors() {
        return factors;
    }

    public void setFactors(ArrayList<Factors> factors) {
        this.factors = factors;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
