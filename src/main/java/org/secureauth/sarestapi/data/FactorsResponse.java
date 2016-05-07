package org.secureauth.sarestapi.data;

/**
 * @author rrowcliffe@secureauth.com
 *
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.util.JSONUtil;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(Factors.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorsResponse {

    private String status;
    private String message;
    private String user_id;
    private ArrayList<Factors> factors = new ArrayList<Factors>();


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
