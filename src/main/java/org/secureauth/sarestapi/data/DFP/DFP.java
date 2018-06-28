
package org.secureauth.sarestapi.data.DFP;

import com.fasterxml.jackson.annotation.*;
import org.secureauth.sarestapi.util.JSONUtil;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "user_id",
    "host_address",
    "fingerprint"
})
public class DFP {

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("host_address")
    private String hostAddress;
    @JsonProperty("fingerprint")
    private Fingerprint fingerprint;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("host_address")
    public String getHostAddress() {
        return hostAddress;
    }

    @JsonProperty("host_address")
    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    @JsonProperty("fingerprint")
    public Fingerprint getFingerprint() {
        return fingerprint;
    }

    @JsonProperty("fingerprint")
    public void setFingerprint(Fingerprint fingerprint) {
        this.fingerprint = fingerprint;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }

}
