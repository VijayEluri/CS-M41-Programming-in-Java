class Cases2 {

  // Library functions:

  public static void swap(final double[] a, final int i, final int j) {
    assert(a != null);
    assert(i >= 0);
    assert(i < a.length);
    assert(j >= 0);
    assert(j < a.length);
    final double t = a[i];
    a[i] = a[j];
    a[j] = t;
  }
  
  // In-place sorting of a, having at most 3 elements:
  public static void sort3(final double[] a) {
    if (a == null) return;
    final int l = a.length;
    assert(l <= 3);
    if (l <= 1) return;
    if (a[0] > a[1]) swap(a,0,1);
    if (l == 2 || a[1] <= a[2]) return;
    swap(a,1,2);
    if (a[0] > a[1]) swap(a,0,1);
    return;
  }
  
  public static int[] read_int(final String[] args, final int begin) {
    final int l = args.length;
    if (args == null || begin > l) return null;
    final int l2 = l - begin;
    final int[] a = new int[l2];
    for (int i = 0; i < l2; ++i) a[i] = Integer.parseInt(args[begin+i]);
    return a;
  }
  public static double[] read_double(final String[] args, final int begin) {
    final int l = args.length;
    if (args == null || begin > l) return null;
    final int l2 = l - begin;
    final double[] a = new double[l2];
    for (int i = 0; i < l2; ++i) a[i] = Double.parseDouble(args[begin+i]);
    return a;
  }
  
  public static void output(final int[] a) {
    if (a == null) return;
    for (int i = 0; i < a.length; ++i) System.out.println(a[i]);
  }
  public static void output(final double[] a) {
    if (a == null) return;
    final int l = a.length;
    if (l == 0) return;
    for (int i = 0; i < l-1; ++i) System.out.print(a[i] + " ");
    System.out.println(a[l-1]);
  }
  
  // Returns a 2-dimensional array ec, where ec[0] contains the different elements of a,
  // in their order of occurrences, while ec[1] contains their counts:
  public static int[][] elements_counts(final int[] a) {
    if (a == null) return null;
    final int l = a.length;
    final int[][] res = new int[2][l];
    if (l == 0) return res;
    int l2 = 0;
    final int[] elements = res[0], counts = res[1];
    for (int i = 0; i < l; ++i) {
      final int x = a[i];
      boolean found = false;
      for (int j = 0; !found && j < l2; ++j)
        if (x == elements[j]) {
          found = true;
          ++counts[j];
        }
      if (!found) {
        elements[l2] = x;
        counts[l2++] = 1;
      }
    }
    if (l2 == l) return res;
    final int[][] res_ = new int[2][l2];
    for (int i = 0; i < l2; ++i) {
      res_[0][i] = res[0][i];
      res_[1][i] = res[1][i];
    }
    return res_;
  }
  
  // Returns an array of size at most 2, containing the majority-elements of a, in
  // the order of occurrences:
  public static int[] majority(final int[] a) {
    if (a == null) return null;
    final int l = a.length;
    final boolean even = l % 2 == 0;
    final int maj = (even) ? l/2 : l/2+1;
    final int[] m = new int[2];
    int lm = 0;
    final int[][] ec = elements_counts(a);
    final int[] elements = ec[0], counts = ec[1];
    {
     final int le = elements.length;
     boolean stop = false;
     for (int i = 0; !stop && i < le; ++i) {
       if (counts[i] >= maj) {
         m[lm++] = elements[i];
         if (! even || counts[i] > maj) stop = true;
       }
     }
    }
    if (lm == 2) return m;
    final int[] m_ = new int[lm];
    for (int i = 0; i < lm; ++i) m_[i] = m[i];
    return m_;
  }

  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("Nothing to be done.");
      return;
    }
    if (nargs == 1) {
      System.out.println("Hello, " + args[0] + "!");
      return;
    }
    if (nargs == 2) {
      final int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]);
      if (b == 0) System.out.println("a = " + a);
      else System.out.println(a + " % " + b + " = " + (a % b));
      return;
    }
    if (nargs == 3) {
      final double[] a = read_double(args,0);
      sort3(a);
      output(a);
      return;
    }
    if (nargs >= 4 && nargs <= 6) {
      output(majority(read_int(args, 0)));
      return;
    }
    {
      final int rem = Integer.parseInt(args[0]);
      if (rem <= 0) {
        long sum = 0;
        for (int i = 1; i < nargs; ++i) sum += Integer.parseInt(args[i]);
        System.out.println(sum);
        return;
      }
      final int[] a = read_int(args,1);
      final int l = a.length;
      for (int i = 0; i < l; ++i) {
        a[i] %= rem;
        if (a[i] < 0) a[i] += rem;
      }
      final int[][] ec = elements_counts(a);
      final int[] e = ec[0], c = ec[1];
      final int le = e.length;
      for (int i = 0; i < rem; ++i) {
        boolean found = false;
        for (int j = 0; !found && j < le; ++j)
          if (e[j] == i) {
           found = true;
           System.out.println(i + ": " + c[j]);
          }
      }
    }
  }
}
