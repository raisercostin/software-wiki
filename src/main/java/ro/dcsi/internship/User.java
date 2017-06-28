package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class User {
	/* TODO add support for complex attributes */
	public final String id;
	private final Hashtable<String, String> attributes = new Hashtable<String, String>();
	
	public User(String id, Map<String, String> attributes) {
		this.id = id;
		this.attributes.putAll(attributes);
	}
	
	public Set<String> getAttributeSet() {
		return attributes.keySet();
	}
	
	public String getAttributeValue(String attribute) {
		return attributes.get(attribute);
	}
}
