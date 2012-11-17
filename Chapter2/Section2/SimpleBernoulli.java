class SimpleBernoulli {
  private static boolean bernoulli(final double p) {
    return Math.random() < p;
  }
  private static int bernoulli_r(final double p, final int N) {
    int count = 0;
    for (int i = 0; i < N; ++i) if (bernoulli(p)) ++count;
    return count;
  }
  public static void main(final String[] args) {
    final int N = Integer.parseInt(args[0]);
    final double p = Double.parseDouble(args[1]);
    System.out.println(bernoulli_r(p,N));
  }
}
