package ro.dcsi.internship;

import java.util.Hashtable;

public class User {
	public final String id;
	private final Hashtable<String, String> attributes = new Hashtable<String, String>();
	
	public User(String id, Hashtable<String, String> attributes) {
		this.id = id;
		this.attributes.putAll(attributes);
	}
	
	public String getAttributeValue(String attribute) {
		return attributes.get(attribute);
	}
}
