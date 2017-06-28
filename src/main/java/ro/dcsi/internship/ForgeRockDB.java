package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgeRockDB implements UserDB {
  public final String openIDMServer, openIDMUsername, openIDMPassword;

  public ForgeRockDB(String openIDMServer, String openIDMUsername, String openIDMPassword) {
    this.openIDMServer = openIDMServer;
    this.openIDMUsername = openIDMUsername;
    this.openIDMPassword = openIDMPassword;
  }

  public static JSONObject userToJSONObject(User user) {
    /* TODO extend User instead of static method */
    JSONObject object = new JSONObject();
    for (String key : user.getAttributeSet()) {
      object.put(key, user.getAttributeValue(key));
    }
    object.put("_id", user.getId());
    return object;
  }

  public static String userToJSONString(User user) {
    /* TODO extend User instead of static method */
    JSONObject object = ForgeRockDB.userToJSONObject(user);
    return object.toString();
  }

  /* TODO jsonToUser methods */

  public Iterator<User> iterator() {
    return new ForgeRockDBIterator(this);
  }

  public Map<String, String> authenticationHeader() {
    Map<String, String> header = new Hashtable<String, String>();
    header.put("X-OpenIDM-Username", this.openIDMUsername);
    header.put("X-OpenIDM-Password", this.openIDMPassword);
    return header;
  }

  public Map<String, String> basicIDMHeader() {
    Map<String, String> header = this.authenticationHeader();
    header.put("Content-Type", "application/json");
    return header;
  }

  public User getUser(String id) {
    HTTPRequest request = new HTTPRequest(this.openIDMServer + "/openidm/managed/user/" + id, "GET",
        this.basicIDMHeader());
    HTTPResponse response = request.send();
    JSONObject jsonUser = null;
    try {
      jsonUser = new JSONObject(response.response);
    } catch (JSONException e) {
      throw new RuntimeException("When response came "+response+" an exception was thrown with "+request+" .", e);
    }
    if (response.code == 200) {
      Map<String, String> attributes = new Hashtable<String, String>();
      for (String attr : jsonUser.toMap().keySet()) {
        if (jsonUser.get(attr) instanceof String) {
          attributes.put(attr, (String) jsonUser.get(attr));
        }
      }
      System.out.println(jsonUser);
      User user = new User(jsonUser.getString("_id"), attributes);
      return user;
    } else {
      return null;
    }
  }

  public boolean userExists(String id) {
    HTTPRequest request = new HTTPRequest(this.openIDMServer + "/openidm/managed/user/" + id, "GET",
        this.basicIDMHeader());
    HTTPResponse response = request.send();

    return (response.code == 200);
  }

  public boolean deleteUser(String id) {
    HTTPRequest request = new HTTPRequest(this.openIDMServer + "/openidm/managed/user/" + id, "DELETE",
        this.basicIDMHeader());
    HTTPResponse response = request.send();

    return (response.code == 200);
  }

  public boolean updateUser(User user) {
    Map<String, String> headers = this.basicIDMHeader();
    headers.put("data", ForgeRockDB.userToJSONString(user));
    HTTPRequest request = new HTTPRequest(this.openIDMServer + "/openidm/managed/user/" + user.getId(), "PUT", headers);
    HTTPResponse response = request.send();

    return (response.code == 201);
  }
}
