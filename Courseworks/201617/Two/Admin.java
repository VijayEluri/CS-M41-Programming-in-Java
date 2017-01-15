class Admin {
  
  public static boolean change_income(final Person p, final int change) {
    if (change >= 0) return p.increase(change);
    else {
      if (change == Integer.MIN_VALUE) p.decrease(Integer.MAX_VALUE);
      else p.decrease(-change);
      return true;
    }
  }
  
  public static boolean equal_persons(final Person[] a) {
    if (a == null || a.length <= 1) return false;
    for (int i = 0; i < a.length-1; ++i)
      if (a[i] != null)
        for (int j = i+1; j < a.length; ++j)
          if (a[i].equals(a[j])) return true;
    return false;
  }
  
  public static long sum_income(final Person[] a) {
    if (a == null) return 0;
    long sum = 0;
    for (int i = 0; i < a.length; ++i)
      if (a[i] != null) sum += a[i].income();
    return sum;
  }
  
  public static String longest_name(final Person[] a) {
    if (a == null) return null;
    if (a.length == 0) return null;
    String l = a[0].name;
    int ll = l.length();
    for (int i = 1; i < a.length; ++i)
      if (a[i].name.length() > ll) {
        l = a[i].name;
        ll = l.length();
      }
    return l;
  }
  
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println(Person.message);
      return;
    }
    if (nargs == 1) {
      final Person p = new Person(args[0]);
      System.out.println(p);
      return;
    }
    if (nargs == 2) {
      final Person p = new Person(args[0], Integer.parseInt(args[1]));
      System.out.println(p);
      return;
    }
    if (nargs == 3) {
      final Person p = new Person(args[0], Integer.parseInt(args[1]));
      final int change = Integer.parseInt(args[2]);
      final boolean success = change_income(p, change);
      if (! success) System.err.println("Failure.");
      else System.out.println(p);
      return;
    }
    {
      assert(nargs >= 4);
      final boolean even = (nargs % 2 == 0);
      final int length = (even) ? nargs/2 : nargs/2+1;
      final Person[] a = new Person[length];
      for (int i = 0; i < nargs-1; i+=2) {
        final String name = args[i];
        final int inc = Integer.parseInt(args[i+1]);
        a[i/2] = new Person(name, inc);
      }
      if (! even) a[length-1] = new Person(args[args.length-1]);
      System.out.println(equal_persons(a));
      System.out.println(sum_income(a));
      System.out.println(longest_name(a));
      return;
    }
  }
}