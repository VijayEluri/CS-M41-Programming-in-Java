class MinMax2 {
  public static void main(final String[] args) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    while (!StdIn.isEmpty()) {
      int x = StdIn.readInt();
      while (x <= 0) {
        System.err.println("All numbers must be positive, and thus " + x +
          " is not allowed -- please reenter:");
        x = StdIn.readInt();
      }
      if (x > max) max = x;
      if (x < min) min = x;
    }
    System.out.println(min);
    System.out.println(max);
  }
}
