package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Map;
import org.junit.Test;

public class HTTPRequestTest {
	String existingUserId = "HTTPRequestTestExistingUser", nonExistingUserId = "HTTPRequestTestNonExistingUser";
	
	public void prepareDatabase() {
		
	}
	
	@Test
	public void simpleTest() {
		/* TODO general tests */
		this.prepareDatabase();
		Map<String, String> headers = new Hashtable<String, String>();
		headers.put("X-OpenIDM-Username", "openidm-admin");
		headers.put("X-OpenIDM-Password", "openidm-admin");
		headers.put("Content-Type", "application/json");
		String url = "http://localhost:8080/openidm/managed/user/" + existingUserId + "?_prettyPrint=true";
		HTTPRequest request = new HTTPRequest(url, "GET", headers);
		HTTPResponse response = request.send();
		System.out.println("Requested " + request);
		System.out.println("Recieved " + response);
		assertEquals(response.code, 200);
	}

	@Test
	public void simple404Test() {
		/* TODO general tests */
		this.prepareDatabase();
		Map<String, String> headers = new Hashtable<String, String>();
		headers.put("X-OpenIDM-Username", "openidm-admin");
		headers.put("X-OpenIDM-Password", "openidm-admin");
		headers.put("Content-Type", "application/json");
		String url = "http://localhost:8080/openidm/managed/user/" + nonExistingUserId + "?_prettyPrint=true";
		HTTPRequest request = new HTTPRequest(url, "GET", headers);
		HTTPResponse response = request.send();
		System.out.println("Requested " + request);
		System.out.println("Recieved " + response);
		assertEquals(response.code, 404);
	}

	@Test
	public void dataTest() {
		/* TODO general tests */
		this.prepareDatabase();
		Map<String, String> headers = new Hashtable<String, String>();
		headers.put("X-OpenIDM-Username", "openidm-admin");
		headers.put("X-OpenIDM-Password", "openidm-admin");
		headers.put("Content-Type", "application/json");
		String url = "http://localhost:8080/openidm/managed/user/" + nonExistingUserId + "?_prettyPrint=true";
		HTTPRequest request = new HTTPRequest(url, "PUT", headers, "{\"givenName\":\"joe\",\"sn\":\"joeSN\",\"userName\":\"joe\",\"_id\":\"joe\",\"mail\":\"joe@ex.com\"}");
		HTTPResponse response = request.send();
		System.out.println("Requested " + request);
		System.out.println("Recieved " + response);
		
		assertEquals(response.code, 201);
	}
}
