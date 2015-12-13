class Cases {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("Hello World!");
      return;
    }
    if (nargs == 1) {
      System.out.println("Hello, " + args[0] + "!");
      return;
    }
    if (nargs == 2) {
      System.out.println("Hello, " + args[0] + " and " + args[1] + "!");
      return;
    }
    if (nargs == 3) {
      final int[] a = new int[nargs];
      for (int i = 0; i < nargs; ++i) a[i] = Integer.parseInt(args[i]);
      boolean found = false;
      for (int i = 0; i < nargs; ++i) if (a[i] == 0) found = true;
      long sum = 0;
      for (int i = 0; i < nargs; ++i) sum += a[i];
      if (sum == 0) found = true;
      for (int i = 0; i < nargs; ++i) if (sum-a[i] == 0) found = true;
      System.out.println(found);
      return;
    }
    if (nargs == 4) {
      int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]),
        c = Integer.parseInt(args[2]), d = Integer.parseInt(args[3]);
      if (a > b) {final int t=a;a=b;b=t;}
      if (b > c) {
        {final int t=b;b=c;c=t;}
        if (a > b) {final int t=a;a=b;b=t;}
      }
      if (b > d)
        if (a > d) {final int t=d;d=c;c=b;b=a;a=t;}
        else {final int t=d;d=c;c=b;b=t;}
      else if (c > d) {final int t=c;c=d;d=t;}
      System.out.println(a + " " + b + " " + c + " " + d);
      return;
    }
    {
      long sum = 0;
      for (int i = 1, mult=1; i < nargs; ++i, mult*=-1)
        sum += mult * (long)Integer.parseInt(args[i]);
      final int s0 = Integer.parseInt(args[0]);
      if (s0 % 2 == 0) {sum *= -1; sum += s0;} else sum -= s0;
      System.out.println(sum);
    }
  }
}
