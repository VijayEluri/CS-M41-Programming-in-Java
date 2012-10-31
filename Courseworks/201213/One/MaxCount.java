public class MaxCount {
  public static void main(final String[] args) {
    final int N = args.length;
    final int[] a = new int[N];
    for (int i = 0; i < N; ++i) a[i] = Integer.parseInt(args[i]);
    int max_count = 0;
    for (int i = 0; i < N; ++i) {
      final int val = a[i];
      int count = 1;
      for (int j = i+1; j < N; ++j) if (a[j] == val) ++count;
      if (count > max_count) max_count = count;
    }
    System.out.println(max_count);
  }
}
