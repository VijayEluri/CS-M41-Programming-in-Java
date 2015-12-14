class Cases1 {
  public static void main(final String[] args) {
    final int N = args.length;
    if (N==0) System.out.println("Hello World!");
    else if (N==1) System.out.println("Hello, " + args[0] + "!");
    else if (N==2) System.out.println("Hello, "+args[0]+" and "+args[1]+"!");
    else {
      final int[] A = new int[N];
      for (int i = 0; i < N; ++i) A[i] = Integer.parseInt(args[i]);
      if (N == 3) {
        final long a = A[0], b = A[1], c = A[2];
        System.out.println((a == 0) || (b == 0) || (c == 0) ||
          (a+b == 0) || (a+c == 0) || (b+c == 0) ||
          (a+b+c == 0));
      }
      else if (N == 4) {
        int a = A[0], b = A[1], c = A[2], d = A[3];
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
      }
      else {
        long sum = 0;
        {boolean pos = true;
         for (int i=0; i<N; ++i, pos=!pos)
           if (pos) sum += A[i]; else sum -= A[i];
        }
        if (A[0] % 2 != 0) sum *= -1;
        System.out.println(sum);
      }
    }
  }
}
