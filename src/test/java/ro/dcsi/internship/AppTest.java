package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AppTest
{
  @Test
  public void testApp(){
    UserDao app= new UserDaoCostin();
    app.writeUsers("file1",new TheUser("ion"),new TheUser("gigi"));
    app.writeUsers("file2",new TheUser("costin"));
    Assert.assertEquals(2,app.readUsers("file1").size());
    Assert.assertEquals(1,app.readUsers("file2").size());
  }
}
