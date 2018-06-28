
package org.secureauth.sarestapi.data.DFP;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "maxTouchPoints",
    "touchEvent",
    "touchStart"
})
public class TouchSupport {

    @JsonProperty("maxTouchPoints")
    private Integer maxTouchPoints;
    @JsonProperty("touchEvent")
    private Boolean touchEvent;
    @JsonProperty("touchStart")
    private Boolean touchStart;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("maxTouchPoints")
    public Integer getMaxTouchPoints() {
        return maxTouchPoints;
    }

    @JsonProperty("maxTouchPoints")
    public void setMaxTouchPoints(Integer maxTouchPoints) {
        this.maxTouchPoints = maxTouchPoints;
    }

    @JsonProperty("touchEvent")
    public Boolean getTouchEvent() {
        return touchEvent;
    }

    @JsonProperty("touchEvent")
    public void setTouchEvent(Boolean touchEvent) {
        this.touchEvent = touchEvent;
    }

    @JsonProperty("touchStart")
    public Boolean getTouchStart() {
        return touchStart;
    }

    @JsonProperty("touchStart")
    public void setTouchStart(Boolean touchStart) {
        this.touchStart = touchStart;
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
