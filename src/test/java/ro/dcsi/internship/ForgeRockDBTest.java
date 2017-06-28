package ro.dcsi.internship;

import static org.junit.Assert.*;
import org.junit.Test;

public class ForgeRockDBTest {
	@Test
	public void test() {
		/* TODO general tests*/
		ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
		User user = db.getUser("75dbcfad-fb4d-4963-81fc-6a8c7175ffce");
		assertEquals(user.getAttributeValue("city"), "buch");
	}
}
