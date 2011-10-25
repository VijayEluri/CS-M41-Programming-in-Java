// Oliver Kullmann, 25.10.2011 (Swansea)

class DiscreteLogarithm {
  static final String errmess = "ERROR[DiscreteLogarithm]: ";
  static final int errcode_args = 1;
  static final int errcode_n = 2;
  public static void main(final String[] args) {
    if (args.length != 3) {
      System.err.println(errmess + "Precisely three arguments are needed, while you entered " +
        args.length + " many.");
      System.exit(errcode_args);
    }
    final int n = Integer.parseInt(args[2]);
    if (n <= 0) {
      System.err.println(errmess + "n must be positive, while it is " + n + ".");
      System.exit(errcode_n);
    }
    if (n == 1) {
      System.out.println(0);
      return;
    }
    final int a = Integer.parseInt(args[0]) % n;
    final int b = Integer.parseInt(args[1]) % n;
    int exp = 0, power = 1;
    while (power != a && exp++ < n) power = (power * b) % n;
    if (power == a) System.out.println(exp);
    else System.out.println(-1);
  }
}
