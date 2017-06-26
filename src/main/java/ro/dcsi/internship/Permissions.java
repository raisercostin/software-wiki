package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox.KeySelectionManager;

public class Permissions {
	private Hashtable<String, Boolean> permissions = new Hashtable<String, Boolean>();

	public Permissions() {
	}
	
	public Permissions(Permissions permissions) {
		this.permissions = (Hashtable<String, Boolean>) permissions.permissions.clone();
	}

	public Permissions(String permissions) {
		String[] list = permissions.split(";");
		for (String permission : list) {
			this.addPermission(permission);
		}
	}
	
	public boolean hasPermission(String permission) {
		return permissions.containsKey(permission) && permissions.get(permission) == true;
	}

	public void addPermission(String permission) {
		permissions.put(permission, true);
	}

	public void removePermission(String permission) {
		permissions.remove(permission);
	}

	public void setPermission(String permission, boolean val) {
		permissions.put(permission, val);
	}
	
	public String toString() {
		Set<String> keys = permissions.keySet();
		StringBuilder str = new StringBuilder();
		for (String key : keys) {
			str.append(key).append(";");
		}
		return str.toString();
	}
}
