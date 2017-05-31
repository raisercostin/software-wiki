package ro.dcsi.internship;

import java.io.*;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Joiner;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ForgeRockUsersDao implements UserDao {
	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ForgeRockUsersDao.class);
	private final HttpClient httpClient = HttpClientBuilder.create().build();
	public final String hostAndPort;
	public final String protocol;

	public ForgeRockUsersDao(String hostAndPort) {
		this("http",hostAndPort);
	}

	public ForgeRockUsersDao(String protocol, String hostAndPort) {
		logger.info("ForgeRockUsersDao initialized");
		this.protocol = protocol;
		this.hostAndPort = hostAndPort;
	}

	@Override
	public String loadHeader(String csvFile) {
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public List<User> load(String csvFile) {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();) {
			ArrayList<User> users = new ArrayList<>();
			String url = protocol+"://" + hostAndPort + "/openidm/managed/user?_prettyPrint=true&_queryId=query-all";
			HttpGet request = new HttpGet(url);
			configure(request);
			try (CloseableHttpResponse response = httpClient.execute(request);) {
				String result = EntityUtils.toString(response.getEntity());
				logger.trace(result);
				// parse the json result and find the largest id
				JSONObject obj = new JSONObject(result);
				JSONArray arr = obj.getJSONArray("result");
				for (int i = 0; i < arr.length(); i++) {
					String usernameFromId = arr.getJSONObject(i).getString("_id");
					String email = arr.getJSONObject(i).getString("mail");
					String username = arr.getJSONObject(i).getString("userName");
					String firstname = arr.getJSONObject(i).getString("givenName");
					String lastname = arr.getJSONObject(i).getString("sn");
					users.add(new User(usernameFromId, email, firstname, lastname));
					if (!username.equals(usernameFromId)) {
						logger.warn(
								"We modeled a single field for both id and username and the server has two different values ["
										+ usernameFromId + "] != [" + username
										+ "]. The first value will be used for both.");
					}
				}
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void save(List<User> users, String outputFileName) {
		for (User user : users) {
			postCreateUser(user.username, user.email, user.username, user.lastname, user.firstname);
		}
	}

	public void delete(String username) {
		try {
			String url = protocol+"://" + hostAndPort + "/openidm/managed/user/" + username;
			HttpDelete request = new HttpDelete(url);
			configure(request);
			HttpResponse response = httpClient.execute(request);
			int responseCode = -1;
			responseCode = response.getStatusLine().getStatusCode();
			String entity = EntityUtils.toString(response.getEntity());
			logger.info("user put " + response + "\nentity=" + entity);
			if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 300) {
				throw new RuntimeException("Error code=" + response + "\ncontent=" + entity);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void postCreateUser(String id, String mail, String userName, String lastName, String givenName) {
		try {
			String url = protocol+"://" + hostAndPort + "/openidm/managed/user/" + id;
			HttpPut request = new HttpPut(url);
			StringEntity params = new StringEntity(
					"{" + "\"_id\": \"" + id + "\", " + "\"mail\": \"" + mail + "\", " + "\"userName\": \"" + userName
							+ "\", " + "\"sn\": \"" + lastName + "\", " + "\"givenName\": \"" + givenName + "\"}");

			configure(request);
			request.addHeader("If-None-Match", "*");

			request.setEntity(params);

			HttpResponse response = httpClient.execute(request);

			String entity = EntityUtils.toString(response.getEntity());
			logger.info("user put " + response + "\nentity=" + entity);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode < 200 || responseCode >= 300) {
				throw new RuntimeException("Error code=" + response + "\ncontent=" + entity);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void configure(HttpRequestBase request) {
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "application/json");
		request.addHeader("X-Requested-With", "Swagger-UI");
		request.addHeader("X-OpenIDM-Username", "openidm-admin");
		request.addHeader("X-OpenIDM-Password", "openidm-admin");
	}

	private int getLastUserId() {
		int max = -1;
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();) {
			String url = protocol+"://" + hostAndPort + "/openidm/managed/user?_queryId=query-all-ids";
			HttpGet request = new HttpGet(url);
			configure(request);

			try (CloseableHttpResponse response = httpClient.execute(request);) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				// parse the json result and find the largest id
				JSONObject obj = new JSONObject(result.toString());
				JSONArray arr = obj.getJSONArray("result");

				for (int i = 0; i < arr.length(); i++) {
					try {
						if (Integer.parseInt(arr.getJSONObject(i).getString("_id")) > max) {
							max = Integer.parseInt(arr.getJSONObject(i).getString("_id"));
						}
					} catch (NumberFormatException e) {
						throw new RuntimeException(e);
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return max;
	}

	public void forcedCreate(List<User> users) {
		deleteIfExists(users);
		save(users, "nu conteaza");
	}
	public void deleteIfExists(List<User> users) {
		for (User user : users) {
			deleteIfExists(user.username);
		}
	}
	public void deleteIfExists(String username) {
		try {
			// TODO deletion if exists shouldn't be implemented by swallowing
			// the exception
			delete(username);
		} catch (RuntimeException e) {
			logger.debug("Cannot delete user [" + username + "].", e);
		}
	}
}
