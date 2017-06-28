package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Map;
import org.junit.Test;

public class HTTPRequestTest {
	@Test
	public void simpleTest() {
		Map<String, String> headers = new Hashtable<String, String>();
		headers.put("X-OpenIDM-Username", "openidm-admin");
		headers.put("X-OpenIDM-Password", "openidm-admin");
		String url = "http://localhost:8080/openidm/managed/user/75dbcfad-fb4d-4963-81fc-6a8c7175ffce";
		HTTPRequest request = new HTTPRequest(url, "GET", headers);
		String response = request.send();
		System.out.println(response);
	}
}
