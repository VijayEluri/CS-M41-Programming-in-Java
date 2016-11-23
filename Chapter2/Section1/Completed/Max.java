public class Max {

  public static int max(final int[] a) {
    int curmax = Integer.MIN_VALUE;
    if (a == null) return curmax;
    for (int i = 0; i < a.length; ++i)
      if (a[i] > curmax) curmax = a[i];
    return curmax;
  }

  public static void main(final String[] args) {
    final int N = args.length;
    final int[] a = new int[N];
    for (int i = 0; i < N; ++i) a[i] = Integer.parseInt(args[i]);
    System.out.println(max(a));
  }
}
