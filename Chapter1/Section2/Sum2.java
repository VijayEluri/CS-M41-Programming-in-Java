class Sum2 {
  public static void main(final String[] args) {
    final int N = Integer.parseInt(args[0]);
    long sum = 0;
    for (int i = 0; i < N; ++i) sum += i+1;
    System.out.println(sum);
  }
}
