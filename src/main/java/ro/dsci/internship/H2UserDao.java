package ro.dsci.internship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.raisercostin.jedi.Locations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class H2UserDao implements UserDao {
  public static void main(String[] args) throws SQLException {
    org.h2.tools.Server.main(null);
  }

  JdbcTemplate template = new JdbcTemplate(newH2Datasource());

  public H2UserDao() {
    createUsersTabeIfExists();
  }

  private void createUsersTabeIfExists() {
    template.execute(Locations.classpath("users.sql").readContent());
  }

  @Override
  public List<User> readUsers(String locatie) {
    return template.query("select * from Users", new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString(1);
        String username = id;
        String firstname = id;
        String lastname = id;
        String email = id;
        return new User(id, username, firstname, lastname, email);
      }
    });
  }

  private DataSource newH2Datasource() {
    JdbcDataSource ds = new JdbcDataSource();
    String url = "jdbc:h2:" + Locations.current("").absolute() + "/target/test-h2-database";
    System.out.println("h2 connection url with user and password [sa]: [" + url + "].");
    ds.setURL(url);
    ds.setUser("sa");
    ds.setPassword("sa");
    return ds;
  }

  @Override
  public void writeUsers(List<User> users, String locatie) {
    for (User user : users) {
      template.update("insert into Users(username) values (?)", user.username);
    }
  }
}
