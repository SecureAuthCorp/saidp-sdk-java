
package org.secureauth.sarestapi.data.DFP;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "architecture"
})
public class UaCPU {

    @JsonProperty("architecture")
    private Object architecture;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("architecture")
    public Object getArchitecture() {
        return architecture;
    }

    @JsonProperty("architecture")
    public void setArchitecture(Object architecture) {
        this.architecture = architecture;
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
