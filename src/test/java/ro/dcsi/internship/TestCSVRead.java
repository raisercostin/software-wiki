package ro.dcsi.internship;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;


/**
 * Created by Catalin on 20/06/2017.
 */
public class TestCSVRead {

    @Test
    public void NoHeaderSmallTest(){
        Usersync sync = new Usersync();
        UserManager database=sync.readUsers("src/test/CSV/NoHeaders.csv");
        Iterator<User> users= database.iterator();
        User buffer;

        buffer=users.next();
        assertEquals(buffer.getName(),"Catalin");
        assertEquals(buffer.getEmail(),"catalin@gmail.com");

        buffer=users.next();
        assertEquals(buffer.getName(),"Ghenea");
        assertEquals(buffer.getEmail(),"ghenea@yahoo.com");

        assertEquals(users.hasNext(),false);
    }

    @Test
    public void HeadersNotDefault(){
        assert(true);
    }

    @Test
    public void NoHeaderLarge(){
        fail();
    }

    @Test
    public void HeadersLarge(){
        assert(true);
    }



}
