
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import org.json.simple.JSONObject;

public class PutOnServer implements UserDao {
	
	static URL url = new URL (//localhost:8080);
	URLConnection connection = url.openConnection();
	static HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

	connection.setDoOutput(true);
	httpCon.setRequestMethod("PUT");
	
    OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
    static JSONObject json=null;

    public static void putDetails(int id, String mail, String userName, String sn, String givenName){
    	httpCon.setRequestProperty("Content-Type", "application/json");
    	httpCon.setRequestProperty("Accept", "application/json");
    	httpCon.setRequestProperty("If-None-Match", "*'");
     	httpCon.setRequestProperty("X-OpenIDM-Username", "openidm-admin");
     	httpCon.setRequestProperty("X-OpenIDM-Username", "openidm-admin");
     	httpCon.setRequestProperty("X-Requested-With", "Swagger-UI");
    	json=new JSONObject();
    	json.put("id", 2023 );
    	json.put("mail", "lukas4@gmail.com" );
    	json.put("userName", "lukas");
    	json.put("sn", "sn210");
    	json.put("givenName", "lukasss"  );
    }


}
