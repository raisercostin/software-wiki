package ro.dcsi.internship;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.raisercostin.jedi.Locations;

public class AppTest {
  static String target = "target/test-files/";
  static String resources = "src/test/resources/";

  @Test
  public void testLiviu() {
    UserController controller = new UserController();
    List<TheUser> existingUsers = controller.readUsers(resources + "users.csv");
    
    Assert.assertEquals(8, existingUsers.size());

    for(TheUser e:existingUsers) {
    	System.out.println(e);
    }
    
    TheUser[] users = new TheUser[existingUsers.size()];
    
//    Locations.current("tempUsers.csv").mkdirOnParentIfNecessary();
    
    File fisier = new File("tempUsers.csv");
   
    try { 
    	if(fisier.exists()){
    		
    	}else {
    		fisier.createNewFile();
    	}
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    controller.writeUsers(target + fisier, existingUsers.toArray(users));

    List<TheUser> tempUsers = controller.readUsers(target + "tempUsers.csv");

    Assert.assertEquals(existingUsers.size(), tempUsers.size());

    for (int i = 0; i < existingUsers.size(); i++) {
      Assert.assertEquals(existingUsers.get(i).toString(), tempUsers.get(i).toString());
    }
  }

  @Test
  public void testReadAppSorin() {
    UserDao appS = new UserDaoSorin();
    System.out.println(appS.readUsers(resources + "sorinUsersStyle.csv"));
  }

  public int getSize(String file) {
    return new UserDaoSorin().readUsers(file).size();

  }

  @Test
  public void testAppSorin() {
    UserDao appS = new UserDaoSorin();
    int size1 = 0;
    int size2 = 0;
    if (new File(target + "newSorinUsersCsv.csv").exists()) {
      size1 = getSize(target + "newSorinUsersCsv.csv");
    }
    if (new File(target + "new2SorinUsersCsv2.csv").exists()) {
      size2 = getSize(target + "new2SorinUsersCsv2.csv");
    }
    //not very clear but working fine
    appS.writeUsers(target + "newSorinUsersCsv.csv",
        new TheUser("ion12", "abc", "IonIon", 755, 22, "RO", "ion.ion@ionmail.com"),
        new TheUser("gigi123200", "qwerty", "GigelMasan", 753, 21, "RO", "gigi.ggg@gmail.com"));
    Assert.assertEquals(size1 + UserDaoSorin.howMany, appS.readUsers(target + "newSorinUsersCsv.csv").size());
    if (size1 == 0) {
      size1 = UserDaoSorin.howMany;
    }
    UserDaoSorin.howMany = 0;
    appS.writeUsers(target + "newSorinUsersCsv.csv",
        new TheUser("ion1233", "abcd", "IonIon", 755, 22, "RO", "ion.ion@ionmailll.com"),
        new TheUser("gigi123200", "qwerty", "GigelMasan", 753, 21, "RO", "gigi.ggg@gmail.com"));
    Assert.assertEquals(size1 + UserDaoSorin.howMany, appS.readUsers(target + "newSorinUsersCsv.csv").size());
    UserDaoSorin.howMany = 0;
    appS.writeUsers(target + "new2SorinUsersCsv2.csv",
        new TheUser("sorin", "mnqw12", "SorinDragan", 777, 20, "RO", "sorin.dragan27@gmail.com"));
    Assert.assertEquals(size2 + UserDaoSorin.howMany, appS.readUsers(target + "new2SorinUsersCsv2.csv").size());
  }

  @Test
  public void testAppIulian() {
    UserDao app = new UserDaoIulian();
    app.writeUsers(target + "file1", new TheUser("1"), new TheUser("1"));
    app.writeUsers(target + "file2", new TheUser("2"));
    Assert.assertEquals(2, app.readUsers(target + "file1").size());
    Assert.assertEquals(1, app.readUsers(target + "file2").size());
    Assert.assertEquals("2", app.readUsers(target + "file2").get(0).username);
  }

  @Test
  public void testGrigore() {
    UserDaoGrigore app = new UserDaoGrigore();
    app.writeUsers(target + "file1", app.generateUsers(5).toArray(new TheUser[0]));
    app.writeUsers(target + "file2", new TheUser("costin"));

    List<TheUser> ls = app.readUsers(target + "file1");
    Assert.assertEquals(2, ls.size());
    Assert.assertEquals(1, app.readUsers(target + "file2").size());
  }


  @Test
  @Ignore
  public void testReadCostin() {
    UserDao app = new UserDaoCostin();
    List<TheUser> ls = app.readUsers("users");
    Assert.assertEquals(8, ls.size());
  }

  @SuppressWarnings("null")
  @Test(expected = NullPointerException.class)
  public void workingWithNull() {
    Integer a = null;
    System.out.println(a.toString());
    int c = a.intValue();
    // if(a != null )// is a a real object?
    Optional<Integer> b = Optional.empty();
    Integer d = c = b.get().intValue();
    d.intValue();
  }
}
