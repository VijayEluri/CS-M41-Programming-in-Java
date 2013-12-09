class Summing1 {
  public static final int err_par=1, err_zero=2;
  public static void main(final String[] args) {
    final int N = args.length;
    if (N == 0) System.exit(err_par);
    final int k = Integer.parseInt(args[0]);
    if (k == 0) {System.out.println(N-1); return;}
    final boolean reciprocal = k < 0;
    final int ak = Math.abs(k);
    double sum = 0, prod = 0;
    for (int i = 1; i < N; ++i) {
      double val = Double.parseDouble(args[i]);
      if (reciprocal) if (val == 0) System.exit(err_zero); else val = 1/val;
      if (ak == 1 || i % ak == 1) { sum += prod; prod = val; }
      else prod *= val;
    }
    System.out.println(sum+prod);
  }
}
