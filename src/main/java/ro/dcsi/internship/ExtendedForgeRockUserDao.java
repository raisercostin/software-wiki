package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtendedForgeRockUserDao implements IterableUserDao {
  private int requestsToServer = 0;
  OpenIdConfig config;

  public ExtendedForgeRockUserDao(String openIDMServer, String openIDMUsername, String openIDMPassword) {
    this(new OpenIdConfig(openIDMServer, openIDMUsername, openIDMPassword));
  }

  public ExtendedForgeRockUserDao(OpenIdConfig config) {
    this.config = config;
  }

  public static JSONObject userToJSONObject(ExtendedUser user) {
    /* TODO extend User instead of static method */
    JSONObject object = new JSONObject();
    for (String key : user.getAttributeSet()) {
      object.put(key, user.getAttributeValue(key));
    }
    object.put("_id", user.getId());
    return object;
  }

  public static String userToJSONString(ExtendedUser user) {
    /* TODO extend User instead of static method */
    JSONObject object = ExtendedForgeRockUserDao.userToJSONObject(user);
    return object.toString();
  }

  public Map<String, String> authenticationHeader() {
    Map<String, String> header = new Hashtable<String, String>();
    header.put("X-OpenIDM-Username", config.openIDMUsername);
    header.put("X-OpenIDM-Password", config.openIDMPassword);
    return header;
  }

  public Map<String, String> basicIDMHeader() {
    Map<String, String> header = this.authenticationHeader();
    header.put("Content-Type", "application/json");
    return header;
  }

  public Optional<ExtendedUser> getUser(String id) {
    HTTPRequest request = new HTTPRequest(config.openIDMServer + "/openidm/managed/user/" + id, "GET",
        this.basicIDMHeader());
    incCallsToServer("getUser" + id);
    HTTPResponse response = request.send();
    JSONObject jsonUser = null;
    try {
      jsonUser = new JSONObject(response.response);
    } catch (JSONException e) {
      throw new RuntimeException("When response came " + response + " an exception was thrown with " + request + " .",
          e);
    }
    if (response.code == 200) {
      Map<String, String> attributes = new Hashtable<String, String>();
      for (String attr : jsonUser.toMap().keySet()) {
        if (jsonUser.get(attr) instanceof String) {
          attributes.put(attr, (String) jsonUser.get(attr));
        }
      }
      ExtendedUser user = new ExtendedUser(jsonUser.getString("_id"), attributes);
      return Optional.of(user);
    } else {
      return Optional.empty();
    }
  }

  public boolean userExists(String id) {
    HTTPRequest request = new HTTPRequest(config.openIDMServer + "/openidm/managed/user/" + id, "GET",
        this.basicIDMHeader());
    incCallsToServer("exists " + id);
    HTTPResponse response = request.send();

    return (response.code == 200);
  }

  public boolean deleteUser(String id) {
    HTTPRequest request = new HTTPRequest(config.openIDMServer + "/openidm/managed/user/" + id, "DELETE",
        this.basicIDMHeader());
    incCallsToServer("delete " + id);
    HTTPResponse response = request.send();
    return (response.code == 200);
  }

  public boolean updateUser(ExtendedUser user) {
    Map<String, String> headers = this.basicIDMHeader();
    HTTPRequest request = new HTTPRequest(config.openIDMServer + "/openidm/managed/user/" + user.getId() + "#_update",
        "PUT", headers, ExtendedForgeRockUserDao.userToJSONString(user));
    incCallsToServer("update " + user.getId());
    HTTPResponse response = request.send();

    return (response.code == 200);
  }

  public boolean addUser(ExtendedUser user) {
    Map<String, String> headers = this.basicIDMHeader();
    if (!this.userExists(user.getId())) {
      HTTPRequest request = new HTTPRequest(config.openIDMServer + "/openidm/managed/user/" + user.getId(), "PUT",
          headers, ExtendedForgeRockUserDao.userToJSONString(user));
      incCallsToServer("add " + user.getId());
      HTTPResponse response = request.send();
      // System.out.println(request);
      // System.out.println(response);

      return (response.code == 201);
    } else {
      return false;
    }
  }

  public Iterator<ExtendedUser> read() {
    return this.iterator();
  }

  /* TODO jsonToUser methods */

  public Iterator<ExtendedUser> iterator() {
    return new ForgeRockDBIterator(this);
  }

  @Override
  public void write(Iterator<ExtendedUser> users) {
    ExtendedUser user = null;
    while (users.hasNext()) {
      user = users.next();
      this.addUser(user);
    }
  }

  public int requestsToServer() {
    return requestsToServer;
  }

  void incCallsToServer(String message) {
    System.out.println(message);
    requestsToServer++;
  }
}
