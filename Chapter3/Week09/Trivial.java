class Trivial {

  private final int a;
  private final int b;
  // One could make these data members public, but the specification
  // (the "API") didn't mention them; however, typically one would
  // want to have the possibility to retrieve this data from a
  // Trivial-object (which is now not possible, given the above
  // qualification as private).

  public Trivial(final int x, final int y) {
    a = x;
    b = y;
  }

  public int sum() {
    return a+b;
  }

  public String toString() {
    return a + ", " + b;
  }
}
