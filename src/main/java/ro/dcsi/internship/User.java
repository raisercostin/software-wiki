package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class User {
  /* TODO add support for complex attributes */
  private final Hashtable<String, String> attributes = new Hashtable<String, String>();

  public User(String id, Map<String, String> attributes) {
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
}
