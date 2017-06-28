package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

public class HTTPRequest {
	public final String url, method;
	public final Map<String, String> headers;
	public final String data;

	public HTTPRequest(String url, String method, Map<String, String> headers, String data) {
		this.url = url;
		this.method = method;
		this.headers = Collections.unmodifiableMap(headers);
		this.data = data;
	}

	public HTTPRequest(String url, String method, Map<String, String> headers) {
		this(url, method, headers, null);
	}

	public HTTPResponse send() {
		HttpURLConnection connection = null;
		try {
			// Open connection
			URL url = new URL(this.url);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(this.method);
			for (String header : this.headers.keySet()) {
				connection.setRequestProperty(header, this.headers.get(header));
			}
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			if (this.data != null) {
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(this.data);
				wr.close();
			}

			// Get response
			InputStream is;
			if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
				is = connection.getInputStream();
			}
			else {
				is = connection.getErrorStream();
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\n');
			}
			rd.close();
			return new HTTPResponse(response.toString(), connection.getResponseCode());
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public String toString() {
		return "HTTPRequest [\n\turl=" + url + ", \n\tmethod=" + method + ", \n\theaders=" + headers
				+ ", \n\tdata=" + data + "\n]";
	}
}
