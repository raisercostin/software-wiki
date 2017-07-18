package ro.dcsi.internship;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileNotFoundException;

import junit.framework.Assert;


/**
 * Unit test for App.
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
     * My tests
     * @throws FileNotFoundException 
     */
    public void testApp() throws FileNotFoundException
    {
    	String testR = new ReadingUsers().read();
		String testW = new WritingUsers().verification();
		Assert.assertEquals("xxyztt", testR);
		Assert.assertEquals("Writing done!", testW);
    }
}