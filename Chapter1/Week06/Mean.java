class Mean {
  public static void main(final String[] args) {
    if (args.length == 0) return;
    final long N = Long.parseLong(args[0]);
    if (N <= 0) return;
    long count = 0;
    double sum = 0;
    while (count < N && ! StdIn.isEmpty()) {
      sum += StdIn.readDouble();
      ++count;
    }
    if (count < N) {
      System.err.println("N=" + N + " numbers expexted, but only "
        + count + " found!");
      System.exit(1);
    }
    System.out.println(sum/N);
  }
}
