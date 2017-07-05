package ro.dcsi.internship;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONObject;

public class ForgeRockDBIterator implements Iterator<User> {
  private final ForgeRockUserDao database;
  private Queue<String> userIds = new LinkedList<String>();

  public ForgeRockDBIterator(ForgeRockUserDao database) {
    this.database = database;
    this.fetchUserList();
  }

  private void fetchUserList() {
    Map<String, String> headers = this.database.basicIDMHeader();
    HTTPRequest request = new HTTPRequest(
        database.config.openIDMServer + "/openidm/managed/user?_queryId=query-all-ids", "GET",
        headers);
    database.incCallsToServer("fetchUsers");
    HTTPResponse response = request.send();

    if (response.code == 200) {
      JSONObject userList = new JSONObject(response.response);
      JSONArray result = userList.getJSONArray("result");
      for (Object obj : result) {
        JSONObject user = (JSONObject) obj;
        this.userIds.add((String) user.get("_id"));
      }
    } else {
      throw new RuntimeException(
          "Error while connecting to server with request " + request + ".\nRecieved response " + response + ".");
    }
  }

  public boolean hasNext() {
    while (!this.userIds.isEmpty() && !this.database.userExists(this.userIds.peek())) {
      this.userIds.remove();
    }
    if (!this.userIds.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public User next() {
    if (this.hasNext()) {
      String id = this.userIds.remove();
      User user = this.database.getUser(id).get();
      if (user != null) {
        return user;
      } else {
        throw new RuntimeException("Error loading next user: user " + user
            + " probably was in the database earlier but is no longer present.");
      }
    } else {
      throw new RuntimeException("Error loading next user: no other users present in the database.");
    }
  }
}
