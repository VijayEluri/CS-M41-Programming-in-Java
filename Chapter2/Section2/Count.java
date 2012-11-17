class Count {

  private static XXX count(XXX a, XXX) {
    assert(a != null);
    XXX result = XXX;
    if (a.length == 0) XXX;
    for (int i = 0; i < N; ++i) XXX;
    XXX;
  }
  
  private static XXX output(XXX c) {
    assert(c != null);
    for (int i = 0; i < c.length; ++i) System.out.print(XXX);
  }

  public static void main(final String[] args) {
    final int l = args.length;
    if (l == 0) {
      System.err.println("ERROR[Count]: at least the number of trials is needed.");
      System.exit(1);
    }
    final int N = Integer.parseInt(args[0]);
    final double[] a = new double[l-1];
    for (int i = 1; i < l; ++i) a[i-1] = Double.parseDouble(args[i]);
    output(count(a, N));
    System.out.println();
  }
}
