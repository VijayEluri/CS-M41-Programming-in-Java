class Functions {

  public static boolean leq(final int a, final int b) {
    return a <= b;
  }
  
  public static double sgn(final double x) {
    return (x > 0) ? 1 : ((x < 0) ? -1 : 0);
  }
  
  public static boolean eqarr(final int[] a, final int[] b) {
    if (a == b) return true;
    if (a == null) return false;
    assert(a != null && b != null);
    if (a.length != b.length) return false;
    for (int i = 0; i < a.length; ++i)
      if (a[i] != b[i]) return false;
    return true;
  }

  public static boolean and(final boolean[] a) {
    if (a == null) return true;
    for (int i = 0; i < a.length; ++i)
      if (!a[i]) return false;
    return true;
  }

  public static int fld(final double x) {
    assert(x > 0);
    if (x == 1) return 0;
    if (x == Double.POSITIVE_INFINITY) return Integer.MAX_VALUE;
    if (x > 1) {
      int result = 1;
      for (double pow = 2; pow <= x; ++result, pow *= 2);
      return result-1;
    }
    else {
      assert(x < 1);
      int result = -1;
      for (double pow = 1.0/2; pow > x; --result, pow /= 2);
      return result;
    }
  }
  
  public static void main(final String[] args) {
    assert(leq(0,0));
    assert(leq(0,1));
    assert(!leq(1,0));
    
    assert(sgn(1) == 1);
    assert(sgn(-1) == -1);
    assert(sgn(0) == 0);
    assert(sgn(Double.POSITIVE_INFINITY) == 1);
    assert(sgn(Double.NEGATIVE_INFINITY) == -1);
    assert(sgn(Double.MAX_VALUE) == 1);
    assert(sgn(Double.MIN_VALUE) == 1);
    assert(sgn(-Double.MIN_VALUE) == -1);

    assert(eqarr(null, null));
    {
      int[] a = new int[0];
      assert(eqarr(a,a));
      int[] b = new int[0];
      assert(eqarr(a,b));
      b = new int[1];
      assert(! eqarr(a,b));
      a = new int[1];
      assert(eqarr(a,b));
      b = new int[2];
      assert(! eqarr(a,b));
      a = new int[2];
      assert(eqarr(a,b));
      a[0] = 1;
      assert(! eqarr(a,b));
    }

    assert(and(null));
    {
      boolean[] a = new boolean[0];
      assert(and(a));
      a = new boolean[1];
      assert(!and(a));
      a[0] = true;
      assert(and(a));
      a = new boolean[2];
      assert(!and(a));
      a[0] = true;
      assert(!and(a));
      a[1] = true;
      assert(and(a));
    }

    assert(fld(1) == 0);
    assert(fld(Double.POSITIVE_INFINITY) == Integer.MAX_VALUE);
    assert(fld(128) == 7);
    assert(fld(127) == 6);
    assert(fld(Math.pow(2,31)) == 31);
    assert(fld(Math.pow(2,31) + 1) == 31);
    assert(fld(Double.MAX_VALUE) == 1023);
    assert(fld(Math.pow(2,1024)) == Integer.MAX_VALUE);
    assert(fld(0.9) == -1);
    assert(fld(0.5) == -1);
    assert(fld(0.499) == -2);
    assert(fld(0.4) == -2);
    assert(fld(0.25) == -2);
    assert(fld(0.24999) == -3);
    assert(fld(Double.MIN_VALUE) == -1074);
  }
}
