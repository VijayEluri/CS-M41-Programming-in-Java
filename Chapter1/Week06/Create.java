class Create {
  public static void main(final String[] args) {
    if (args.length == 0) return;
    final long N = Long.parseLong(args[0]);
    for (long i = 0; i < N; ++i) System.out.print((i+1) + " ");
    System.out.println();
  }
}
