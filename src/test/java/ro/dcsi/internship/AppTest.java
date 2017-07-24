package ro.dcsi.internship;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AppTest {
  static String resourcesFolder = "src/test/resources/";

  @Test
  public void testApp() {
	  UserController controller = new UserController();
	  List<User> existingUsers = controller.readUsers(resourcesFolder + "users.csv");
	  User[] users = new User[existingUsers.size()];
	  
	  controller.writeUsers(resourcesFolder + "tempUsers", existingUsers.toArray(users));
	  
	  List<User> tempUsers = controller.readUsers(resourcesFolder + "tempUsers");
	  
	  Assert.assertEquals(existingUsers.size(), tempUsers.size());
	  
	  for (int i = 0; i< existingUsers.size(); i++) {
		  Assert.assertEquals(existingUsers.get(i).toString(), tempUsers.get(i).toString());
	  }
  }

  @Test
  public void testReadAppSorin() {
    UserDao appS = new UserDaoSorin();
    System.out.println(appS.readUsers(resourcesFolder + "sorinUsersStyle.csv"));
  }

  @Test
  public void testAppSorin() {
    UserDao appS = new UserDaoSorin();
    appS.writeUsers(resourcesFolder + "newSorinUsersCsv.csv",
        new TheUser("ion12", "abc", "IonIon", 755, 22, "RO", "ion.ion@ionmail.com"),
        new TheUser("gigi123200", "qwerty", "GigelMasan", 753, 21, "RO", "gigi.ggg@gmail.com"));
    appS.writeUsers(resourcesFolder + "new2SorinUsersCsv2.csv",
        new TheUser("sorin", "mnqw12", "SorinDragan", 777, 20, "RO", "sorin.dragan27@gmail.com"));
    Assert.assertEquals(2, appS.readUsers(resourcesFolder + "newSorinUsersCsv.csv").size());
    Assert.assertEquals(1, appS.readUsers(resourcesFolder + "new2SorinUsersCsv2.csv").size());
  }

  @Test
  public void testApp3() {
    App3.writeDataInFile("");
    App3.readDataFromFile();
  }
  @Test
  @Ignore
  public void testCostin(){
    UserDao app= new UserDaoCostin();
    app.writeUsers("file1",new TheUser(),new TheUser());
    app.writeUsers("file2",new TheUser());
    Assert.assertEquals(2,app.readUsers("file1").size());
    Assert.assertEquals(1,app.readUsers("file2").size());
  }
}
