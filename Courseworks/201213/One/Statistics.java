public class Statistics {
  public static void main(final String[] args) {
    final int N = args.length;
    System.out.println(N);
    if (N > 0) {
      final double[] a = new double[N];
      for (int i = 0; i < N; ++i) a[i] = Double.parseDouble(args[i]);
      double min = Double.POSITIVE_INFINITY;
      double max = Double.NEGATIVE_INFINITY;
      double sum = 0.0;
      for (int i = 0; i < N; ++i) {
        final double v = a[i];
        if (v < min) min = v;
        if (v > max) max = v;
        sum += v;
      }
      System.out.println(min);
      System.out.println(max);
      System.out.println(sum);
      System.out.println(sum/N);
    }
  }
}
