package ro.dcsi.internship;

import java.util.Iterator;

public class ForgeRockDBIterator implements Iterator<User> {
	private final ForgeRockDB database;
	public ForgeRockDBIterator(ForgeRockDB database) {
		this.database = database;
	}
	
	@Override
	public boolean hasNext() {
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public User next() {
		throw new RuntimeException("Not implemented yet!");
	}
}
