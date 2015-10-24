// for-loops are better here, since we can make good use of the
// loop variable, but to show how you do it with while-loops:

class NHellos1 {
  public static void main(final String[] args) {
    final int N = Integer.parseInt(args[0]);
    final int M = Integer.parseInt(args[1]);
    int i = 0;
    while (i<N) {
      System.out.print("Hello, World");
      int j = 0;
      while (j < M) {
        System.out.print("!");
        ++j;
      }
      System.out.print("\n");
      ++i;
    }
  }
}
