package ro.dcsi.internship;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Created by Catalin on 20/06/2017.
 */
public class TestCSVRead {

    @Test
    public void NoHeaderSmallTest() {
        UserSync sync = new FileUserManger();
        test(sync);
    }

    @Test
    public void ReadUsersFromRemoteServer() {
        UserSync sync = new RemoteServerUserManger();
        test(sync);
    }

    private void test(UserSync sync) {
        String filename = "src/test/CSV/NoHeaders.csv";
        Iterator<User> users = sync.readUsers(filename);
        User buffer;

        buffer = users.next();
        assertEquals(buffer.getName(), "Catalin");
        assertEquals(buffer.getEmail(), "catalin@gmail.com");

        buffer = users.next();
        assertEquals(buffer.getName(), "Ghenea");
        assertEquals(buffer.getEmail(), "ghenea@yahoo.com");

        assertEquals(users.hasNext(), false);
    }

    @Test
    public void HeadersNotDefault() {
        FileUserManger sync = new FileUserManger();
        UserManager2 database = sync.readUsersAsManager("src/test/CSV/headersnotdefault.csv");
        Iterator<User> users = database.iterator();
        User buffer = null;

        while (users.hasNext())
            buffer = users.next();

        assertNotEquals(buffer, null);
        assertEquals(buffer.getName(), "CatalinLast");
        assertEquals(buffer.getEmail(), "catalinlast@yahoo.com");

    }

    @Test
    public void NoHeaderLarge() {
        FileUserManger sync = new FileUserManger();
        UserManager2 database = sync.readUsersAsManager("src/test/CSV/NoHeadersLarge.csv");
        Iterator<User> users = database.iterator();
        User buffer = null;

        while (users.hasNext())
            buffer = users.next();

        assertNotEquals(buffer, null);
        assertEquals(buffer.getName(), "CatalinLast");
        assertEquals(buffer.getEmail(), "emaillast@ceva.com");
    }

    @Test
    public void HeadersLarge() {
        FileUserManger sync = new FileUserManger();
        UserManager2 database = sync.readUsersAsManager("src/test/CSV/headerslarge.csv");
        Iterator<User> users = database.iterator();
        User buffer = null;
        int count = 0;

        while (users.hasNext()) {
            buffer = users.next();
            count++;
        }

        assertEquals(count, 1070);
        assertNotEquals(buffer, null);
        assertEquals(buffer.getName(), "CatalinLast");
        assertEquals(buffer.getEmail(), "catalinlast@yahoo.com");
    }

}
