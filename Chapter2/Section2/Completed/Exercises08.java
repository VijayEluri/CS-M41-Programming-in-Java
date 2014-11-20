class Exercises08 {

  public static double sgn(final double x) {
    if (x < 0) return -1;
    if (x > 0) return +1;
    return 0;
  }
  
  public static int fld(final double x) {
    assert(! Double.isNaN(x));
    assert(x != Double.POSITIVE_INFINITY);
    assert(x != Double.NEGATIVE_INFINITY);
    int n = 0;
    double p = 1;
    if (x == p) return n;
    if (x > p) {
      do { ++n; p *= 2; }
      while (p <= x);
      return n-1;
    }
    assert(x < p);
    do {--n; p /= 2; }
    while (p > x);
    return n;
  }
  
  public static boolean and(final boolean[] a) {
    if (a == null) return true;
    for (int i = 0; i < a.length; ++i) if (!a[i]) return false;
    return true;
  }
  
  public static boolean xor(final boolean[] a) {
    if (a == null) return false;
    int count = 0;
    for (int i = 0; i < a.length; ++i) if (a[i]) ++count;
    return count % 2 == 1;
  }
  
  private static boolean i2b(final int i) {return i != 0; }
  
  /* Run with "java -ea Exercises08". */
  public static void main(final String[] args) {
  
    assert(sgn(-2) == -1);
    assert(sgn(0.0) == 0);
    assert(sgn(4.7) == 1);
    assert(sgn(Double.NaN) == 0); // so well
    assert(sgn(Double.POSITIVE_INFINITY) == 1);
    assert(sgn(Double.NEGATIVE_INFINITY) == -1);
    
    assert(fld(4) == 2);
    assert(fld(256) == 8);
    assert(fld(100) == 6);
    assert(fld(2.7) == 1);
    assert(fld(2) == 1);
    assert(fld(1) == 0);
    assert(fld(0.8) == -1);
    assert(fld(0.5) == -1);
    assert(fld(0.4) == -2);
    assert(fld(1/64.0) == -6);
    assert(fld(1/65.0) == -7);
    // fld(Double.NaN) yields nonsensical result -1
    // fld(Double.POSITIVE_INFINITY) yields infinite loop.
    // fld(Double.NEGATIVE_INFINITY) yields infinite loop.
    
    assert(and(null) == true);
    assert(xor(null) == false);
    boolean[] a = new boolean[0];
    assert(and(a) == true);
    assert(xor(a) == false);
    a = new boolean[1];
    assert(and(a) == false);
    assert(xor(a) == false);
    a[0] = true;
    assert(and(a) == true);
    assert(xor(a) == true);
    a = new boolean[3];
    for (int i = 0; i <= 1; ++i) {
      a[0] = i2b(i);
      for (int j = 0; j <= 1; ++j) {
        a[1] = i2b(j);
        for (int k = 0; k <= 1; ++k) {
          a[2] = i2b(k);
          final int sum = i+j+k;
          assert(and(a) == (sum == 3));
          assert(xor(a) == (sum % 2 == 1));
        }
      }
    }
  }
}
