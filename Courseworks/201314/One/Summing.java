class Summing {
  public static void main(final String[] args) {
    final int N = args.length-1;

    final int error_no_args = 1;
    final int error_zero = 2;

    if (N == -1) System.exit(error_no_args);
    final int k = Integer.parseInt(args[0]);
    if (k == 0) {System.out.println(N); return;}
    final boolean division = k < 0;
    final int ak = Math.abs(k);
    
    final double[] a = new double[N];
    for (int i = 0; i < N; ++i) {
      final double p = Double.parseDouble(args[i+1]);
      if (division && p == 0) System.exit(error_zero);
      a[i] = p;
    }

    double sum = 0;
    int i;
    for (i = 0; i < N / ak; ++i) {
      double prod = 1;
      for (int j = 0; j < ak; ++j)
        prod *= a[i*ak + j];
      if (division) sum += 1/prod;
      else sum += prod;
    }
    if (ak * i < N) {
      double prod = 1;
      for (int j = ak * i; j < N; ++j) prod *= a[j];
      if (division) sum += 1/prod;
      else sum += prod;
    }

    System.out.println(sum);
  }
}
