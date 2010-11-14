public class Max4 {

  public static int max(final int a, final int b) {
    if (a>b) return a;
    else return b;
  }
  public static int max(final int a, final int b, final int c) {
    return max(max(a,b),c);
  }
  XXX max(XXX) {
    XXX
  }

  public static void main(final String[] args) {
    final int a = Integer.parseInt(args[0]);
    final int b = Integer.parseInt(args[1]);
    final int c = Integer.parseInt(args[2]);
    final int d = Integer.parseInt(args[3]);
    StdOut.printf("The maximum of %d, %d, %d, %d is %d.\n", a,b,c,d,max(a,b,c,d));
  }
}
