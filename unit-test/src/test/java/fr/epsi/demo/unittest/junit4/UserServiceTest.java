package fr.epsi.demo.unittest.junit4;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.epsi.demo.unittest.bean.User;
import fr.epsi.demo.unittest.dao.UserCriteria;
import fr.epsi.demo.unittest.dao.UserJdbcDao;
import fr.epsi.demo.unittest.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  @Mock
  private UserJdbcDao userJdbcDao;

  private UserService userService;

  @Before
  public void setUp() {
    this.userService = new UserService();
    this.userService.setUserJdbcDao(this.userJdbcDao);
  }

  @Test
  public void shoud_return_all_users() {
    User u1 = new User(1L, "Daenerys", "Targaryen", "0611111111");
    Mockito.when(this.userJdbcDao.findAll())
      .thenReturn(Arrays.asList(u1));

    Collection<User> ret = this.userService.findAll();
    Assert.assertFalse(ret.isEmpty());
    Assert.assertArrayEquals(new User[] { u1 }, ret.toArray());

  }

  @Test
  public void should_add_new_user() {
    User u1 = new User(null, "Daenerys", "Targaryen", "0611111111");
    this.userService.addUser(u1);
    verify(this.userJdbcDao, atLeastOnce()).addUser(eq(u1));
  }
  
  
  @Test
  public void should_find_user_when_user_exists() {
    User u1 = new User(1L, "Daenerys", "Targaryen", "0611111111");
    User u2 = new User(2L, "Jon", "Snow", "0711111111");

    when(this.userJdbcDao.findById(eq(1L))).thenReturn(u1);
    when(this.userJdbcDao.findById(eq(2L))).thenReturn(u2);
    
    User r1 = this.userService.findById(1L).orElseThrow(() -> fail("Utilisateur non trouvé"));
    Assert.assertEquals((Long)1L, r1.getIdentifier());
    Assert.assertEquals("Daenerys", r1.getFirstName());
    
    User r2 = this.userService.findById(2L).orElseThrow(() -> fail("Utilisateur non trouvé"));
    Assert.assertEquals((Long)2L, r2.getIdentifier());
    Assert.assertEquals("Jon", r2.getFirstName());
    
  }

  @Test
  public void should_find_user_by_first_name() {
    //Appel du service 
    this.userService.findUserBy("Daenerys", null);
    
    //Capture du dao appelé 
    ArgumentCaptor<UserCriteria> captor = ArgumentCaptor.forClass(UserCriteria.class);
    verify(this.userJdbcDao).findByCriteria(captor.capture());
    
    //Vérification du passage de criteres
    UserCriteria userCriteria = captor.getValue();
    
    //Vérification des données
    Assert.assertNull(userCriteria.getName());
    Assert.assertEquals("Daenerys", userCriteria.getFirstName());
  }
}
