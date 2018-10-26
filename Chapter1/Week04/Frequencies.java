class Frequencies {
  private static String error = "Error[Frequencies]: ";
  public static void main(final String[] args) {
    if (args.length < 2) {
      System.err.println(error + "Two arguments needed, N and T,"
       + " while only " + args.length + " were provided.");
      System.exit(1);
    }
    final int N = Integer.parseInt(args[0]);
    if (N <= 0) {
      System.err.println(error + "N=" + N + ", but N must be positive.");
      System.exit(2);
    }
    final long T = Long.parseLong(args[1]);
    if (T <= 0) return;
    long[] counts = new long[N];
    for (long i = 0; i < T; ++i) ++counts[(int)(Math.random()*N)];
    for (int i = 0; i < N; ++i)
      System.out.println(i + ": " + counts[i]/(double) T);
  }
}
