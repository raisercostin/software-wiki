package ro.dcsi.internship;

import java.util.Set;
import java.util.UUID;
import com.google.common.base.Preconditions;
import com.opencsv.bean.CsvBindByName;

public class User {
  public static String generateUserId() {
    return UUID.randomUUID().toString();
  }

  @CsvBindByName
  public final String id;
  @CsvBindByName
  public final String username;
  @CsvBindByName
  public final String email;
  @CsvBindByName
  public final String firstname;
  @CsvBindByName
  public final String lastname;

  /**
   * @CsvBindByName public final String passwd;
   * @CsvBindByName(required = false) public final String fullname; public final int permissions;
   *                         public final int age; private Set<String> permisions;
   **/

  // TODO Don't remove it. It's used by opencsv via reflection.
  public User() {
    this("<cannot happen: no name-private-constructor>");
  }

  public User(String name) {
    this(name, "", "", "<no-email>");
  }
  //
  // public User(String username, String firstname, String lastname, String email) {
  // this(generateUserId(), username, "", firstname, lastname, "", 0, 0, email);
  // }
  // //
  // public User(String id, String username, String passwd, String firstname, String lastname,
  // String fullname,
  // int permissions, int age, String email) {
  // this.username = username;
  // this.passwd = passwd;
  // this.fullname = fullname;
  // this.firstname = firstname;
  // this.lastname = lastname;
  // this.permissions = permissions;
  // this.age = age;
  // this.id = id;
  // this.email = email;
  // Preconditions.checkNotNull(id);
  // }

  public User(String username, String email) {
    this(username, email, "NoFirstName", "NoLastName");
  }

  public User(String username, String email, String firstname, String lastname) {
    this.id = generateUserId();
    this.username = username;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    if (username.contains("\n"))
      throw new RuntimeException(
          "A username shouldn't contain End Of Lines. The username was [" + username + "]");
  }
  // @Override
  // public String toString() {
  // return "User("+username+"<"+email+">)";
  // }

  public String toString() {
    return "TheUser{" + "username='" + username + ", id='" + id + '\'' + ", email='" + email + '\''
        + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + '}';
  }

  /** A cleaned up username that can be used by forgerock. */
  // TODO remove a violation of SRP
  public String idFromUsenameForForgeRock() {
    String reg = "[\\\\ \",]";
    return username.replaceAll(reg, "-");
  }

  public String getUsername() {
    return username;
  }
  /*
   * public String getPasswd() { return passwd; }
   * 
   * public String getFullname() { return fullname; }
   * 
   * public int getPermissions() { return permissions; }
   * 
   * public int getAge() { return age; }
   */

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }
}
