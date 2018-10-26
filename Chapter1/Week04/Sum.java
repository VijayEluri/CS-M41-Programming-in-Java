// Oliver Kullmann, 16.12.2009 (Swansea)

class Sum {

  public static void main(final String[] args) {
    final int N = Integer.parseInt(args[0]);
    long sum = 0;
    for (int i = 0; i < N; ++i) sum += i+1;
    System.out.println(sum);
  }
}
