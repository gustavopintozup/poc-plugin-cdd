package br.com.stackedu.cdd;

public class AnonymousSimples {

  public void get() {
    int count = 1;
    Runnable action = new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable with captured variables: " + count);
      }
    };
  }
}
