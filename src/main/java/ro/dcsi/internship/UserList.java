package ro.dcsi.internship;

import java.util.Iterator;
import java.util.List;

public class UserList implements Iterable<User> {

	CSVUtils csvutils;
	List<String> line;

	public UserList(String csvFile) {
		csvutils = new CSVUtils(csvFile);
	}

	public Iterator<User> iterator() {
		// TODO Auto-generated method stub
		return new UserListIterator();
	}

	private class UserListIterator implements Iterator<User> {

		@Override
		public boolean hasNext() {
			return csvutils.scannerHasNext();
		}

		@Override
		public User next() {
			line = csvutils.parseHugeCSVFile();
			return new User(line.get(0));
		}
	}

}
