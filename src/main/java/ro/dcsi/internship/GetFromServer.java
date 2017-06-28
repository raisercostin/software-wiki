package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetFromServer {
	public static void main(String[] args) {
		readUsers("localhost:8080");
	}

	public static List<User> readUsers(String hostAndPort) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		List<User> lu = new ArrayList<>();
		try {
			String url = "http://" + hostAndPort + "/openidm/managed/user?_prettyPrint=true&_queryId=query-all";
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
			JSONObject obj = new JSONObject(result.toString());
			JSONArray arr = obj.getJSONArray("result");
			for (int i = 0; i < arr.length(); i++) {
				String username = arr.getJSONObject(i).getString("userName");
				String mail = arr.getJSONObject(i).getString("mail");
				String firstname = arr.getJSONObject(i).getString("givenName");
				String lastname = arr.getJSONObject(i).getString("sn");
				User user = new User(username, mail, firstname, lastname);
				lu.add(user);
			}
		
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		for(User us : lu)
			System.out.println(us);
		return lu;
	}
}
