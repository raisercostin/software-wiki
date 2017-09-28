package ro.dsci.internship;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
  @Test
  public void testAppExecuteMain() {
    User user1 = new User("GeorgescuA", "Alexandru", "email1");
    Assert.assertEquals("Nume,Prenume,Job,Locatie\n" + "Popescu,Andrei,Manager,Bucuresti\n"
        + "Ionescu,Ana,Secretara,Pitesti\n" + "Georgescu , Alexandru, IT,Iasi\n" + "Georgescu , Alexandru, IT,Iasi\n"
        + "Georgescu , Alexandru, IT,Iasi\n", user1.read());
  }
}
