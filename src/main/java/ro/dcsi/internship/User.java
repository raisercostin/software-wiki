package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;

public class User {
  public String username;
  public String email;
  public final String firstName;
  public final String lastName;
  private final Permissions permissions;
  private List<String> extraFields = new ArrayList<>();
  private List<String> extraFieldHeaders = new ArrayList<>();

  public User(String username, String email, String firstName, String lastName, Permissions permissions) {
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.permissions = new Permissions(permissions);
  }

  public User(String username, String email, String firstName, String lastName, String permissions) {
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.permissions = new Permissions(permissions);
  }

  public User(String username, String email, String firstName, String lastName) {
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.permissions = new Permissions();
  }

  public User(String username) {
    this.username = username;
    this.email = "";
    this.firstName = "";
    this.lastName = "";
    this.permissions = new Permissions();
  }

  public User() {
    this("");
  }

  public User(User user) {
    this.username = user.username;
    this.email = user.email;
    this.firstName = user.firstName;
    this.lastName = user.lastName;
    this.permissions = new Permissions(user.permissions);
  }

  protected void addExtraField(String Value, String Header) {
    extraFields.add(Value);
    extraFieldHeaders.add(Header);
  }

  public List<String> getExtraFieldHeaders() {
    return new ArrayList<>(extraFieldHeaders);
  }

  public List<String> getExtraFields() {
    return new ArrayList<>(extraFields);
  }

  public boolean hasPermission(String permission) {
    return permissions.hasPermission(permission);
  }

  public void addPermission(String permission) {
    permissions.addPermission(permission);
  }

  public void removePermission(String permission) {
    permissions.removePermission(permission);
  }

  public void setPermission(String permission, boolean val) {
    permissions.setPermission(permission, val);
  }

  @Override
  public String toString() {
    return username + ": [" + email + ", " + firstName + ", " + lastName + ", " + permissions + "]";
  }

  public String toString2() {
    String rez;
    rez = "User{" + "name='" + username + '\'' + ", email='" + email + '\'';

    StringBuilder builder = new StringBuilder(rez);
    int i;

    for (i = 0; i < extraFields.size(); i++) {
      builder.append(", " + extraFieldHeaders.get(i) + "='" + extraFields.get(i) + '\'');
    }

    builder.append("}");
    return builder.toString();
  }

  public void setName(String name) {
    this.username = name;
  }

  public String getName() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
