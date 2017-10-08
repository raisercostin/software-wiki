package ro.dsci.internship;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestForgeRockUserDao implements UserDao {

	@Override
	public List<User> readUsers(String locatie) {
		try {
			HttpResponse<JsonNode> getResponse = Unirest
					.get("http://localhost:8080/openidm/managed/user?_prettyPrint=true&_queryId=query-all")
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("X-Requested-With", "Swagger-UI")
					.header("X-OpenIDM-Username", "openidm-admin")
					.header("X-OpenIDM-Password", "openidm-admin")
					.asJson();

			JSONObject body = getResponse.getBody().getObject();
			System.out.println(body.toString(4));
			JSONArray users = body.getJSONArray("result");
			List<User> result = new ArrayList<>();
			for (int i = 0, maxi = users.length(); i < maxi; i++) {
				result.add(toUser((JSONObject) users.get(i)));
			}
			return result;
		} catch (UnirestException e) {
			throw new RuntimeException("Wrapped checked exception.", e);
		}
	}

	private User toUser(JSONObject object) {
		return new User(object.getInt("_id"),object.getString("userName"), object.getString("givenName"), object.getString("sn"),
				object.getString("mail"));
	}

	@Override
	public void writeUsers(List<User> users, String locatie) {
		for (User user : users) {
			
			System.out.print("Username:");
			user.username = new Scanner(System.in).next();
			System.out.print("\nFirst name:");
			user.firstname = new Scanner(System.in).next();
			System.out.print("\nLast name:");
			user.lastname = new Scanner(System.in).next();
			System.out.print("\nEmail:");
			user.email = new Scanner(System.in).next();
			
			addUsers(user);
			
		}
		
		
	}

	public User addUsers(User x) {
		try {
			// --adding parameters solution below--
		//  	try {
	    //	StringEntity parameters = new StringEntity("{" + "\"_id\": \"" + id + "\"userName\": \"" + userName
		//				+ "\"givenName\": \"" + givenName + "\"sn\": \"" + sn + "\"mail\": \"" + mail + "}");

		//	} catch (UnsupportedEncodingException e) {
		//		throw new RuntimeException(e);
		//	}
			
			HttpResponse<User> jsonResponse = Unirest.put("http://localhost:8080/openidm/managed/user")
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.header("X-Requested-With", "Swagger-UI")
					.header("X-OpenIDM-Username", "openidm-admin")
					.header("X-OpenIDM-Password", "openidm-admin")
					.body(x)
					.asObject(User.class);
			
			User createduser = jsonResponse.getBody();
		    return createduser;

			

		} catch (UnirestException e) {
			throw new RuntimeException("Wrapped checked exception.", e);
		}
	}

	private int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
