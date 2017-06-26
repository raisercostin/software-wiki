package ro.dcsi.internship;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class UserSync {

	public static List<User> readUsers(String fileName) {
		CSVReader csvReader = null;
		List<User> empList = new ArrayList<User>();
		try {
			/**
			 * Reading the CSV File Delimiter is comma Start reading from line 1
			 */
			csvReader = new CSVReader(new FileReader("users1.csv"), ',', '"', 1);
			String[] employeeDetails = null;

			while ((employeeDetails = csvReader.readNext()) != null) {
				User emp = new User(employeeDetails[0], employeeDetails[1], employeeDetails[2], employeeDetails[3],
						employeeDetails[4]);

				empList.add(emp);
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return empList;
	}

	public static Iterable<User> readUsersFromHugeFile(String fileName) {
		CSVReader csvReader = null;
		List<User> empList = new ArrayList<User>();
		try {
			/**
			 * Reading the CSV File Delimiter is comma Start reading from line 1
			 */
			csvReader = new CSVReader(new FileReader("users1.csv"), ',', '"', 1);
			String[] employeeDetails = null;

			while ((employeeDetails = csvReader.readNext()) != null) {
				User emp = new User(employeeDetails[0], employeeDetails[1], employeeDetails[2], employeeDetails[3],
						employeeDetails[4]);
				empList.add(emp);
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return empList;
	}

}
