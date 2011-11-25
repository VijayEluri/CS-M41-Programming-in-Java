// Oliver Kullmann 24.11.2011 (Swansea)

class PrintArray {
  public static void print_array(final int[][] a) {
    if (a == null) {
      System.out.println("[[null]]");
      return;
    }
    final int M = a.length;
    for (int i = 0; i < M; ++i) {
      if (a[i] == null) System.out.println("[null]");
      else {
        final int N = a[i].length;
        for (int j = 0; j < N; ++j) System.out.print(a[i][j] + " ");
        System.out.println();
      }
    }
  }

  public static void main(final String[] args) {
    int[][] a = null;
    System.out.println("No allocation:");
    print_array(a);
    System.out.println("Now the empty matrix:");
    a = new int[0][];
    print_array(a);
    System.out.println("This is also the empty matrix:");
    a = new int[0][0];
    print_array(a);
    System.out.println("3 x 3 array:");
    a = new int[3][3];
    print_array(a);
    System.out.println("Now an array with 4 rows, the second is non-initialised, the third empty:");
    a = new int[4][];
    a[0] = new int[7];
    for (int i = 0; i < 7; ++i) a[0][i] = i;
    a[2] = new int[0];
    a[3] = new int[1];
    a[3][0] = 777;
    print_array(a);
  }
}
