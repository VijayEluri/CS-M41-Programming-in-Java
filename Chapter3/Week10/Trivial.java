class Trivial {

  private final int a;
  private final int b;
  // One could make these data members public, but the specification
  // (the "API") didn't mention them. This is a possible design weakness,
  // since typically one would want to have the possibility to retrieve this
  // data from a Trivial-object, which is now not possible, given the above
  // qualification as private -- but perhaps this *abstraction* is what
  // one wants?! Actually, these members "leak" via toString.

  public Trivial(final int x, final int y) {
    a = x;
    b = y;
  }

  public int sum() { return a+b; }
  public int prod() { return a*b; }

  public Trivial multiply(final int m) {
    return new Trivial(a*m, b*m);
  }

  public boolean equals(final Trivial other) {
    return ((a==other.a) && (b==other.b)) || ((a==other.b) && b==other.a);
  }
  public String toString() {
    return a + ", " + b;
  }
  // We note that two Trivial-objects can be equal, while having different
  // string-translations -- but this is natural, since toString() is
  // typically used for debugging purposes, and thus typically prints
  // the *full* *internal* representation.

  // Run with "java -ea" (to enable the assertions)
  public static void main(final String[] args) {
    final Trivial T = new Trivial(5,11);
    assert(T.sum() == 16);
    assert(T.prod() == 55);
    assert(T.equals(new Trivial(5,11)));
    assert(T.equals(new Trivial(11,5)));
    assert(! T.equals(new Trivial(5,10)));
    assert(! T.equals(new Trivial(6,11)));
    assert(T.toString().equals("5, 11"));
    final Trivial T2 = T.multiply(3);
    assert(T2.a == 15);
    assert(T2.b == 33);
  }
}
