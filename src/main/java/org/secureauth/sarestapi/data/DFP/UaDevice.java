
package org.secureauth.sarestapi.data.DFP;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "model",
    "type",
    "vendor"
})
public class UaDevice {

    @JsonProperty("model")
    private Object model;
    @JsonProperty("type")
    private Object type;
    @JsonProperty("vendor")
    private Object vendor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("model")
    public Object getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(Object model) {
        this.model = model;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("vendor")
    public Object getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(Object vendor) {
        this.vendor = vendor;
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
