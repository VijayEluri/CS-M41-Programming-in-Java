public class Max2 {

  public static int max(final int a, final int b) {
    if (a>b) return a;
    else return b;
  }
  /* Alternatives are:
    1.
      if (a>b) return a;
      return b;
    2.
      return (a>b) ? a : b;
  */

  public static void main(final String[] args) {
    final int a = Integer.parseInt(args[0]);
    final int b = Integer.parseInt(args[1]);
    System.out.println("The maximum of " + a + " and " + b + " is " + max(a,b) + ".");
  }
}
