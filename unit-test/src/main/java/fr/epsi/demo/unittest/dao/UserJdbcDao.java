package fr.epsi.demo.unittest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import fr.epsi.demo.unittest.bean.User;

public class UserJdbcDao extends JdbcDaoSupport {

  public Collection<User> findAll() {
    return this.getJdbcTemplate()
      .query("select identifier, first_name, last_name, phone_number from contact", (rs, index) -> {
        User user = new User();
        user.setIdentifier(rs.getLong("identifier"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
      });
  }

  public void addUser(User user) {
    final String query = user.getIdentifier() != null ? "update contact set first_name=?, last_name=?, phone_number=? where identifier=?"
        : "insert into contact(first_name, last_name, phone_number) values(?, ?, ?)";

    this.getJdbcTemplate()
      .update((PreparedStatementCreator) (action -> {
        PreparedStatement ps = action.prepareStatement(query);
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getPhoneNumber());
        if (user.getIdentifier() != null) {
          ps.setLong(4, user.getIdentifier());
        }
        return ps;
      }));
  }

  public void deleteUser(Long identifier) {
    this.getJdbcTemplate()
      .update((PreparedStatementCreator) (action -> {
        PreparedStatement ps = action.prepareStatement("delete from contact where identifier=?");
        ps.setLong(1, identifier);
        return ps;
      }));
  }

  public User findById(Long id) {
    return this.getJdbcTemplate()
      .execute((PreparedStatementCreator) (conn -> {
        PreparedStatement ps = conn.prepareStatement("select identifier, first_name, last_name, phone_number from contact where identifier = ?");
        ps.setLong(1, id);
        return ps;
      }), action -> {
        ResultSet rs = action.executeQuery();
        User user = null;
        if (rs.next()) {
          user = new User();
          user.setIdentifier(rs.getLong("identifier"));
          user.setFirstName(rs.getString("first_name"));
          user.setLastName(rs.getString("last_name"));
          user.setPhoneNumber(rs.getString("phone_number"));
        }
        return user;
      });
  }
  
  
  public Collection<User> findByCriteria(UserCriteria criteria){
    StringBuffer queryBuffer = new StringBuffer("select identifier, first_name, last_name, phone_number from contact where 1=1");
    List<String> queryParameter = new ArrayList<>();
    if(!isNullOrEmpty(criteria.getFirstName())){
      queryBuffer.append(" and first_name=?");
      queryParameter.add(criteria.getFirstName());
    }
    if(!isNullOrEmpty(criteria.getName())){
      queryBuffer.append(" and last_name=?");
      queryParameter.add(criteria.getName());
    }
    
    return this.getJdbcTemplate().query(queryBuffer.toString(), (PreparedStatementSetter)ps -> {
      int parameterIndex =1;
      for(String param: queryParameter) {
        ps.setString(parameterIndex++, param);
      }
    },(rse, index) ->{
      User user = null;
      user = new User();
      user.setIdentifier(rse.getLong("identifier"));
      user.setFirstName(rse.getString("first_name"));
      user.setLastName(rse.getString("last_name"));
      user.setPhoneNumber(rse.getString("phone_number"));
      return user;
    });
  }
  
  private boolean isNullOrEmpty(String string) {
    return string == null || string.trim().length() == 0;
  }

}
