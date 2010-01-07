// Oliver Kullmann, 16.12.2009 (Swansea)

class Product {

  public static void main(String[] args) {
    final int N = Integer.parseInt(args[0]);
    if (N > 20) {
      System.err.println("N must be at most 20.");
      return;
    }
    long prod = 1;
    for (int i = 1; i <= N; ++i) prod *= i;
    System.out.println(prod);
  }
}
