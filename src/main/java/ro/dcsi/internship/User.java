package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class User {
  public String username;
  public String email;
  public String firstName;
  public String lastName;
  /* TODO add support for complex attributes */
  private final Hashtable<String, String> attributes = new Hashtable<String, String>();

  public User(String username, String email, String firstName, String lastName) {
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  public User(String id, Map<String, String> attributes) {
    this.attributes.putAll(attributes);
    this.attributes.put("_id", id);
  }

  @Override
  public String toString() {
    return "User [username=" + username + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
        + "]";
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
