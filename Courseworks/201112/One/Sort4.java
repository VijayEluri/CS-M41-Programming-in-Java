// Oliver Kullmann, 25/10/2011 (Swansea)

class Sort4 {
  static final String errmess = "ERROR[Sort4]: ";
  static final int err_exit_param = 1;
  static final int err_exit_int = 2;

  public static void main(final String[] args) {
    if (args.length != 4) {
      System.err.println(errmess + "Precisely four arguments are needed, while you entered " +
        args.length + " arguments.");
      System.exit(err_exit_param);
    }
    try {
      int a = Integer.parseInt(args[0]);
      int b = Integer.parseInt(args[1]);
      int c = Integer.parseInt(args[2]);
      final int d = Integer.parseInt(args[3]);

      // Sort a, b, c
      if (b < a) { final int t=a; a=b; b=t; }
      if (c < b) { final int t=b; b=c; c=t; }
      if (b < a) { final int t=a; a=b; b=t; }
      assert(a <= b);
      assert(b <= c);

      if (d < b)
        if (d < a) System.out.println(d + " " + a + " " + b + " " + c);
        else System.out.println(a + " " + d + " " + b + " " + c);
      else
        if (d < c) System.out.println(a + " " + b + " " + d + " " + c);
        else System.out.println(a + " " + b + " " + c + " " + d);
    }
    catch (final NumberFormatException e) {
      System.err.println(errmess + "One of the parameters was not an integer.");
      System.exit(err_exit_int);
    }
  }
}
