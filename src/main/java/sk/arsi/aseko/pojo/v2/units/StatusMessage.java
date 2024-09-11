
package sk.arsi.aseko.pojo.v2.units;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "__typename",
    "type",
    "severity",
    "message",
    "detail"
})
@Generated("jsonschema2pojo")
public class StatusMessage {

    @JsonProperty("__typename")
    public String typename;
    @JsonProperty("type")
    public String type;
    @JsonProperty("severity")
    public String severity;
    @JsonProperty("message")
    public String message;
    @JsonProperty("detail")
    public String detail;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
