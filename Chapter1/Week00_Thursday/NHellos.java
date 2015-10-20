class NHellos {
  public static void main(final String[] args) {
    if (args.length < 2) {
      System.err.println("Two arguments N, M are needed, but only "
       + args.length + " arguments have been provided.");
      System.exit(1);
    }
    final int N = Integer.parseInt(args[0]);
    final int M = Integer.parseInt(args[1]);
    for (int i = 0; i < N; ++i) {
      System.out.print("Hello, World");
      for (int j = 0; j < M; ++j) System.out.print("!");
      System.out.println();
    }
  }
}
