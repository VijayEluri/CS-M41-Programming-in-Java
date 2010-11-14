public class Max {

  XXX max(XXX) {
    XXX
  }

  public static void main(final String[] args) {
    final int N = args.length;
    int[] a = new int[N];
    for (int i = 0; i < N; ++i)
      a[i] = Integer.parseInt(args[i]);
    System.out.println(max(a));
  }
}
