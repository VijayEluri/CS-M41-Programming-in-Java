public class Duplicate {
  public static void main(final String[] args) {
    final int N = args.length;
    final int[] a = new int[N];
    for (int i = 0; i < N; ++i) a[i] = Integer.parseInt(args[i]);
    boolean found = false;
    for (int i = 0; i < N-1 && ! found; ++i)
      for (int j = i+1; j < N && ! found; ++j)
        found = a[i] == a[j];
    System.out.println(found);
  }
}
