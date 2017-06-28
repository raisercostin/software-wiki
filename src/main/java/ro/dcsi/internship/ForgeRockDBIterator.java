package ro.dcsi.internship;

import java.util.Iterator;

public class ForgeRockDBIterator implements Iterator<User> {
	private final ForgeRockDB database;

	public ForgeRockDBIterator(ForgeRockDB database) {
		this.database = database;
	}

	public boolean hasNext() {
		/* TODO implement hasNext() */
		throw new RuntimeException("Not implemented yet!");
	}

	public User next() {
		/* TODO implement next() */
		throw new RuntimeException("Not implemented yet!");
	}
}
