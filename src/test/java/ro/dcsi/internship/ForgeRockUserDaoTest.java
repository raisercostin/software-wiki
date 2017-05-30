package ro.dcsi.internship;

public class ForgeRockUserDaoTest extends CsvFileUserDaoTest {
	@Override
	UserDao exporter() {
		return new ForgeRockUsersDao();
	}
}
