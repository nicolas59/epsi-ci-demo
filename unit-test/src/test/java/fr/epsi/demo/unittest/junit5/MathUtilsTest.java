package fr.epsi.demo.unittest.junit5;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.rules.ExpectedException;

import fr.epsi.demo.unittest.MathUtils;

public class MathUtilsTest {
  
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @BeforeAll
  public static void initClass() throws Exception {
    System.out.println("Initialisation de la classe");
  }
  
  @BeforeEach
  public void setUp() throws Exception {
    System.out.println("Pré-initialisation du test");
  }

  @Test
  public void should_return_one_when_number_is_zero() {
    System.out.println("Verification factorielle 0");
    Assertions.assertEquals(1L, MathUtils.factoriel(0L));
  }
  
  @Test
  public void should_return_big_number_when_number_is_one_hundred() {
    System.out.println("Verification factorielle 100");
    Assertions.assertEquals(3_628_800L, MathUtils.factoriel(10L), "La valeur n'est pas correcte");
  }
  
  @DisplayName("Doit founir la bonne valeur")
  @ParameterizedTest(name = "{index} => number={0}, expected={1}")
  @CsvSource({
          "0, 1",
          "1, 1",
          "2, 2",
          "3, 6"
  })
  public void should_provide_correct_value(long number, long expected) {
    Assertions.assertEquals(expected, MathUtils.factoriel(number));
  }
  
  
  @Test
  public void should_throw_exception_when_number_is_negative() {
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage(CoreMatchers.equalTo("Le nombre doit être positif"));
    MathUtils.factoriel(-12);
  }
  
  @AfterEach
  public void tearDown() throws Exception {
    System.out.println("Demontage du test");
  }
  
  @AfterAll
  public static void tearDownClass() throws Exception {
    System.out.println("Demontage de la classe de test");
  }
}
