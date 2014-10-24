class Cases {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("Nothing to be done.");
      System.exit(0);
    }
    if (nargs == 1) {
      System.out.println("Hello, " + args[0] + "!");
      System.exit(0);
    }
    if (nargs == 2) {
      final int a = Integer.parseInt(args[0]);
      final int b = Integer.parseInt(args[1]);
      if (b == 0)
        System.out.println("a = " + a);
      else
        System.out.println("a % b = " + (a % b));
      System.exit(0);
    }
    if (nargs == 3) {
      final double a = Double.parseDouble(args[0]);
      final double b = Double.parseDouble(args[1]);
      final double c = Double.parseDouble(args[2]);
      if (a <= b)
        if (b <= c) System.out.println(a + " " + b + " " + c);
        else if (c < a) System.out.println(c + " " + a + " " + b);
        else System.out.println(a + " " + c + " " + b);
      else if (a <= c) System.out.println(b + " " + a + " " + c);
      else if (c < b) System.out.println(c + " " + b + " " + a);
      else System.out.println(b + " " + c + " " + a);
      System.exit(0);
    }
  }
}
