class Missing {
  public static void main(final String[] args) {
    final int N = Integer.parseInt(args[0]);
    if (N <= 0) {
      System.err.print("The number N of values must be positive,");
      System.err.println(" but N = " + N + ".");
      System.exit(1);
    }
    boolean[] found = new boolean[N];
    for (int read=0; !StdIn.isEmpty() && read < N-1;) {
      final int x = StdIn.readInt();
      if (x >= 1 && x <= N && !found[x-1]) {
        found[x-1] = true;
        ++read;
      }
    }
    for (int i = 0; i < N; ++i)
      if (! found[i]) {
        System.out.println(i+1);
        return;
      }
  }
}
