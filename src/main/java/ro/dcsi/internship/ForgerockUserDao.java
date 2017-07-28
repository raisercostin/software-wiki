package ro.dcsi.internship;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.raisercostin.jedi.Locations;
import scalaj.http.Http;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by Cristi on 27-Jul-17.
 */
public class ForgerockUserDao {

    public void writeUsersToServer(TheUser... users) {
        List<TheUser> theUserList = Arrays.asList(users);
        String target = "target/";
        String filePath = target + "jsondata.json";
        Gson gson = new Gson();
        String json= "";
        for (int i = 0; i < theUserList.size(); i++) {
             json = gson.toJson(theUserList.get(i));
        }
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(json);
    }

    public List<TheUser> readUsersFromServer() {
        List<TheUser> theUserList = new ArrayList<>();

        String target = "target/";
        String filePath = target + "jsondata.json";

        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            TheUser theUser = gson.fromJson(bufferedReader, TheUser.class);
            System.out.println("Username: " + theUser.getUsername());
            System.out.println("First name: " + theUser.getFirstname());
            System.out.println("Last name: " + theUser.getLastname());
            System.out.println("Mail: " + theUser.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return theUserList;
    }

    //TODO de rezolvat eroarea la conectare
    public void connectToServer() {
        String url = "https://localhost:8443/openidm/managed/user?_queryId=query-all";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response;

        try {
            response = client.execute(request);
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    public void backupUsers(List<TheUser> theUserList) {
        BeanBasedUserDao beanBasedUserDao = new BeanBasedUserDao();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        String target = "target/test-files/";
        String csvFile = target + "users_backup_" + dateFormat.format(date) + ".csv";

        Locations.current(csvFile).mkdirOnParentIfNecessary();

        TheUser[] theUser = theUserList.toArray(new TheUser[theUserList.size()]);

        beanBasedUserDao.writeUsers(csvFile, theUser);
    }
}
