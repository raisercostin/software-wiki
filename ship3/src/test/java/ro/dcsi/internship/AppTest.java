package ro.dcsi.internship;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        CsvExporter export = new CsvExporter();
        List<User> users = export.readUsers("src/main/resources/users.csv");
        assertEquals("username,email,other",export.readHeading("src/main/resources/users.csv"));
        assertEquals(9,users.size());
        for(User user:users.subList(1, users.size()-1)){
            Integer indexOfAt = user.email.indexOf("@");
            System.out.println(user.email);
            System.out.println(indexOfAt);
            System.out.println(user.email.length());
            String at = user.email.substring(indexOfAt, user.email.length());
            System.out.println(at);
            assertEquals("@gmail.com",at);
        }
    }
}
