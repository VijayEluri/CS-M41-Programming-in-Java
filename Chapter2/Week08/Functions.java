class Functions {

  public static boolean leq(final int a, final int b) {
    return a <= b;
  }

  public static double sgn(final double x) {
    return (x > 0) ? 1 : ((x < 0) ? -1 : 0);
  }

  public static int[] fill(final int x, final int y, final int z) {
    return new int[]{x,y,z};
  }
  
  public static int[] sort(int x, int y, int z) {
    if (x > y) {final int t=x; x=y; y=t;}
    if (y > z) {final int t=y; y=z; z=t;}
    if (x > y) {final int t=x; x=y; y=t;}
    return fill(x,y,z);
  }

  public static long pow2(final int e) {
    assert(e >= 0);
    assert(e <= 62);
    long pow = 1;
    for (int i = 0; i < e; pow *= 2, ++i);
    return pow;
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

  public static boolean xor(final boolean[] a) {
    if (a == null) return false;
    boolean result = false;
    for (int i = 0; i < a.length; ++i)
      if (a[i]) result = !result;
    return result;
  }

  public static int fld(final double x) {
    assert(x > 0);
    if (x == 1) return 0;
    if (x == Double.POSITIVE_INFINITY) return Integer.MAX_VALUE;
    if (x > 1) {
      int result = 0;
      for (double pow = 2; pow <= x; ++result, pow *= 2);
      return result;
    }
    else {
      assert(x < 1);
      int result = -1;
      for (double pow = 1.0/2; pow > x; --result, pow /= 2);
      return result;
    }
  }

  // The remainder of the file is for TESTING.

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

    {
      int[] a = {1,2,3};
      assert(eqarr(fill(1,2,3), a));
    }

    {
      final int T = 100;
      for (int x = -T; x <= T; ++x)
        for (int y = -T; y <= T; ++y)
          for (int z = -T; z <= T; ++z) {
            final int[] a = sort(x,y,z);
            assert(a[0] <= a[1] && a[1] <= a[2]);
            assert(a[0]==x || a[0]==y || a[0]==z);
            assert(a[1]==x || a[1]==y || a[1]==z);
            assert(a[2]==x || a[2]==y || a[2]==z);
            assert(x==a[0] || x==a[1] || x==a[2]);
            assert(y==a[0] || y==a[1] || y==a[2]);
            assert(z==a[0] || z==a[1] || z==a[2]);
          }
    }

    assert(pow2(0) == 1);
    assert(pow2(1) == 2);
    assert(pow2(31)-1 == Integer.MAX_VALUE);
    assert((pow2(62)-1)+pow2(62) == Long.MAX_VALUE);

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

    assert(!xor(null));
    for (int n = 0; n <= 10; ++n)
      assert(!xor(new boolean[n]));
    for (int n = 0; n <= 10; ++n) {
      final boolean[] a = new boolean[n];
      for (int i = 0; i < n; ++i) a[i] = true;
      assert(xor(a) == (n % 2 == 1));
    }
    for (int i = 0; i <= 1; ++i) {
      final boolean x = i==1;
      for (int j = 0; j <= 1; ++j) {
        final boolean y = j==1;
        for (int k = 0; k <= 1; ++k) {
          final boolean z = k==1;
          assert(xor(new boolean[]{x,y,z}) == ((i+j+k) % 2 == 1));
        }
      }
    }

    assert(fld(1) == 0);
    final int im = Integer.MAX_VALUE;
    assert(im == Math.pow(2,31) - 1);
    assert(fld(Double.POSITIVE_INFINITY) == im);
    assert(fld(128) == 7);
    assert(fld(127) == 6);
    assert(fld(Math.pow(2,31)) == 31);
    assert(fld(Math.pow(2,31) + 1) == 31);
    assert(fld(Math.pow(2,31) - 1) == 30);
    assert(fld(im) == 30);
    final long lm = Long.MAX_VALUE;
    assert(lm == Math.pow(2,63) - 1);
    // The following two asserts show that fld computes with the
    // inputs converted to double, which yields a loss of precision:
      assert(lm == Math.pow(2,63));
      assert(fld(lm) == 63); // correct would be 62, IF lm would fit into double
    // The largest integer in double, such that 1 can be subtracted:
    final long lmd = pow2(53);
    assert(fld(lmd) == 53);
    assert(fld(lmd - 1) == 52);
    assert(fld(Double.MAX_VALUE) == 1023);
    assert(fld(Math.pow(2,1024)) == im);
    assert(fld(0.9) == -1);
    assert(fld(0.5) == -1);
    assert(fld(0.499) == -2);
    assert(fld(0.4) == -2);
    assert(fld(0.25) == -2);
    assert(fld(0.24999) == -3);
    assert(fld(Double.MIN_VALUE) == -1074);
    for (int i = -1072; i <= 1023; ++i) {
      final double p = Math.pow(2,i);
      assert(fld(p) == i);
      assert(fld(p * 1.5) == i);
      assert(fld(p * 0.8) == i-1);
    }
  }
}
