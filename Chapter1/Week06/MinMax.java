class MinMax {
  public static void main(final String[] args) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    while (!StdIn.isEmpty()) {
      final int x = StdIn.readInt();
      if (x > max) max = x;
      if (x < min) min = x;
    }
    System.out.println(min);
    System.out.println(max);
  }
}
