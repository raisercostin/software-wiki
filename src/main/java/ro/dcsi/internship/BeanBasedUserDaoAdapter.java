package ro.dcsi.internship;

import java.util.List;

import com.google.api.client.util.Preconditions;

public class BeanBasedUserDaoAdapter implements UserDao {
  private BeanBasedUserDao dao;
  private String file;

  public BeanBasedUserDaoAdapter(BeanBasedUserDao dao, String file){
    this.dao = dao;
    this.file = file;
    Preconditions.checkNotNull(dao);
    Preconditions.checkNotNull(file);
  }

  @Override
  public void writeUsers(TheUser... users) {
    dao.writeUsers(file,users);
  }

  @Override
  public List<TheUser> readUsers() {
    return dao.readUsers(file);
  }
}
