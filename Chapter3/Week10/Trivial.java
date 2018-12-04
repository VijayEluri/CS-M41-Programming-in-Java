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

  public String toString() {
    return a + ", " + b;
  }

  public boolean equals(final Trivial other) {
    if (other == null) return false;
    return ((a==other.a) && (b==other.b)) || ((a==other.b) && b==other.a);
  }
  // We note that two Trivial-objects can be equal, while having different
  // string-translations -- but this is natural, since toString() is
  // typically used for debugging purposes, and thus typically prints
  // the *full* *internal* representation.

  public Trivial multiply(final int m) {
    return new Trivial(a*m, b*m);
  }

  public boolean safe_add() {
    final int sum = a + b;
    if (a >= 0 && b >= 0)
      return sum >= a && sum >= b;
    else if (a <= 0 && b <= 0)
      return sum <= a && sum <= b;
    else if (a >= 0 && b <= 0)
      return sum <= a && sum >= b;
    else
      return sum >= a && sum <= b;
  }
  public boolean safe_prod() {
    final long prod = (long) a * b;
    return prod >= Integer.MIN_VALUE && prod <= Integer.MAX_VALUE;
  }
  
  public static long sum(final Trivial[] a) {
    if (a == null) return 0;
    long sum = 0;
    for (int i = 0; i < a.length; ++i) sum += a[i].prod();
    return sum;
  }
  

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
    final int min = Integer.MIN_VALUE;
    final int max = Integer.MAX_VALUE;
    assert((new Trivial(max,0)).safe_add());
    assert((new Trivial(max,-1)).safe_add());
    assert(!(new Trivial(max,1)).safe_add());
    assert((new Trivial(0,max)).safe_add());
    assert((new Trivial(-1,max)).safe_add());
    assert(!(new Trivial(1,max)).safe_add());
    assert((new Trivial(min,0)).safe_add());
    assert(!(new Trivial(min,-1)).safe_add());
    assert((new Trivial(min,1)).safe_add());
    assert((new Trivial(0,min)).safe_add());
    assert(!(new Trivial(-1,min)).safe_add());
    assert((new Trivial(1,min)).safe_add());
    assert((new Trivial(1,min)).safe_prod());
    assert(!(new Trivial(-1,min)).safe_prod());
    assert((new Trivial(1,max)).safe_prod());
    assert((new Trivial(-1,max)).safe_prod());
    assert(sum(null) == 0);
    assert(sum(new Trivial[]{new Trivial(3,4), new Trivial(-3,5), new Trivial(5,6)}) == 3*4+(-3)*5+5*6);
  }
}
