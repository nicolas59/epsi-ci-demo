package fr.epsi.demo.unittest.service;

import java.util.Collection;
import java.util.Optional;

import fr.epsi.demo.unittest.bean.User;
import fr.epsi.demo.unittest.dao.UserCriteria;
import fr.epsi.demo.unittest.dao.UserJdbcDao;

public class UserService {

  private UserJdbcDao userJdbcDao;
  
  public Collection<User> findAll(){
    return this.userJdbcDao.findAll();
  }

  public void setUserJdbcDao(UserJdbcDao userJdbcDao) {
    this.userJdbcDao = userJdbcDao;
  }
  
  public Optional<User> findById(Long id){
    return Optional.ofNullable(this.userJdbcDao.findById(id));
  }
  
  public void addUser(User user) {
    this.userJdbcDao.addUser(user);
  }
  
  
  public Collection findUserBy(String firstName, String lastName) {
    UserCriteria criteria = new UserCriteria();
    criteria.setFirstName(firstName);
    criteria.setName(lastName);
    return this.userJdbcDao.findByCriteria(criteria);
  }
}
