public class PassByValue {
  static void update(int a, int[] b, String c) {
    a    = 7;
    b[3] = 7;
    c    = "seven";
    System.out.println(a + " " + b[3] + " " + c);
  }
  public static void main(String[] args) {
    int a = 3;
    int[] b = { 0, 1, 2, 3, 4, 5 };
    String c = "three";
    System.out.println(a + " " + b[3] + " " + c);
    update(a, b, c);
    System.out.println(a + " " + b[3] + " " + c);
  }
}
