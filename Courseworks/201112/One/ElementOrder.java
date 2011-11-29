// Oliver Kullmann, 25.10.2011 (Swansea)

class ElementOrder {
  static final String errmess = "ERROR[ElementOrder]: ";
  static final int errcode_args = 1;
  static final int errcode_n = 2;
  static final int errcode_int = 3;
  static final int errcode_mem = 4;

  public static void main(final String[] args) {
    if (args.length != 2) {
      System.err.println(errmess + "Precisely two arguments are needed, while you entered " +
        args.length + " arguments.");
      System.exit(errcode_args);
    }
    try {
      final int n = Integer.parseInt(args[1]);
      if (n <= 0) {
        System.err.println(errmess + "n must be positive, while it is " + n + ".");
        System.exit(errcode_n);
      }
      final int a = Integer.parseInt(args[0]) % n;
      final boolean[] found = new boolean[n];
      found[a] = true;
      int order = 1;
      long power = ((long) a * a) % n;
      while (! found[(int) power]) {
        ++order;
        found[(int) power] = true;
        power = (power * a) % n;
      }
      System.out.println(order);
    }
    catch (final NumberFormatException e) {
      System.err.println(errmess + "One of the parameters was not an integer.");
      System.exit(errcode_int);
    }
    catch (final OutOfMemoryError e) {
      System.err.println(errmess + "Parameter n is too big for the available memory.");
      System.exit(errcode_mem);
    }
  }
}
