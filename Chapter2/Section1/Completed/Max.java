public class Max {

  public static int max(final int[] a) {
    int min = Integer.MIN_VALUE;
    for (int i = 0; i < a.length; ++i)
      if (a[i] > min)
        min = a[i];
    return min;
  }

  public static void main(final String[] args) {
    final int N = args.length;
    int[] a = new int[N];
    for (int i = 0; i < N; ++i)
      a[i] = Integer.parseInt(args[i]);
    System.out.println(max(a));
  }
}
