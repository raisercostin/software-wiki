package ro.dcsi.internship;

public class TheUser {
  
  private String username;
  private String passwd;
  private String fullname;
  private int permissions;
  private int age;
  private String country;
  private String email;

  public TheUser(String name) {
    setUsername(name);
  }
  
  public TheUser() {
    
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public int getPermissions() {
    return permissions;
  }

  public void setPermissions(int permissions) {
    this.permissions = permissions;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String toString() {
    return "[" + getUsername() + "::" + getFullname() + "::" + getPermissions() + "::" + getPermissions() + "::" + getEmail() + "]";
  }

}
