class Functions {

  public static int no_args = 1, wrong_selection = 2, missing_args = 3,
    negative_counts = 4, missing_strings = 5;

  private static int selection(final String a) {
    if (a.equals("1")) return 1;
    if (a.equals("2")) return 2;
    if (a.equals("3")) return 3;
    return 0;
  }
  private static int[] read(final String[] a) {
    assert(a != null);
    final int l = a.length;
    assert(l >= 1);
    final int lb = l-1;
    final int[] b = new int[lb];
    for (int i = 0; i < lb; ++i) b[i] = Integer.parseInt(a[i+1]);
    return b;
  }
  private static void write(final int[] a) {
    assert(a != null);
    for (int i = 0; i < a.length; ++i) System.out.println(a[i]);
  }
  
  public static boolean div(final int[] a) {
    if (a == null) return false;
    final int l = a.length;
    long sum = 0;
    for (int i = 0; i < l; ++i) sum += a[i];
    if (l == 0) return sum == 0;
    return sum % l == 0;
  }

  public static int[] max3(final int[] a) {
    if (a == null) return null;
    final int l = a.length;
    if (l == 0) return a; // harmless, since elements can't be changed
    final int l3 = l/3 + ((l%3 == 0) ? 0 : 1);
    final int[] m = new int[l3];
    for (int i = 0, j = 0; i < l; ++i, j=(i%3==0)?j+1:j)
      if (i%3 == 0 || a[i]>m[j]) m[j] = a[i];
    return m;
  }
  
  public static void comp(final String[] a, final String[] b, final String[] x) {
    if (a == null || b == null || x == null) return;
    final int la = a.length, lb = b.length, lx = x.length;
    X count1 = (la>0)?new X(la):null, count2 = (lb>0)?new X(lb):null;
    for (int i = 0; i < lx; ++i) {
      final String s = x[i];
      for (int j = 0; j < la; ++j) if (s.equals(a[j])) count1.inc(j);
      for (int j = 0; j < lb; ++j) if (s.equals(b[j])) count2.inc(j);
    }
    if (count1!=null) System.out.println(count1);
    if (count2!=null) System.out.println(count2);
    if (count1!=null && count2!=null) System.out.println(count1.equals(count2));
    else if (count1 == count2) System.out.println(true);
    else if (count1 != null) System.out.println(count1.sum() == 0);
    else System.out.println(count2.sum() == 0);
  }
  
  public static void main(final String[] args) {
    final int l = args.length;
    if (l == 0) System.exit(no_args);
    final int sel = selection(args[0]);
    if (sel == 0) System.exit(wrong_selection);
    
    if (sel == 1) {
      final int[] a = read(args);
      System.out.println(div(a));
      return;
    }
    if (sel == 2) {
      final int[] a = read(args);
      write(max3(a));
      return;
    }
    if (sel == 3) {
      if (l <= 2) System.exit(missing_args);
      final int m = Integer.parseInt(args[1]);
      final int n = Integer.parseInt(args[2]);
      if (m < 0 || n < 0) System.exit(negative_counts);
      if (l < 3+m+n) System.exit(missing_strings);
      final String[] a = new String[m];
      for (int i = 0; i < m; ++i) a[i] = args[3+i];
      final String[] b = new String[n];
      for (int i = 0; i < n; ++i) b[i] = args[3+m+i];
      final int lx = l - (3+m+n);
      final String[] x = new String[lx];
      for (int i = 0; i < lx; ++i) x[i] = args[3+m+n+i];
      comp(a,b,x);
      return;
    }
  }
  
}
