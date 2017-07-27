package ro.dcsi.internship;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.raisercostin.jedi.Locations;
import scalaj.http.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cristi on 27-Jul-17.
 */
public class ForgerockUserDao {

    public void writeUsersToServer() {

    }

    public List<TheUser> readUsersFromServer() {
        List<TheUser> theUserList = new ArrayList<>();


        return theUserList;
    }

    public void connectToServer(){
        String url = "http://localhost:8443/openidm/managed/user?_queryId=query-all";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response;

        try {
            response = client.execute(request);
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (UnsupportedOperationException e){
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
