package ro.dcsi.internship;

import java.util.Hashtable;

public class Permissions {
	private Hashtable<String, Boolean> permissions = new Hashtable<String, Boolean>();

	public Permissions() {
		
	}
	
	public Permissions(Permissions permissions) {
		this.permissions = (Hashtable<String, Boolean>) permissions.permissions.clone();
	}

	public Permissions(String permissinos) {

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
}
