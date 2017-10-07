package ro.dcsi.internship;

import java.util.List;

public class FileUserDaoAdapter implements UserDao{
  private String fileName;
  private FileUserDao dao;
  public FileUserDaoAdapter(String fileName, FileUserDao dao){
    this.fileName = fileName;
    this.dao = dao;
  }
  @Override
  public void save(List<User> users) {
    dao.save(users,fileName);
  }
  @Override
  public List<User> load() {
    return dao.load(fileName);
  }
}