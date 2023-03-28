package br.com.zupedu.cdd;

public class LoopsSimples {

  public void basic() {
    for (int i = 0; i < 5; i++) {
      System.out.println(i);
    }
  }

  public void forEach() {
    int[] intArr = { 0, 1, 2, 3, 4 };
    for (int num : intArr) {
      System.out.println(num);
    }
  }

  public void whileLoop() {
    int i = 0;
    while (i < 5) {
      System.out.println(i++);
    }
  }

  public void doWhile() {
    int i = 0;
    do {
      System.out.println("Do-While loop: i = " + i++);
    } while (i < 5);
  }
}
