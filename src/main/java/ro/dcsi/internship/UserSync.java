package ro.dcsi.internship;

import java.util.Iterator;

public interface UserSync {
    Iterator<User> readUsers(String filename);
}
