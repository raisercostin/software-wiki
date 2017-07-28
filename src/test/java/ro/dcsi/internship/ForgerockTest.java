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
    public void testConnectionToServer() {
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.connectToServer();
    }

    @Test
    public void testReadUsers() {
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.readUsersFromServer();
    }

    @Test
    public void testWriteUsers(){
        ForgerockUserDao forgerockUserDao = new ForgerockUserDao();
        forgerockUserDao.writeUsersToServer(generateUsers(1).toArray(new TheUser[0]));
    }

    public List<TheUser> generateUsers(int n) {
        List<TheUser> theUserList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < n; i++) {
            Integer permission = (Math.random() < 0.5) ? 0 : 1;
            Integer age = faker.number().numberBetween(0, 100);
            TheUser user = new UserBuilder().setUsername(faker.name().username()).setPasswd(faker.idNumber().valid())
                    .setFullname(faker.name().fullName()).setPermissions(permission).setAge(age)
                    .setCountry(faker.address().country()).setEmail(faker.name().username() + "@gmail.com").build();
            theUserList.add(user);
        }
        return theUserList;
    }
}
