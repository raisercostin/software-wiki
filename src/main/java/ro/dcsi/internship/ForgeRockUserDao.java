package ro.dcsi.internship;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ForgeRockUserDao implements UserDao {
	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ForgeRockUserDao.class);
	private final HttpClient httpClient = HttpClientBuilder.create().build();
	public final String hostAndPort;
	public final String protocol;

	public ForgeRockUserDao(String hostAndPort) {
		this("http",hostAndPort);
	}

	public ForgeRockUserDao(String protocol, String hostAndPort) {
		logger.info("ForgeRockUserDao initialized");
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
					User user = new User(username, email, firstname, lastname);
					users.add(user);
					if (!user.idFromUsenameForForgeRock().equals(usernameFromId)) {
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
			createUser(user);
		}
	}

	public void deleteById(String id) {
		try {
			String url = protocol+"://" + hostAndPort + "/openidm/managed/user/" + id;
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
	private void createUser(User user) {
		String id = user.idFromUsenameForForgeRock();
		try {
			String url = protocol+"://" + hostAndPort + "/openidm/managed/user/" + id;
			HttpPut request = new HttpPut(url);

			JSONObject json = new JSONObject();
	    	json.put("id", id );
	    	json.put("mail", user.email );
	    	json.put("userName", user.username);
	    	json.put("sn", user.lastname);
	    	json.put("givenName", user.firstname );	    	
	    	String fullJson = json.toString();
//					"{" + 
//					"\"_id\": \"" + id + "\", " + 
//					"\"mail\": \"" + user.email + "\", " + 
//					"\"userName\": \"" + user.username
//					+ "\", " + "\"sn\": \"" + user.lastname + "\", " + "\"givenName\": \"" + user.firstname + "\"}";
			StringEntity params = new StringEntity(fullJson);
		
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
			deleteIfExistsById(user.username);
		}
	}
	public void deleteIfExistsById(String id) {
		try {
			// TODO deletion if exists shouldn't be implemented by swallowing
			// the exception
			deleteById(id);
		} catch (RuntimeException e) {
			logger.debug("Cannot delete user [" + id + "].", e);
		}
	}

	public void delete(User user) {
		deleteById(user.idFromUsenameForForgeRock());
	}

	//TODO improve performance
	public Optional<User> loadUserById(String idFromUsenameForForgeRock) {
		List<User> users2 = load("doesn't matter the name2");
		List<User> res = users2.stream().filter(u->u.idFromUsenameForForgeRock().equals(idFromUsenameForForgeRock)).collect(Collectors.toList());
		if(res.size()>1)
			throw new IllegalStateException("Too many users!");
		if(res.size()==0)
			return Optional.empty();
		return Optional.of(res.get(0));
	}
}
