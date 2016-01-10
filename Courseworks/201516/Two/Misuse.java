class Misuse {
  public static void main(final String[] args) {
    System.out.println(Double.NaN > 0);
    System.out.println(Double.NaN < 0);
    System.out.println(Double.NaN == 0);
    System.out.println(Math.max(Double.NaN, 5));
    System.out.println(Math.max(Double.NaN, Double.POSITIVE_INFINITY));
  }
}
