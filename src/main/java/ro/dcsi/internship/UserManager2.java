package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Catalin on 6/19/2017.
 */
public class UserManager2 implements Iterable<User> {
  private Translator reader, writer;
  private List<User> users;
  private UserIterator instance = null;
  private int maxRead;

  public UserManager2(int maxRead) {
    reader = null;
    users = null;
    this.maxRead = maxRead;
  }

  public void setReader(Translator reader) {
    this.reader = reader;
  }

  public void setWriter(Translator writer) {
    this.writer = writer;
  }

  public boolean readUsers() {
    if (reader == null) {
      return false;
    }

    // Variables
    List<List<String>> buffer;
    List<String> headers;
    User bufferUser;
    int i;

    // Read fields
    buffer = reader.readBulk(this.maxRead);

    // Check error
    if (buffer == null) {
      return false;
    }

    // Read headers + init
    headers = reader.getHeaders();
    users = new ArrayList<>();

    // Convert to users
    for (List<String> l : buffer) {
      bufferUser = new User();
      for (i = 0; i < l.size(); i++) {

        // Match header
        if (headers.get(i).toLowerCase().equals("name")) {
          bufferUser.setName(l.get(i));
        } else if (headers.get(i).toLowerCase().equals("email")) {
          bufferUser.setEmail(l.get(i));
        } else {
          bufferUser.addExtraField(l.get(i), headers.get(i));
        }
      }
      users.add(bufferUser);
    }

    return true;
  }

  public void writeUsers() {
    // Variables
    List<String> headers, buffer;
    List<List<String>> bulkWrite;

    // set headers
    headers = users.get(0).getExtraFieldHeaders();
    headers.add(0, "name");
    headers.add(1, "email");
    writer.setHeaders(headers);

    while (true) {
      bulkWrite = new ArrayList<>();
      for (User u : users) {
        buffer = u.getExtraFields();
        buffer.add(0, u.getName());
        buffer.add(1, u.getEmail());
        bulkWrite.add(buffer);
      }
      writer.writeBulk(bulkWrite);

      if (reader.hasNext()) {
        this.readUsers();
      } else {
        break;
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (User u : users) {
      builder.append(u.toString() + '\n');
    }

    if (reader.hasNext()) {
      this.readUsers();
      builder.append(this.toString());
    }

    return builder.toString();
  }

  public class UserIterator implements Iterator<User> {
    private int currentIndex;

    public UserIterator() {
      currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
      if (currentIndex < users.size()) {
        return true;
      }
      return false;
    }

    @Override
    public User next() {
      User result = users.get(currentIndex++);
      if (currentIndex == users.size() && reader.hasNext()) {
        readUsers();
        currentIndex = 0;
      }
      return result;
    }

    @Override
    public void remove() {
      return;
    }
  }

  @Override
  public Iterator<User> iterator() {
    if (instance == null) {
      instance = new UserIterator();
    }
    return instance;
  }
}
