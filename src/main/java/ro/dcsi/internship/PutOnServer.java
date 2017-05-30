
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

public class PutOnServer implements UserDao {
	
	URL url = new URL (http://localhost:8080);
	URLConnection connection = url.openConnection();
	connection.setDoOutput(true);
    OutputStreamWriter writer=new OutputStreamWriter(conn.getOutputStream());
    
}
