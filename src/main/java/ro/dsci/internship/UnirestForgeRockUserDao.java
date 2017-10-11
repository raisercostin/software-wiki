package ro.dsci.internship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestForgeRockUserDao implements UserDao {
  public String generateUniqueId() {
    return UUID.randomUUID().toString();
  }

  public String url = "http://localhost:8080";
  public String userLogIn = "openidm-admin";

  @Override
  public List<User> readUsers(String locatie) {
    try {
      HttpResponse<JsonNode> getResponse = Unirest
          .get(url + "/openidm/managed/user?_prettyPrint=true&_queryId=query-all")
          .header("Accept", "application/json").header("Content-Type", "application/json")
          .header("X-Requested-With", "Swagger-UI").header("X-OpenIDM-Username", userLogIn)
          .header("X-OpenIDM-Password", userLogIn).asJson();

      JSONObject body = getResponse.getBody().getObject();
      // System.out.println(body.toString(4));
      JSONArray users = body.getJSONArray("result");
      List<User> result = new ArrayList<>();
      for (int i = 0, maxi = users.length(); i < maxi; i++) {
        result.add(toUser((JSONObject) users.get(i)));
      }
      result.forEach(System.out::println);
      return result;
    } catch (UnirestException e) {
      throw new RuntimeException("Wrapped checked exception.", e);
    }
  }

  private User toUser(JSONObject object) {
    return new User(object.getString("_id"), object.getString("userName"), object.getString("givenName"),
        object.getString("sn"), object.getString("mail"));
  }

  private JSONObject userToJSONObject(User user) {
    JSONObject object = new JSONObject();
    object.put("_id", user.getId());
    object.put("userName", user.getUsername());
    object.put("givenName", user.getFirstname());
    object.put("sn", user.getLastname());
    object.put("mail", user.getEmail());
    return object;

  }

  @Override
  public void writeUsers(List<User> users, String locatie) {
    for (int i = 0; i < users.size(); i++) {
      User user = users.get(i);
      user.id = makeItUnique(user.id);
      user.username = makeItUnique(user.username);
      JSONObject userAsJson = userToJSONObject(user);

      try {
        String urlid = url + "/openidm/managed/user/" + user.id;
        HttpResponse<JsonNode> jsonResponse = Unirest.put(urlid).header("Content-Type", "application/json")
            .header("Accept", "application/json").header("If-None-Match", " *").header("X-OpenIDM-Username", userLogIn)
            .header("X-OpenIDM-Password", userLogIn).header("X-Requested-With", "Swagger-UI").body(userAsJson).asJson();

        if (jsonResponse.getStatus() < 200 || jsonResponse.getStatus() >= 300) {
          throw new UserSyncException(
              "Request to server [" + urlid + "] returned with wrong http status " + jsonResponse.getStatus() + " ("
                  + jsonResponse.getStatusText() + "). Full body response:" + jsonResponse.getBody());
        }
      } catch (UnirestException e) {
        throw new RuntimeException("Wrapped checked exception.", e);
      }
    }
  }

  private String makeItUnique(String text) {
    return text + "--" + generateUniqueId();
  }

  public void deleteAllEntries() {
    List<User> users = this.readUsers("");

    for (int i = 0; i < users.size(); i++) {
      User user = users.get(i);
      JSONObject Json = userToJSONObject(user);

      try {
        HttpResponse<JsonNode> jsonResponse = Unirest
            .delete("http://localhost:8080/openidm/managed/user/" + user.getId())
            .header("Content-Type", "application/json").header("Accept", "application/json")

            .header("X-OpenIDM-Username", "openidm-admin").header("X-OpenIDM-Password", "openidm-admin")
            .header("X-Requested-With", "Swagger-UI").body(Json).asJson();

        System.out.println(jsonResponse.getBody().toString());
      } catch (UnirestException e) {

        e.printStackTrace();
        throw new RuntimeException("Wrapped checked exception.", e);
      }
    }

  }
}
