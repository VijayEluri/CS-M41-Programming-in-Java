class SeveralCases {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("123456");
      return;
    }
    if (nargs == 1) {
      final int length = args[0].length();
      System.out.print("\"");
      for (int i = 0; i < length; ++i)
        System.out.print(args[0]);
      System.out.println("\"");
      return;
    }
    if (nargs == 2) {
      final int x = Integer.parseInt(args[0]);
      final int y = Integer.parseInt(args[1]);
      if (x < 0 || y < 0)
        if (x < y) System.out.println(x);
        else System.out.println(y);
      else if (x == 0)
        System.out.println(y);
      else if (y == 0)
        System.out.println(x);
      else {
        final int div = x / y;
        final int mod = x % y;
        System.out.println(x + "=" + y + "*" + div + "+" + mod);
      }
      return;
    }
    if (nargs == 3) {
      final long a = Integer.parseInt(args[0]),
        b = Integer.parseInt(args[1]),
        c = Integer.parseInt(args[2]);
      System.out.println(a*b+c);
      return;
    }
     if (nargs == 4) {
      final long[] a = new long[nargs];
      for (int i = 0; i < nargs; ++i)
        a[i] = Integer.parseInt(args[i]);
      long max = 0;
      for (int i = 0; i < nargs-1; ++i) {
        final long x = a[i];
        for (int j = i+1; j < nargs; ++j) {
          final long dist = Math.abs(x - a[j]);
          if (dist > max) max = dist;
        }
      }
      System.out.println(max);
      return;
    }
    {
      final long[] a = new long[nargs];
      for (int i = 0; i < nargs; ++i)
        a[i] = Integer.parseInt(args[i]);
      long sum = 0;
      for (int i = 0; i < nargs; ++i) sum += a[i];
      long sum_l = 0, sum_r = sum;
      for (int i = 0; i < nargs; ++i) {
        sum_l += a[i];
        sum_r -= a[i];
        if (sum_l == sum_r) {
          System.out.println(true);
          return;
        }
      }
      System.out.println(false);
      return;
    }
  }
}
