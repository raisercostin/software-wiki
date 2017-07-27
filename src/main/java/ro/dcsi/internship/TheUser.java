package ro.dcsi.internship;

import com.opencsv.bean.CsvBindByName;

//case class TheUser(username:String, passwd:String)
//new TheUser(passwd="a",username="b'")

public class TheUser {
    @CsvBindByName
    public final String username;
    @CsvBindByName
    public final String passwd;
    @CsvBindByName(required = false)
    public final String fullname;
    public final int permissions;
    public final int age;
    @CsvBindByName
    public final String country;
    @CsvBindByName
    public final String email;
    @CsvBindByName
    private String firstname;
    @CsvBindByName
    private String lastname;

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getFullname() {
        return fullname;
    }

    public int getPermissions() {
        return permissions;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
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

    // needed by com.opencsv.CsvToBean
    @Deprecated
    public TheUser() {
        this("empty user");
    }

    public TheUser(String name) {
        this(name, "", "", 0, 0, "", "");
    }

/*    public TheUser(String username, String firstname, String lastname, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwd = "";
        this.fullname = "";
        this.permissions = 0;
        this.age = 0;
        this.country = "";

    }*/

    public TheUser(String username, String passwd, String fullname, int permissions, int age, String country,
                   String email) {
        this.username = username;
        this.passwd = passwd;
        this.fullname = fullname;
        this.permissions = permissions;
        this.age = age;
        this.country = country;
        this.email = email;
    }

    public String toString() {
        return "[" + username + "::" + fullname + "::" + permissions + "::" + email + "]";
    }

}
//
//@SuppressWarnings("serial")
//class TheUser2 implements Serializable {
//  // private String id;
//  @CsvBindByName
//  public final String name;
//  // private String email;
//
//  public TheUser2() {
//    this("NO_NAME");
//  }
//  public TheUser2(String name) {
//    this.name = name;
//  }
//}