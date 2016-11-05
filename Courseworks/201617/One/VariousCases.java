class VariousCases {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println(" Hello");
      System.out.println("   world !!");
      return;
    }
    if (nargs == 1) {
      final int length = args[0].length();
      for (int i = 0; i < length; ++i) {
        for (int j = 0; j < i; ++j) System.out.print(" ");
        System.out.println(args[0]);
      }
      return;
    }
    if (nargs == 2) {
      System.out.println("Argument 1: \"" + args[0] + "\".");
      System.out.println("Argument 2: \"" + args[1] + "\".");
      return;
    }
    if (nargs == 3) {
      final int a = Math.abs(Integer.parseInt(args[0])),
        b = Math.abs(Integer.parseInt(args[1])),
        c = Math.abs(Integer.parseInt(args[2]));
      boolean found = false;
      if (a*b == c) {found = true; System.out.println("1*2=3");}
      if (a*c == b) {found = true; System.out.println("1*3=2");}
      if (b*c == a) {found = true; System.out.println("2*3=1");}
      if (! found) System.out.println("None.");
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
      int size = 1;
      if (b > a) ++size;
      if (c > b) ++size;
      if (d > c) ++size;
      System.out.println(size);
      return;
    }
    {
      final int modifier = Integer.parseInt(args[0]);
      if (modifier < 0) {
        long prod = 1;
        for (int i = 1; i < nargs; ++i)
          prod *= (long) (Integer.parseInt(args[i]));
        System.out.println(prod);
        return;
      }
      long sum = 0;
      if (modifier == 0) {
        for (int i = 1; i < nargs; ++i)
          sum += (long) (Integer.parseInt(args[i]));
        System.out.println(sum);
        return;
      }
      for (int i = 1; i < nargs; ++i)
        if (i % modifier != 0)
          sum += (long) (Integer.parseInt(args[i]));
      System.out.println(sum);
      return;
    }
  }
}
