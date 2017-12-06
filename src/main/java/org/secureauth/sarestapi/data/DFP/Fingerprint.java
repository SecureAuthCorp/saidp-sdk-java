
package org.secureauth.sarestapi.data.DFP;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fingerprint"
})
public class Fingerprint {

    @JsonProperty("fingerprint")
    private Fingerprint_ fingerprint;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fingerprint")
    public Fingerprint_ getFingerprint() {
        return fingerprint;
    }

    @JsonProperty("fingerprint")
    public void setFingerprint(Fingerprint_ fingerprint) {
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

}
