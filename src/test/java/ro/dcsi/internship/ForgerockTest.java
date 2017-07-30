package ro.dcsi.internship;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.ArrayList;
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
    public void testReadUsers() {
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        List<TheUser> theUserList = forgerockUserDao.readUsersFromServer();

        for (TheUser user : theUserList)
            System.out.println(user.toString());
    }

    @Test
    public void testWriteUsers() {
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.writeUsersToServer(0,generateUsers(3).toArray(new TheUser[0]));
        forgerockUserDao.writeUsersToServer(3,generateUsers(3).toArray(new TheUser[0]));
        forgerockUserDao.writeUsersToServer(6,generateUsers(3).toArray(new TheUser[0]));
    }

    //TODO main test si restul
    @Test
    public void mainTest() {
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.writeUsersToServer(0,generateUsers(100).toArray(new TheUser[0]));
        List<TheUser> theUserList = forgerockUserDao.readUsersFromServer();
        forgerockUserDao.backupUsers(theUserList);
    }

    public List<TheUser> generateUsers(int n) {
        List<TheUser> theUserList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < n; i++) {
            TheUser user = new UserBuilder().setUsername(faker.name().username())
                    .setFirstName(faker.name().firstName())
                    .setLastName(faker.name().lastName())
                    .setEmail(faker.name().username() + "@gmail.com").builder();
            theUserList.add(user);
        }
        return theUserList;
    }
}
