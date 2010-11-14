public class Max3 {

  public static int max(final int a, final int b) {
    if (a>b) return a;
    else return b;
  }

  public static XXX max(XXX, XXX, XXX) {
    return XXX;
  }
  /* We can use the same name for functions in case the input is
  different. This is called *overloading* and is very useful ---
  the compiler shall find out which version to use, not the programmer!
  */

  public static void main(final String[] args) {
    final int a = Integer.parseInt(args[0]);
    final int b = Integer.parseInt(args[1]);
    final int c = Integer.parseInt(args[2]);
    System.out.println("The maximum of " + a + ", " + b + ", " + c + " is " + max(a,b,c) + ".");
  }
}
