package fr.epsi.demo.unittest.junit3;

import org.junit.runner.RunWith;

import fr.epsi.demo.unittest.MathUtils;
import junit.framework.TestCase;

public class MathUtilsTest extends TestCase {

  @Override
  protected void setUp() throws Exception {
    System.out.println("Pré-initialisation du test");
  }

  public void testFactorialZero() {
    System.out.println("Verification factorielle 0");
    assertEquals(1L, MathUtils.factoriel(0L));
  }

  public void testFactorialTen() {
    System.out.println("Verification factorielle 100");
    assertEquals(3_628_800L, MathUtils.factoriel(10L));
  }

  public void testNegativeNumber() {
    try{
      MathUtils.factoriel(-12);
      fail(); // On ne doit pas passer ici
    }catch(IllegalArgumentException e) {
      
    }
  }

  @Override
  protected void tearDown() throws Exception {
    System.out.println("Demontage du test");
  }

}
