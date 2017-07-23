package ro.dcsi.internship;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
  @Test
  public void testApp(){
    App.main(null);
  }
  @Test
  public void testAppSorin() throws FileNotFoundException {
    UserDao appS = new UserDaoSorin();
    appS.writeUsers("file1",new TheUser("ion"),new TheUser("gigi"));
    appS.writeUsers("file2",new TheUser("costin"));
    Assert.assertEquals(2,appS.readUsers("file1").size());
    Assert.assertEquals(1,appS.readUsers("file2").size());
  }
  @Test
  public void testApp3(){
    App3.writeDataInFile("");
    App3.readDataFromFile();
  }
  @Test
  public void testApp4(){
    UserDao app= new UserDaoCostin();
    app.writeUsers("file1",new TheUser("ion"),new TheUser("gigi"));
    app.writeUsers("file2",new TheUser("costin"));
    Assert.assertEquals(2,app.readUsers("file1").size());
    Assert.assertEquals(1,app.readUsers("file2").size());
  }
}
