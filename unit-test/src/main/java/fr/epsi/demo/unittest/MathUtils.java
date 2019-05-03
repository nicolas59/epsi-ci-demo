package fr.epsi.demo.unittest;

public class MathUtils {

  public static long factoriel(long n) throws IllegalArgumentException{
    if(n < 0) {
      throw new IllegalArgumentException("Le nombre doit être positif");
    }
    if(n == 0) {
      return 1L;
    }else {
      return n * factoriel(--n);
    }
  }
}
