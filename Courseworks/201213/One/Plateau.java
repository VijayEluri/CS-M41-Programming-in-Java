public class Plateau {
  public static void main(final String[] args) {
    final int N = args.length;
    final int[] a = new int[N];
    for (int i = 0; i < N; ++i) a[i] = Integer.parseInt(args[i]);
    int max_plateau = 0;
    for (int i = 0, j; i < N; i = j) {
      final int val = a[i];
      for (j = i+1; j < N && a[j] == val; ++j);
      final int length = j - i;
      if ((i==0 || a[i-1]<val) && (j==N || a[j]<val) && length>max_plateau)
        max_plateau = length;
    }
    System.out.println(max_plateau);
  }
}
