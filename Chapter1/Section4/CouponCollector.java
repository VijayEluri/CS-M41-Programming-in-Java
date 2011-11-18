// Oliver Kullmann, 1/11/2011 (Swansea)

class CouponCollector {
  private static final String err = "ERROR[CouponCollector]: ";
  public static final int errcode_args = 1;
  public static final int errcode_parse = 2;
  public static final int errcode_N = 3;

  public static void main(final String[] args) {
    if (args.length != 1) {
      System.err.println(err+"Precisely one argument is needed!");
      System.exit(errcode_args);
    }
    try {
      final int N = Integer.parseInt(args[0]);
      if (N <= 0) {
        System.err.println(err+"N must be positive!");
        System.exit(errcode_N);
      }
      int cardcnt = 0;
      // do simulation:
      {int valcnt = 0;
       for (boolean[] found = new boolean[N]; valcnt < N; ++cardcnt) {
         final int val = (int) (Math.random() * N);
         if (! found[val]) { ++valcnt; found[val] = true; }
       }
      }
      System.out.println("Measured:    " + cardcnt);
      double sum = 0;
      for (int i=1; i <= N; ++i) sum += 1.0/i;
      System.out.println("Theoretical: " + String.format("%.2f",N*sum,2));
    }
    catch (final Exception e) {
      System.err.println(err+"The parameter must be an integer!");
      System.exit(errcode_parse);
    }
  }
}
