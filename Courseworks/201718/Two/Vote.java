class Vote {
  public static Counter counter_factory(final String n) {
    if (n == null || n.equals("")) return null;
    else return new Counter(n);
  }
  public static long sum_counts(final Counter[] a) {
    if (a == null) return 0;
    long sum = 0;
    for (int i = 0; i < a.length; ++i)
      if (a[i] != null) sum += a[i].count();
    return sum;
  }
  public static int count_counters(final Counter[] a) {
    if (a == null) return 0;
    int count = 0;
    final int length = a.length;
    if (length == 0) return 0;
    if (a[0] != null) ++count;
    for (int i = 1; i < length; ++i) {
      final Counter c = a[i];
      if (c != null) {
        int j;
        for (j = 0; j < i && ! c.equals(a[j]); ++j);
        if (j == i) ++count;
      }
    }
    return count;
  }
  public static String shortest_name(final Counter[] a) {
    if (a == null) return null;
    final int length = a.length;
    int index_shortest;
    for (index_shortest = 0;
         index_shortest < a.length && a[index_shortest] == null;
         ++index_shortest);
    if (index_shortest == length) return null;
    int current_length = a[index_shortest].name().length();
    for (int i = index_shortest+1; i < length; ++i) {
      final Counter c = a[i];
      if (c != null) {
        final int l = c.name().length();
        if (l <= current_length) {
          current_length = l;
          index_shortest = i;
        }
      }
    }
    return a[index_shortest].name();
  }
  
  private static boolean increment(final Counter c) {
    if (c == null) return true;
    final int l = c.name().length();
    for (int i = 0; i < l; ++i)
      if (! c.inc()) return false;
    return true;
  }

  public static void main(final String[] args) {
    final int N = args.length;
    if (N == 0) {
      System.out.println(Counter.bound);
      return;
    }
    else if (N == 1) {
      final String name = args[0];
      final Counter c = counter_factory(name);
      if (c == null) System.out.println("ERROR");
      else {
        if (! increment(c)) System.out.println("ERROR");
        else System.out.println(c);
      }
      return;
    }
    else {
      Counter[] a = new Counter[N];
      for (int i = 0; i < N; ++i) a[i] = counter_factory(args[i]);
      for (int i = 0; i < N; ++i)
        if (! increment(a[i])) a[i] = null;
      System.out.println(sum_counts(a));
      System.out.println(count_counters(a));
      System.out.println(shortest_name(a));
      return;
    }
  }
}
