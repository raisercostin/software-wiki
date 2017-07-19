package ro.dcsi.internship;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
  @Test
  public void testApp() throws FileNotFoundException {
    String testR = new ReadingUsers().read();
    String testW = new WritingUsers().verification();
    //knowing the last user i want to know if the method      //read them all
    //TODO review
    Assert.assertEquals("ca\n", testR);
    Assert.assertEquals("Writing done!", testW);
  }
}
