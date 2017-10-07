package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.raisercostin.jedi.Locations;

import com.google.common.annotations.VisibleForTesting;

import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

/**
 * Created by Cristi on 27-Jul-17.
 */
public class ForgerockUserDao implements UserDao {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ForgerockUserDao.class);
  private String serverUrl;
  private String serverUsername;
  private String serverPassword;

  public ForgerockUserDao(String serverUrl, String serverUsername, String serverPassword) {
    this.serverUrl = StringUtils.stripEnd(serverUrl, "/");
    this.serverUsername = serverUsername;
    this.serverPassword = serverPassword;
  }

  @Override
  public void save(User... users) {
    List<Pair<User, Try<String>>> result = writeUsersWithExceptions(users);
    int errors = 0;
    for (Pair<User, Try<String>> pair : result) {
      logger.info("For user " + pair.getLeft() + " operation got " + pair.getRight());
      if (pair.getRight().isFailure())
        errors += 1;
    }
    if (errors != 0) {
      throw new IllegalStateException("The write operation thrown at least one exception" + result);
    }
  }

  public List<Pair<User, Try<String>>> writeUsersWithExceptions(User... users) {
    List<Pair<User, Try<String>>> result = new ArrayList<>();
    for (User theUser : users) {
      logger.debug("saving " + theUser);
      try {
        String id = theUser.id;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("mail", theUser.getEmail());
        jsonObject.put("sn", theUser.getLastname());
        jsonObject.put("givenName", theUser.getFirstname());
        jsonObject.put("userName", theUser.getUsername());
        String jsonResult = jsonObject.toString();
        StringEntity jsonEntity = new StringEntity(jsonResult);

        connectToServerAndPut(id, jsonEntity);
        logger.debug("saved " + theUser);
        result.add(Pair.of(theUser, new Success<>("")));
      } catch (UnknownHostException e) {
        throw new WrappedCheckedException(e);
      } catch (WrappedCheckedException e) {
        // throw new WrappedCheckedException(e);
        // logger.warn("could't save " + theUser, e);
        result.add(Pair.of(theUser, new Failure(e)));
      } catch (UnsupportedEncodingException e) {
        // logger.warn("could't save " + theUser, e);
        result.add(Pair.of(theUser, new Failure(e)));
      }
    }
    return result;
  }

  // TODO ignoring filename is a bit surprising
  @Override
  public List<User> load() {
    List<User> theUserList = new ArrayList<>();

    try {
      JSONObject result = new JSONObject(connectToServerGet());
      JSONArray jsonArray = result.optJSONArray("result");
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        String sn = jsonObject.getString("sn");
        String givenName = jsonObject.getString("givenName");
        String userName = jsonObject.getString("userName");
        String mail = jsonObject.getString("mail");

        theUserList.add(new User(userName, givenName, sn, mail));
      }
    } catch (JSONException e) {
      throw new JSONException(e);
    }
    return theUserList;
  }

  private void connectToServerAndPut(String id, StringEntity jsonEntity) throws UnknownHostException {
    String url = serverUrl + "/openidm/managed/user/" + id;
    HttpPut request = new HttpPut(url);
    request.addHeader("Content-Type", "application/json");
    request.addHeader("Accept", "application/json");
    request.addHeader("If-None-Match", "*");
    request.addHeader("X-OpenIDM-Username", serverUsername);
    request.addHeader("X-OpenIDM-Password", serverPassword);
    request.addHeader("X-Requested-With", "Swagger-UI");
    request.setEntity(jsonEntity);

    try (CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(request)) {
      String entity = EntityUtils.toString(response.getEntity());
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode < 200 || responseCode >= 300) {
        throw new WrappedCheckedException(
            "Call to [" + url + "] failed. Error code=" + response + "\ncontent=" + entity);
      }
    } catch (UnknownHostException e) {
      throw e;
    } catch (IOException e) {
      throw new WrappedCheckedException(e);
    }
  }

  // TODO review for 10G response
  private String connectToServerGet() {
    String url = serverUrl + "/openidm/managed/user?_queryId=query-all";

    HttpGet request = new HttpGet(url);
    request.addHeader("Accept", "application/json");
    request.addHeader("X-Requested-With", "Swagger-UI");
    request.addHeader("X-OpenIDM-Username", serverUsername);
    request.addHeader("X-OpenIDM-Password", serverPassword);
    try (CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(request)) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line;
      StringBuffer jsonResponse = new StringBuffer();
      while ((line = bufferedReader.readLine()) != null) {
        jsonResponse.append(line);
      }
      return jsonResponse.toString();
    } catch (IOException | UnsupportedOperationException e) {
      throw new WrappedCheckedException(e);
    }
  }

  // TODO what backup ??? :D
  @VisibleForTesting
  void backupUsers(List<User> theUserList) {
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
    Date date = new Date();

    String target = "target/test-files/";
    String csvFile = target + "users_backup_" + dateFormat.format(date) + ".csv";

    Locations.current(csvFile).mkdirOnParentIfNecessary();

    User[] theUser = theUserList.toArray(new User[theUserList.size()]);

    new BeanBasedUserDao().writeUsers(csvFile, theUser);
  }

  @Override
  public String toString() {
    return "ForgerockUserDao[serverUrl=" + serverUrl + ", serverUsername=" + serverUsername + "]";
  }
}
