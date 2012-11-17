class Statistics {
  public static void main(final String[] args) {
    final int N = args.length;
    System.out.println(N);
    if (N == 0) return;
    final double[] a = new double[N];
    for (int i = 0; i < N; ++i) a[i] = Double.parseDouble(args[i]);
    System.out.println(StdStats.min(a));
    System.out.println(StdStats.max(a));
    System.out.println(StdStats.sum(a));
    System.out.println(StdStats.mean(a));
  }
}
