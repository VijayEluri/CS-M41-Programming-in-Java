// Oliver Kullmann, 21.11.2011 (Swansea)

class Rows {

  public static int[][][] list_rows(final int k, final int m, final int n, final int r) {
    assert(k >= 2);
    assert(m >= 1);
    assert(n >= 1);
    assert(k <= m || k <= n);
    assert(r >= 1);
    int[][][] rows = new int[r][][];
    // XXX to be completed XXX
    return rows;
  }

  // performs a formal check on lr, a "list of rows":
  public static boolean check_list_rows(final int[][][] lr, final int[][] field) {
    if (lr == null) return false;
    final int R = lr.length;
    if (R == 0) return false;
    if (lr[0] == null) return false;
    final int K = lr[0].length;
    if (K == 0) return false;
    for (int r = 0; r < R; ++r) {
      if (lr[r] == null) return false;
      if (lr[r].length != K) return false;
      for (int p = 0; p < K; ++p) {
        if (lr[r][p] == null) return false;
        if (lr[r][p].length != 2) return false;
        if (! Field.valid_coordinates(field, lr[r][p][0], lr[r][p][1])) return false;
      }
    }
    return true;
  }


  // for testing:
  public static void main(final String[] args) {
    final int K = Integer.parseInt(args[0]);
    final int M = Integer.parseInt(args[1]);
    final int N = Integer.parseInt(args[2]);
    final int R = Counting.number_rows(K,M,N);
    final int[][][] rows = list_rows(K,M,N,R);
    for (int r = 0; r < R; ++r) {
      System.out.print(r + ": ");
      for (int p = 0; p < K; ++p)
        System.out.print("(" + rows[r][p][0] + "," + rows[r][p][1] + ")");
      System.out.println();
    }
  }

}
