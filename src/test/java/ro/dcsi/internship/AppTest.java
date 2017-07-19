package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AppTest {
  @Test
  public void testApp(){
    App.main(null);
  }
  @Test
  public void testApp2() throws FileNotFoundException {
    String testR = new ReadingUsers().read();
    String testW = new WritingUsers().verification();
    //knowing the last user i want to know if the method      //read them all
    //TODO review
    Assert.assertEquals("ca\n", testR);
    Assert.assertEquals("Writing done!", testW);
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
