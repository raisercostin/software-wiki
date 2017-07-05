package ro.dcsi.internship;

import org.apache.commons.cli.*;

public class UserSync {
  public void copyUsers(UserDao src, UserDao dest) {
    dest.write(src.read());
  }
  /*public static void main(String args[]) {
	Option.Builder builder = Option.builder();
	Option sourceType = builder.
  }*/
}
