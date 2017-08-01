package ro.dcsi.internship;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.raisercostin.jedi.Locations;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Cristi on 27-Jul-17.
 */
public class ForgerockUserDao implements UserDao {
  private String serverUrl;
  private String serverUsername;
  private String serverPassword;

  public ForgerockUserDao(String serverUrl, String serverUsername, String serverPassword) {
    this.serverUrl = serverUrl;
    this.serverUsername = serverUsername;
    this.serverPassword = serverPassword;
  }

  @Override
  public void writeUsers(TheUser... users) {
    int idStart = 0;
    List<TheUser> theUserList = Arrays.asList(users);
    int stop = theUserList.size() + idStart;
    int start = idStart;
    //TODO batch exception handling 
    try {
      for (int i = start, j = 0; j < theUserList.size() && i < stop; j++, i++) {
        String id = theUserList.get(j).id;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("mail", theUserList.get(j).getEmail());
        jsonObject.put("sn", theUserList.get(j).getLastname());
        jsonObject.put("givenName", theUserList.get(j).getFirstname());
        jsonObject.put("userName", theUserList.get(j).getUsername());
        String jsonResult = jsonObject.toString();
        StringEntity jsonEntity = new StringEntity(jsonResult);

        connectToServerAndPut(id, jsonEntity);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void connectToServerAndPut(String id, StringEntity jsonEntity) throws IOException, ClientProtocolException {
    String url = serverUrl + "openidm/managed/user/" + id;

    HttpClient client = HttpClientBuilder.create().build();
    HttpPut request = new HttpPut(url);
    request.addHeader("Content-Type", "application/json");
    request.addHeader("Accept", "application/json");
    request.addHeader("If-None-Match", "*");
    request.addHeader("X-OpenIDM-Username", serverUsername);
    request.addHeader("X-OpenIDM-Password", serverPassword);
    request.addHeader("X-Requested-With", "Swagger-UI");
    request.setEntity(jsonEntity);

    HttpResponse response = client.execute(request);
    String entity = EntityUtils.toString(response.getEntity());
    int responseCode = response.getStatusLine().getStatusCode();
    if (responseCode < 200 || responseCode >= 300) {
      throw new RuntimeException("Error code=" + response + "\ncontent=" + entity);
    }
  }

  //TODO ignoring filename is a bit surprising
  @Override
  public List<TheUser> readUsers() {
    List<TheUser> theUserList = new ArrayList<>();

    try {
      JSONObject result = new JSONObject(connectToServerGet());
      JSONArray jsonArray = result.optJSONArray("result");
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        String sn = jsonObject.getString("sn");
        String givenName = jsonObject.getString("givenName");
        String userName = jsonObject.getString("userName");
        String mail = jsonObject.getString("mail");

        theUserList.add(new TheUser(userName, givenName, sn, mail));
      }
    } catch (JSONException e) {
      throw new JSONException(e);
    }
    return theUserList;
  }

  //TODO review for 10G response
  private String connectToServerGet() {
    String url = serverUrl + "/openidm/managed/user?_queryId=query-all";

    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url);

    request.addHeader("Accept", "application/json");
    request.addHeader("X-Requested-With", "Swagger-UI");
    request.addHeader("X-OpenIDM-Username", serverUsername);
    request.addHeader("X-OpenIDM-Password", serverPassword);
    try {
      HttpResponse response = client.execute(request);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line;
      StringBuffer jsonResponse = new StringBuffer();
      while ((line = bufferedReader.readLine()) != null) {
        jsonResponse.append(line);
      }
      return jsonResponse.toString();
    } catch (IOException | UnsupportedOperationException e) {
      throw new RuntimeException(e);
    }
  }

  //TODO what backup ??? :D
  void backupUsers(List<TheUser> theUserList) {
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
    Date date = new Date();

    String target = "target/test-files/";
    String csvFile = target + "users_backup_" + dateFormat.format(date) + ".csv";

    Locations.current(csvFile).mkdirOnParentIfNecessary();

    TheUser[] theUser = theUserList.toArray(new TheUser[theUserList.size()]);

    new BeanBasedUserDao().writeUsers(csvFile, theUser);
  }
}
