
package sk.arsi.aseko.pojo.v2.unit;

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
    "id",
    "type",
    "name",
    "enabled",
    "lowWarningLevel",
    "highWarningLevel",
    "color",
    "currentValue",
    "suffix",
    "hasWarning",
    "possibleWarningLevels"
})
@Generated("jsonschema2pojo")
public class NotificationConfiguration {

    @JsonProperty("__typename")
    public String typename;
    @JsonProperty("id")
    public String id;
    @JsonProperty("type")
    public String type;
    @JsonProperty("name")
    public String name;
    @JsonProperty("enabled")
    public Boolean enabled;
    @JsonProperty("lowWarningLevel")
    public Object lowWarningLevel;
    @JsonProperty("highWarningLevel")
    public Object highWarningLevel;
    @JsonProperty("color")
    public Object color;
    @JsonProperty("currentValue")
    public Object currentValue;
    @JsonProperty("suffix")
    public Object suffix;
    @JsonProperty("hasWarning")
    public Boolean hasWarning;
    @JsonProperty("possibleWarningLevels")
    public Object possibleWarningLevels;
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
