package ro.dsci.internship;



import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;





/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
	{
		/**
		 * Rigourous Test :-)
		 */
		public void testApp()
			{

				assertTrue(true);
			}



		public void testAppExecuteMain()
			{

				User user1 = new User("GeorgescuA", "Alexandru", "IT", "Iasi");
				Assert.assertEquals(
						"Nume,Prenume,Job,Locatie\n" + "Popescu,Andrei,Manager,Bucuresti\n"
								+ "Ionescu,Ana,Secretara,Pitesti\n" + "Georgescu , Alexandru, IT,Iasi\n"
								+ "Georgescu , Alexandru, IT,Iasi\n" + "Georgescu , Alexandru, IT,Iasi\n",
						user1.read());
			}
	}
