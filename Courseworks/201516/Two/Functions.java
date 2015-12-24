class Functions {

  public final static int error_no_args = 1;

  public static String concat(final String[] a) {
    if (a == null) return "";
    String res = new String();
    for (int i = 0; i < a.length; ++i) res += a[i];
    return res;
  }
  
  public static long sum(final int[] a) {
    long res = 0;
    if (a == null) return res;
    for (int i = 0; i < a.length; ++i) res += a[i];
    return res;
  }
  
  public static double max(final double[] a) {
    double res = Double.NEGATIVE_INFINITY;
    if (a == null) return res;
    for (int i = 0; i < a.length; ++i)
      if (a[i] > res) res = a[i];
    return res;
  }
  
  public static double min(final double[] a) {
    double res = Double.POSITIVE_INFINITY;
    if (a == null) return res;
    for (int i = 0; i < a.length; ++i)
      if (a[i] < res) res = a[i];
    return res;
  }
  
  private static int[] extract_int(final String[] a) {
    if (a == null) return null;
    int count = 0;
    for (int i = 0; i < a.length; ++i) {
      final X x = new X(a[i]);
      if (x.to_int()) ++count;
    }
    final int[] res = new int[count];
    for (int i = 0, j = 0; j < count; ++i) {
      final X x = new X(a[i]);
      if (x.to_int()) res[j++] = x.i();
    }
    return res;
  }
  
  private static double[] extract_double(final String[] a) {
    if (a == null) return null;
    int count = 0;
    for (int i = 0; i < a.length; ++i) {
      final X x = new X(a[i]);
      if (x.to_double()) ++count;
    }
    final double[] res = new double[count];
    for (int i = 0, j = 0; j < count; ++i) {
      final X x = new X(a[i]);
      if (x.to_double()) res[j++] = x.d();
    }
    return res;
  }

  public static void main(final String[] args) {
    final int N = args.length;
    if (N == 0) System.exit(error_no_args);
    final X x = new X(args[0]);
    if (x.to_boolean()) {
      final boolean b = x.b();
      if (b) {
        final String[] s = new String[N-1];
        for (int i = 0; i < s.length; ++i) s[i] = args[i+1];
        System.out.println("\"" + concat(s) + "\"");
        return;
      }
      else {
        final String[] s = new String[N/2];
        for (int i = 0; i < s.length; ++i) s[i] = args[2*i+1];
        System.out.println("\"" + concat(s) + "\"");
        return;
      }
    }
    else if (x.to_int()) {
      final int n = x.i();
      System.out.println(sum(extract_int(args)) - n);
      return;
    }
    else if (x.to_double()) {
      final double d = x.d();
      if (d >= 0) System.out.println(max(extract_double(args)));
      else System.out.println(min(extract_double(args)));
    }
    else if (args[0].equals("special")) {
      final int[] l = new int[N];
      for (int i = 0; i < N; ++i) l[i] = args[i].length();
      System.out.println(sum(l));
    }
    else System.out.println(concat(args));
  }
}
