class Sum1 {
  public static void main(final String[] args) {
    final int N = Integer.parseInt(args[0]);
    long sum = 0;
    for (int i = 1; i <= N; ++i) sum += i;
    System.out.println(sum);
  }
}
