class Vote {
  public static Counter counter_factory(final String n) {
    if (n == null || n.equals("")) return null;
    else return new Counter(n);
  }
  public static long sum_counts(final Counter[] a) {
    if (a == null) return 0;
    long sum = 0;
    for (int i = 0; i < a.length; ++i) sum += a[i].count();
    return sum;
  }
  public static int count_counters(final Counter[] a) {
    if (a == null) return 0;
    int count = 0;
    final int length = a.length;
    for (int i = 1; i < length; ++i) {
      final Counter c = a[i];
      int j;
      for (j = 0; j < i && ! c.equals(a[j]); ++j);
      if (j == i) ++count;
    }
    return count;
  }
  public static String shortest_name(final Counter[] a) {
    if (a == null || a.length == 0) return null;
    int length = a[0].name().length();
    int shortest = 0;
    for (int i = 1; i < a.length; ++i) {
      final int l = a[i].name().length();
      if (l <= length) {
        length = l;
        shortest = i;
      }
    }
    return a[shortest].name();
  }
  
}
