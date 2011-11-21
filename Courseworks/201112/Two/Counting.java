// Oliver Kullmann, 20.11.2011 (Swansea)

class Counting {

  public static int number_rows(final int k, final int m, final int n) {
    assert(k >= 1);
    assert(m >= 1);
    assert(n >= 1);
    if (k > m && k > n) return 0;
    if (k > m) return m*(n-k+1);
    if (k > n) return n*(m-k+1);
    if (k == 1) return m*n;
    return num_vert_horiz(k,m,n) + num_diag(k,m,n);
  }

  // number of vertical and horizonal lines:
  private static int num_vert_horiz(final int k, final int m, final int n) {
    assert(k >= 2);
    assert(k <= m);
    assert(k <= n);
    return 2*m*n - (m+n)*(k-1);
  }
  // number of diagonal lines:
  private static int num_diag(final int k, final int m, final int n) {
    assert(k >= 2);
    assert(k <= m);
    assert(k <= n);
    int sum=0;
    int length=1; // length of diagonal considered
    for (int i = 1; i <= (m+n-2)/2; ++i) {
      if (length < m) ++length;
      if (length >= k) {
        final int factor = (2*i == m+n-2) ? 1 : 2;
        sum += factor * 2 * (length - k + 1);
      }
    }
    return sum;
    // factor is there to avoid counting a middling element twice in the
    // upward and downward sector.
  }


  // for testing:
  public static void main(final String[] args) {
    final int k = Integer.parseInt(args[0]);
    final int m = Integer.parseInt(args[1]);
    final int n = Integer.parseInt(args[2]);
    System.out.println(number_rows(k,m,n));
  }
}
