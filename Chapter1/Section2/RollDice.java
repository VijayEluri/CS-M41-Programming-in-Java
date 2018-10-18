class RollDice {
  public static void main(final String[] args) {
    final int dice = 6;
    final double r1 = Math.random();
    final int d1 = (int) (r1 * dice) + 1;
    final double r2 = Math.random();
    final int d2 = (int) (r2 * dice) + 1;
    System.out.println(d1 + d2);
  }
}
