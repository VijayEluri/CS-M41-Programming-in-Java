class Cases {
  public static void main(final String[] args) {
    final int nargs = args.length;
    if (nargs == 0) {
      System.out.println("Nothing to be done.");
      return;
    }
    if (nargs == 1) {
      System.out.println("Hello, " + args[0] + "!");
      return;
    }
    if (nargs == 2) {
      final int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]);
      if (b == 0) System.out.println("a = " + a);
      else System.out.println(a + " % " + b + " = " + (a % b));
      return;
    }
    if (nargs == 3) {
      final double a = Double.parseDouble(args[0]);
      final double b = Double.parseDouble(args[1]);
      final double c = Double.parseDouble(args[2]);
      if (a <= b)
        if (b <= c) System.out.println(a + " " + b + " " + c);
        else if (c < a) System.out.println(c + " " + a + " " + b);
        else System.out.println(a + " " + c + " " + b);
      else if (a <= c) System.out.println(b + " " + a + " " + c);
      else if (c < b) System.out.println(c + " " + b + " " + a);
      else System.out.println(b + " " + c + " " + a);
      return;
    }
    if (nargs >= 4 && nargs <= 6) {
      final boolean even = nargs % 2 == 0;
      final int maj = (even) ? nargs/2 : nargs/2+1;
      final int[] a = new int[nargs];
      for (int i = 0; i < nargs; ++i) a[i] = Integer.parseInt(args[i]);
      int i = 0; boolean stop = false;
      while (i <= nargs / 2 && ! stop) {
        final int b = a[i];
        int count = 1;
        for (int j = i+1; j < nargs; ++j) if (b == a[j]) ++count;
        if (count >= maj) {
          System.out.println(b);
          if (! even || count > maj) stop = true;
        }
        ++i;
      }
      return;
    }
    {
      final int rem = Integer.parseInt(args[0]);
      if (rem <= 0) {
        long sum = 0;
        for (int i = 1; i < nargs; ++i) sum += Integer.parseInt(args[i]);
        System.out.println(sum);
        return;
      }
      final int[] count = new int[rem];
      for (int i = 1; i < nargs; ++i) {
        int r = Integer.parseInt(args[i]) % rem;
        if (r < 0) r += rem;
        ++count[r];
      }
      for (int i = 0; i < rem; ++i)
        if (count[i] != 0) System.out.println(i + ": " + count[i]);
    }
  }
}
