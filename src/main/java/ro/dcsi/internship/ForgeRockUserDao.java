package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Joiner;
import com.google.common.net.HostAndPort;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import ro.dcsi.internship.CsvFileUserDao.Header;

public class ForgeRockUserDao implements UserDao {
	private final String hostAndPort;

	public ForgeRockUserDao(String hostAndPort) {
		this.hostAndPort = hostAndPort;
		System.out.println("OpenCsvFileUserDao initialized");
	}

	@Override
	public String loadHeader(String csvFile) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<User> load(String csvFile) {
		ArrayList<User> users = new ArrayList<>();
		// users.add(header.toUser(splited));
		return users;
	}

	@Override
	public void save(List<User> users, String outputFileName) {
		for (User user : users) {
			postCreateUser(getLastUserId() + 1, user.email, user.username, user.lastname, user.firstname);
		}
	}

	private void postCreateUser(int id, String mail, String userName, String lastName, String givenName) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
																	// instead
		int responseCode = -1;
		try {
			String url = "http://"+hostAndPort+"/openidm/managed/user/" + Integer.toString(id);
			HttpPut request = new HttpPut(url);
			StringEntity params = new StringEntity("{" + "\"_id\": \"" + Integer.toString(id) + "\", " + "\"mail\": \""
					+ mail + "\", " + "\"userName\": \"" + userName + "\", " + "\"sn\": \"" + lastName + "\", "
					+ "\"givenName\": \"" + givenName + "\"}");

			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("If-None-Match", "*");
			request.addHeader("X-Requested-With", "Swagger-UI");
			request.addHeader("X-OpenIDM-Username", "openidm-admin");
			request.addHeader("X-OpenIDM-Password", "openidm-admin");

			request.setEntity(params);

			HttpResponse response = httpClient.execute(request);

			responseCode = response.getStatusLine().getStatusCode();
			if (responseCode < 200 || responseCode >= 300) {
				throw new RuntimeException("Error " + response +"\nentity="+EntityUtils.toString(response.getEntity()));
			}else{
//				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//
//				String output;
//				// System.out.println("Output from Server ...." +
//				// response.getStatusLine().getStatusCode() + "\n");
//				while ((output = br.readLine()) != null) {
//					// System.out.println(output);
//				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	private int getLastUserId() {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
																	// instead
		int max = -1;
		try {
			String url = "http://"+hostAndPort+"/openidm/managed/user?_queryId=query-all-ids";
			HttpGet request = new HttpGet(url);

			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("X-Requested-With", "Swagger-UI");
			request.addHeader("X-OpenIDM-Username", "openidm-admin");
			request.addHeader("X-OpenIDM-Password", "openidm-admin");

			HttpResponse response = httpClient.execute(request);

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
					continue;
				}
			}
		} catch (ClientProtocolException ex) {
			throw new RuntimeException(ex);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		return max;
	}
}
