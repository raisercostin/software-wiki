
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import org.json.simple.JSONObject;

public class PutOnServer implements UserDao {
	
	URL url = new URL (http://localhost:8080);
	URLConnection connection = url.openConnection();
	HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

	connection.setDoOutput(true);
    OutputStreamWriter writer=new OutputStreamWriter(conn.getOutputStream());
    JSONObject json=null;

    public static putDetails(int id, String mail, String userName, String sn, String givenName){
    	json=new JSONObject();
    	json.put("id", 2023 );
    	json.put("mail", lukas4@gmail.com );
    	json.put("userName", lukas,  );
    	json.put("sn", sn210,  );
    	json.put("givenName", lukasss,  );
    }


}
