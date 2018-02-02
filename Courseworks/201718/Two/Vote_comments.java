class Vote {
  // For a valid name n, returns a Counter-object, otherwise returns null:
  public static Counter counter_factory(final String n) {
    // Note the order of the tests (remember: evaluation is from left to
    // right!):
    if (n == null || n.equals("")) return null;
    else return new Counter(n);
  }
  // Sums up the counts of all objects:
  public static long sum_counts(final Counter[] a) {
    // Only for a != null can a.length be used:
    if (a == null) return 0;
    // long is enough to precisely carry out the summation of
    // 2^31 many ints, since 2^31 * 2^31 = 2^62 < 2^63 :
    long sum = 0;
    for (int i = 0; i < a.length; ++i)
      // Only for a[i] != null can the method count be called:
      if (a[i] != null) sum += a[i].count();
    return sum;
  }
  // Determines how many different (not-equal) Counter-objects are in a:
  public static int count_counters(final Counter[] a) {
    // As usual, first handle the case there is no array:
    if (a == null) return 0;
    int count = 0;
    // Compares object a[i] with all previous objects a[j]:
    for (int i = 0; i < a.length; ++i) {
      final Counter c = a[i];
      if (c != null) {
        int j;
        // Search for an object equal to c:
        for (j = 0; j < i && ! c.equals(a[j]); ++j);
        // We note here that the equals-method of Counter can handle null.
        // No object found equal to c iff j == i:
        if (j == i) ++count;
      }
    }
    return count;
  }
  // If there is at least one Counter-object in array a, return the
  // shortest name of it (last found), otherwise return null:
  public static String shortest_name(final Counter[] a) {
    if (a == null) return null;
    final int size = a.length;
    int index_shortest;
    // First search for an object:
    for (index_shortest = 0;
         index_shortest < a.length && a[index_shortest] == null;
         ++index_shortest);
    // All a[i] are equal null iff index_shortest == length:
    if (index_shortest == size) return null;
    int current_length = a[index_shortest].name().length();
    for (int i = index_shortest+1; i < size; ++i) {
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
    // Remark: In many situations, for functions like this one it would
    // be better to return the index (i.e., just return index_shortest):
    // Given the index, you can easily determine the string, but the other
    // way around is expensive.
    // Since indices in Java are int's (different from other languages),
    // the return-value -1 (instead of null) could then be used to indicate
    // that there isn't an object there at all.
  }
  // More compactly (renamed):
  public static String shortest_name_2(final Counter[] a) {
    if (a == null) return null;
    int min_length = Integer.MAX_VALUE;
    String shortest = null;
    for (int i = 0; i < a.length; ++i)
      if (a[i] != null) {
        final String name = a[i].name();
        if (name.length() <= min_length) {
          min_length = name.length();
          shortest = name;
        }
      }
    return shortest;
  }
  
  // Returns true iff incrementation by as many characters as are in the
  // name didn't fail (now just an implementation detail, and thus
  // private); as a side effect, c is incremented as often as needed and
  // possible:
  private static boolean increment(final Counter c) {
    if (c == null) return true;
    final int l = c.name().length();
    for (int i = 0; i < l; ++i)
      // Note that we MUST delegate the work to method inc of object c:
      if (! c.inc()) return false;
    return true;
  }

  public static void main(final String[] args) {
    final int N = args.length;
    if (N == 0) {
      // Again, it is ESSENTIAL to use the facilities of class Counter:
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
