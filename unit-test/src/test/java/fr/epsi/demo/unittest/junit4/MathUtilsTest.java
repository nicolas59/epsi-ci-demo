package fr.epsi.demo.unittest.junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.demo.unittest.MathUtils;

public class MathUtilsTest {

  @BeforeClass
  public static void initClass() throws Exception {
    System.out.println("Initialisation de la classe");
  }
  
  @Before
  public void setUp() throws Exception {
    System.out.println("Pré-initialisation du test");
  }

  @Test
  public void should_return_one_when_number_is_zero() {
    System.out.println("Verification factorielle 0");
    Assert.assertEquals(1L, MathUtils.factoriel(0L));
  }
  
  @Test
  public void should_return_big_number_when_number_is_one_hundred() {
    System.out.println("Verification factorielle 100");
    Assert.assertEquals(3_628_800L, MathUtils.factoriel(10L));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void should_throw_exception_when_number_is_negative() {
    MathUtils.factoriel(-12);
  }
  
  @After
  public void tearDown() throws Exception {
    System.out.println("Demontage du test");
  }
  
  @AfterClass
  public static void tearDownClass() throws Exception {
    System.out.println("Demontage de la classe de test");
  }
}
