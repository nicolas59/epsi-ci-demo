package fr.epsi.demo.unittest.junit5.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.epsi.demo.unittest.bean.User;
import fr.epsi.demo.unittest.dao.UserJdbcDao;
import fr.epsi.demo.unittest.service.UserService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class UserServiceTest {
  @Mock
  private UserJdbcDao userJdbcDao;
  
  private UserService userService;
  
  @BeforeEach
  public void setUp() {
    this.userService = new UserService();
    this.userService.setUserJdbcDao(this.userJdbcDao);
  }
  
  @Test
  public void shoud_return_all_users() {
    User u1 = new User(1L, "Daenerys", "Targaryen", "0611111111");
    when(this.userJdbcDao.findAll()).thenReturn(Arrays.asList(u1));
    
    Collection<User> ret = this.userService.findAll();
    Assertions.assertFalse(ret.isEmpty());
    Assertions.assertArrayEquals(new User[] {u1}, ret.toArray());
    
  }
}
