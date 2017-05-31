package ro.dcsi.internship;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class OpenCsvFileUserDao implements UserDao {
	public OpenCsvFileUserDao() {
		System.out.println("OpenCsvFileUserDao initialized");
	}
	@Override
	public String loadHeader(String csvFile) {
		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '\"', 0)) {
			return toLine(reader.readNext());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private String toLine(String[] fields) {
		return Joiner.on(",").join(Arrays.asList(fields));
	}
	
	@Override
	public List<User> load(String csvFile) {
		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '\"', 0)) {
			ArrayList<User> users = new ArrayList<>();
			String[] splited = null;
			Header header = new Header(reader.readNext());
			while ((splited = reader.readNext()) != null) {
				users.add(header.toUser(splited));
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException("Can't load file ["+csvFile+"].",e);
		} catch (RuntimeException e) {
			throw new RuntimeException("Can't load file ["+csvFile+"].",e);
		}
	}


	public void save(List<User> users, String outputFileName) {
		Header header = new Header();
		try (CSVWriter writer = new CSVWriter(new FileWriter(outputFileName))) {
			writer.writeNext(header.headerValues());
			for (User user : users) {
				String[] currentUser = header.fromUser(user);
				writer.writeNext(currentUser);//Arrays.toString(currentUser).replaceAll("\\[|\\]", "").split(","));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
