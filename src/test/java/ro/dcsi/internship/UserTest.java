package ro.dcsi.internship;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {
    @Test
    public void test() {
        User john = new User("john", "em@ai.l", "John", "Kramer");
        assertEquals(john.username, "john");
    }
}
