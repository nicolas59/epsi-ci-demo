package fr.epsi.demo.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.epsi.demo.unittest.bean.User;

public class SpyDemo {

  @Test
  public void should_modify_array_size() {
    List<User> users = new ArrayList<>();
    users.add(new User(1L, "Daenerys", "Targaryen", "0611111111"));
    users.add(new User(2L, "Jon", "Snow", "0711111111"));
    
    assertEquals(2, users.size());
    
    List<User> spy = spy(users);
    when(spy.size()).thenReturn(10);
    
    assertEquals(10, spy.size());
    
    User r1 = spy.get(0);
    assertEquals(r1.getFirstName(), "Daenerys");
  }
  
}
