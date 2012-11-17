class Count {

  private static int[] count(final double[] a, final int N) {
    assert(a != null);
    int[] result = new int[a.length];
    if (a.length == 0) return result;
    for (int i = 0; i < N; ++i) ++result[StdRandom.discrete(a)];
    return result;
  }
  
  private static void output(final int[] c) {
    assert(c != null);
    for (int i = 0; i < c.length; ++i) System.out.print(c[i] + " ");
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
