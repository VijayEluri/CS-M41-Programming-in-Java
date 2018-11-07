class MinMax {
  public static void main(final String[] args) {
    long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
    while (!StdIn.isEmpty()) {
      final long x = StdIn.readLong();
      if (x > max) max = x;
      if (x < min) min = x;
    }
    System.out.println(min);
    System.out.println(max);
  }
}
