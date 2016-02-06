package org.secureauth.sarestapi.data;

/**
 * @author rrowcliffe@secureauth.com
 *
 */
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;

@XmlRootElement
@XmlSeeAlso(Factors.class)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t").append("Status:").append(status);
        stringBuilder.append("\n\t").append("Message:").append(message);
        stringBuilder.append("\n\t").append("User_ID:").append(user_id);
        stringBuilder.append("\n\t").append("Factors:").append(factors);
        return stringBuilder.toString();
    }
}
