package ro.dcsi.internship;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RawUser {
  /* TODO add support for complex attributes */
  protected final Map<String, String> attributes = new TreeMap<String, String>();

  public RawUser(String id, Map<String, String> attributes) {
    this.attributes.putAll(attributes);
    this.attributes.put("_id", id);
  }

  public Set<String> getAttributeSet() {
    return attributes.keySet();
  }

  public String getAttributeValue(String attribute) {
    return attributes.get(attribute);
  }

  public String getId() {
    return attributes.get("_id");
  }

  public String toString() {
    return this.getClass().getSimpleName() + " [attributes=" + attributes + "]";
  }
}
