package ro.dcsi.internship;

import com.opencsv.bean.CsvBindByName;

public class TheUser {
  @CsvBindByName
  public String mUserName;

  public TheUser(String name) {
    this.mUserName = name;
  }

  public String getmUserName() {
    return mUserName;
  }
}
