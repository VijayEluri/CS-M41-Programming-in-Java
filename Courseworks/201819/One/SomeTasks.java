class SomeTasks {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("123456");
      return;
    }
    if (nargs == 1) {
      final int N = Integer.parseInt(args[0]);
      long sum = 0;
      for (int i = 0; i < N; ++i) {
        final long j = i+1;
        final long sq = j*j;
        final long new_sum = sum + sq;
        if (new_sum < sum) {
          System.out.println("Overflow at i = " + i);
          System.exit(1);
        }
        sum = new_sum;
      }
      System.out.println(sum);
      return;
    }
    if (nargs == 2) {
      final int x = Integer.parseInt(args[0]);
      final int y = Integer.parseInt(args[1]);
      if (y > 0) System.out.println(x/y);
      else if (x > 0) System.out.println(y/x);
      else {
        final long nx = x, ny = y;
        System.out.println((-nx)+(-ny));
      }
      return;
    }
     if (nargs == 3) {
      final int b = Integer.parseInt(args[0]),
                e = Integer.parseInt(args[1]);
      final boolean duplicate = Boolean.parseBoolean(args[2]);
      if (e < b) return;
      for (int i = b; i < e; ++i) System.out.print(i + " ");
      System.out.print(e);
      if (duplicate) System.out.println(" " + e);
      else System.out.println();
      return;
    }
    if (nargs == 4) {
      int minl = args[0].length();
      for (int i = 1; i < nargs; ++i) {
        final int l = args[i].length();
        if (l < minl) minl = l;
      }
      for (int i = 0; i < nargs; ++i)
        if (args[i].length() == minl)
          System.out.println("\"" + args[i] + "\"");
      return;
    }
    {
      final boolean[] found = new boolean[nargs];
      for (int i = 0; i < nargs; ++i) {
        final int x = Integer.parseInt(args[i]);
        if (x < 1 || x > nargs || found[x-1]) {
          System.out.println(false);
          return;
        }
        else found[x-1] = true;
      }
      System.out.println(true);
    }
  }
}
