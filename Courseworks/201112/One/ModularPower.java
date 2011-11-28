// Oliver Kullmann, 25.10.2011 (Swansea)

class ModularPower {
  static final String errmess = "ERROR[ModularPower]: ";
  static final int errcode_args = 1;
  static final int errcode_n = 2;
  static final int errcode_int = 3;

  public static void main(final String[] args) {
    if (args.length != 3) {
      System.err.println(errmess + "Precisely three arguments are needed, while you entered " +
        args.length + " arguments.");
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
    try {
      final int a = Integer.parseInt(args[0]) % n;
      final int e = Integer.parseInt(args[1]);
      long power = 1;
      for (int i = 0; i < e; ++i) power = (power * a) % n;
      System.out.println(power);
    }
    catch (final NumberFormatException e) {
      System.err.println(errmess + "One of the parameters was not an integer.");
      System.exit(errcode_int);
    }
  }
}
