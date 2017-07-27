package ro.dcsi.internship;

import org.junit.Test;

import java.util.List;

/**
 * Created by Cristi on 27-Jul-17.
 */
public class ForgerockTest {

    static String target = "target/test-files/";

    @Test
    public void testBackup() {
        BeanBasedUserDao controller = new BeanBasedUserDao();
        List<TheUser> existingUsers = controller.readUsers(target + "tempUsers.csv");

        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.backupUsers(existingUsers);
    }

    @Test
    public void testConnectionToServer(){
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.connectToServer();
    }
}
