class SeveralCases_4 {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("123456");
      return;
    }
    if (nargs == 1) {
      final String s = args[0];
      final int length = s.length();
      System.out.print("\"");
      for (int i = 0; i < length; ++i) System.out.print(s);
      System.out.println("\"");
      return;
    }
    if (nargs == 2) {
      final int x = Integer.parseInt(args[0]);
      final int y = Integer.parseInt(args[1]);
      if (x < 0 || y < 0)
        if (x < y) System.out.println(x);
        else System.out.println(y);
      else if (x == 0) System.out.println(y);
      else if (y == 0) System.out.println(x);
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
      int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
      for (int i = 0; i < nargs; ++i) {
        final int x = Integer.parseInt(args[i]);
        if (x < min) min = x;
        if (x > max) max = x;
      }
      System.out.println((long) max - min);
      return;
    }
    {
      final int[] a = new int[nargs];
      for (int i = 0; i < nargs; ++i) a[i] = Integer.parseInt(args[i]);
      long sum_r = 0;
      for (int i = 0; i < nargs; ++i) sum_r += a[i];
      long sum_l = 0;
      for (int i = 0; i < nargs; sum_l += a[i], sum_r -= a[i++])
        if (sum_l == sum_r) {System.out.println(true); return;}
      System.out.println(false);
      return;
    }
  }
}
