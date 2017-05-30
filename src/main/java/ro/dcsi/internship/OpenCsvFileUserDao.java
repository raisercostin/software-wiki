package ro.dcsi.internship;

import java.io.*;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Joiner;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import ro.dcsi.internship.CsvFileUserDao.Header;

public class OpenCsvFileUserDao implements UserDao {
	public OpenCsvFileUserDao() {
		System.out.println("OpenCsvFileUserDao initialized");
	}
	@Override
	public String loadHeader(String csvFile) {
		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '\"', 0)) {
			return toLine(reader.readNext());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private String toLine(String[] fields) {
		return Joiner.on(",").join(Arrays.asList(fields));
	}
	
	@Override
	public List<User> load(String csvFile) {
		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '\"', 0)) {
			ArrayList<User> users = new ArrayList<>();
			String[] splited = null;
			Header header = new Header(reader.readNext());
			while ((splited = reader.readNext()) != null) {
				users.add(header.toUser(splited));
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public void save(List<User> users, String outputFileName) {
		Header header = new Header();
		try (CSVWriter writer = new CSVWriter(new FileWriter(outputFileName))) {
			writer.writeNext(header.headerValues());
			for (User user : users) {
				String[] currentUser = header.fromUser(user);
				writer.writeNext(currentUser);//Arrays.toString(currentUser).replaceAll("\\[|\\]", "").split(","));

				postCreateUser(getLastUserId()+1, 
						header.getEmail(currentUser),
						header.getUserName(currentUser),
						header.getLastName(currentUser),
						header.getFirstName(currentUser));
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void postCreateUser(int id, String mail, String userName, String lastName, String givenName){
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		int responseCode = -1;	
		
		try {
			String url = "http://localhost:8080/openidm/managed/user/" + Integer.toString(id);
			HttpPut request = new HttpPut(url);	
		    StringEntity params =new StringEntity("{"
		    		+ "\"_id\": \"" + Integer.toString(id) + "\", "
		    		+ "\"mail\": \"" + mail + "\", "
		    		+ "\"userName\": \"" + userName + "\", "
		    		+ "\"sn\": \"" + lastName + "\", "
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
	        if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) {

	            BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));

	            String output;
	           // System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n");
	            while ((output = br.readLine()) != null) {
	               // System.out.println(output);
	            }
	        }
	        else{
	        	System.out.println("Error " + response.getStatusLine().getStatusCode());
	        }

		}catch (Exception ex) {

		    //handle exception here

		} 
	}
	
	private static int getLastUserId(){
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		int max = -1;
		try {
			String url = "http://localhost:8080/openidm/managed/user?_queryId=query-all-ids";
			HttpGet request = new HttpGet(url);	
		    
		    request.addHeader("content-type", "application/json");
		    request.addHeader("Accept", "application/json");
		    request.addHeader("X-Requested-With", "Swagger-UI");
		    request.addHeader("X-OpenIDM-Username", "openidm-admin");
		    request.addHeader("X-OpenIDM-Password", "openidm-admin");
		    
		    HttpResponse response = httpClient.execute(request);

			BufferedReader rd = new BufferedReader(
	                       new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			//parse the json result and find the largest id
			JSONObject obj = new JSONObject(result.toString());
			JSONArray arr = obj.getJSONArray("result");
			
			for (int i = 0; i < arr.length(); i++)
			{
				try {
					if (Integer.parseInt(arr.getJSONObject(i).getString("_id")) > max){
						max = Integer.parseInt(arr.getJSONObject(i).getString("_id"));
					}
				} catch (NumberFormatException e) {
				    continue;
				}
			}
		}catch (Exception ex) {
		    //handle exception here
		} 
		return max;
	}
	
}
