// Oliver Kullmann, 1/12/2011 (Swansea)

class DiagonalSum {
  private static final String err = "ERROR[DiagonalSum]: ";
  public static final int err_param = 1;
  public static final int err_int = 2;
  public static final int err_neg = 3;
  public static final int err_mem = 4;

  private static int[][] create(final int N) {
    assert(N >= 0);
    final int[][] A = new int[N][N];
    for (int i = 0; i < N; ++i)
      for (int j = 0; j < N; ++j)
        A[i][j] = i+j;
    return A;
  }

  private static int sum(final int[][] A) {
    assert(A != null);
    final int N = A.length;
    int sum = 0;
    for (int i = 0; i < N; ++i) {
      assert(A[i] != null);
      assert(A[i].length > i);
      sum += A[i][i];
    }
    return sum;
  }

  public static void main(final String[] args) {
    try {
      final int N = Integer.parseInt(args[0]);
      if (N < 0) {
        System.out.println(err + "N can not be negative.");
        System.exit(err_neg);
      }
      System.out.println(sum(create(N)));
    }
    catch(final ArrayIndexOutOfBoundsException e) {
      System.out.println(err + "At least one parameter is required.");
      System.exit(err_param);
    }
    catch(final NumberFormatException e) {
      System.out.println(err + "The parameter N must be an integer.");
      System.exit(err_int);
    }
    catch(final OutOfMemoryError e) {
      System.out.println(err + "The parameter N is too large.");
      System.exit(err_mem);
    }
  }
}
