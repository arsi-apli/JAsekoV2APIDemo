
package sk.arsi.aseko.pojo.v2.unit;

import java.util.LinkedHashMap;
import java.util.List;
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
    "serialNumber",
    "name",
    "note",
    "brandName",
    "statusMessages",
    "heating",
    "waterFilling",
    "consumables",
    "notificationConfiguration",
    "unitModel"
})
@Generated("jsonschema2pojo")
public class UnitBySerialNumber {

    @JsonProperty("__typename")
    public String typename;
    @JsonProperty("serialNumber")
    public String serialNumber;
    @JsonProperty("name")
    public String name;
    @JsonProperty("note")
    public Object note;
    @JsonProperty("brandName")
    public BrandName brandName;
    @JsonProperty("statusMessages")
    public List<Object> statusMessages;
    @JsonProperty("heating")
    public Heating heating;
    @JsonProperty("waterFilling")
    public WaterFilling waterFilling;
    @JsonProperty("consumables")
    public List<Object> consumables;
    @JsonProperty("notificationConfiguration")
    public List<NotificationConfiguration> notificationConfiguration;
    @JsonProperty("unitModel")
    public UnitModel unitModel;
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
