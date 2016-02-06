class Misuse {
  public static void main(final String[] args) {
    System.out.println(Double.NaN > 0);
    System.out.println(Double.NaN < 0);
    System.out.println(Double.NaN == 0);
    System.out.println(Double.NaN == Double.NaN);
    System.out.println(Double.NaN != Double.NaN);
    System.out.println(Math.max(Double.NaN, 5));
    System.out.println(Math.max(Double.NaN, Double.POSITIVE_INFINITY));
    System.out.println(Double.MAX_VALUE < Double.POSITIVE_INFINITY);
    System.out.println(Double.MIN_VALUE > 0);
    System.out.println(-Double.MAX_VALUE > Double.NEGATIVE_INFINITY);
  }
}
