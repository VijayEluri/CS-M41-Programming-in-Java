class Collatz {
  public static long parse(final String s) {
    if (s == null) return 0;
    long res;
    try { res = Long.parseLong(s); }
    catch (Exception e) { return 0;}
    return res;
  }
  public static boolean evenp(final long n) {
    return n % 2 == 0;
  }
  public static boolean oddp(final long n) {
    return n % 2 != 0;
  }
  public static long h(final long n) {
    return n/2;
  }
  public static long tpo(final long n) {
    return 3*n + 1;
  }
  public static long[] evaluation(long n) {
    assert(n >= 1);
    long count = 0;
    long max = n;
    while (n != 1) {
      n = evenp(n) ? h(n) : tpo(n);
      ++count;
      if (n > max) max = n;
    }
    final long[] res = {count, max};
    return res;
  }
  public static void output(final long[] res) {
    assert(res != null);
    assert(res.length == 2);
    System.out.println(res[0] + " " + res[1]);
  }
  public static void main(final String[] args) {
    if (args.length == 0) {
      System.err.println("ERROR[Collatz]: The argument n is needed.");
      System.exit(1);
    }
    final long n = parse(args[0]);
    if (n <= 0) {
      System.err.println("ERROR[Collatz]: n must be an integer from 1 to " + Long.MAX_VALUE + ".");
      System.exit(2);
    }
    output(evaluation(n));
  }
}
