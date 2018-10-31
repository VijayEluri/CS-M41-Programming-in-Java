class Collector {
  public static final int num_args_error = 1;
  public static final int N_val_error = 2;
  public static final int T_val_error = 3;
  public static final int k_val_error = 4;
  public static final int max_steps_error = 5;
  
  public static final long max_steps = Long.MAX_VALUE - 1;
  
  public static void main(final String[] args) {
    if (args.length != 3) {
      System.err.println("Exactly three command-line arguments needed, " +
        "N, T and k, but " + args.length + " provided.");
      System.exit(num_args_error);
    }
    final int N = Integer.parseInt(args[0]);
    if (N < 1) {
      System.err.println("N=" + N + ", but must be at least 1.");
      System.exit(N_val_error);
    }
    final long T = Long.parseLong(args[1]);
    if (T < 1) {
      System.err.println("T=" + T + ", but must best at least 1.");
      System.exit(T_val_error);
    }
    final long k = Long.parseLong(args[2]);
    if (k < 1) {
      System.err.println("k=" + k + ", but must be at least 1.");
      System.exit(k_val_error);
    }

    long minsteps = max_steps, maxsteps = 0, sum_steps = 0;
    // Remark: for very long experiments, sum_steps might overrun, and then
    // is only correct modulo 2^64.
    for (long i = 0; i != T; ++i) {
      final long[] counts = new long[N];
      long num_steps = 0;
      for (int num_values = 0; num_values != N;) {
        final int r = (int) (Math.random() * N);
        ++num_steps;
        if (num_steps == max_steps) {
          System.err.println("Reached max_steps = " + max_steps + ".");
          System.exit(max_steps_error);
        }
        ++counts[r];
        if (counts[r] == k) ++num_values;
      }
      sum_steps += num_steps;
      if (num_steps < minsteps) minsteps = num_steps;
      if (num_steps > maxsteps) maxsteps = num_steps;
    }
    
    System.out.println("min = " + minsteps);
    System.out.println("max = " + maxsteps);
    System.out.println("mean = " + sum_steps / (double) T);
  }
}
