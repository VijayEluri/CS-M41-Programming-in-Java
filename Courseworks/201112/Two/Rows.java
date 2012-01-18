// Oliver Kullmann, 21.11.2011 (Swansea)

/* Provides functions

   - list_rows(K,M,N,R) for computing the R "rows" ("lines") of length K
     in an M x N rectangle (field); R must be the correct number.
     A "row" (of length K) is given by K-many pairs of coordinates (internal
     format).
   - check_list_rows(rows, field) checks the computed list of rows against
     a valid field (whether rows is fully initialised, and is an array of
     arrays A of the same length, where A is an array of two-element
     arrays C, and where C finally is a pair of valid coordinates (internal)
     for field).

     Running main with parameters (3,3,3) yields the 8 "rows" of the
     standard tic-tac-toe board:

0: (0,0)(0,1)(0,2)
1: (1,0)(1,1)(1,2)
2: (2,0)(2,1)(2,2)
3: (0,0)(1,0)(2,0)
4: (0,1)(1,1)(2,1)
5: (0,2)(1,2)(2,2)
6: (0,0)(1,1)(2,2)
7: (2,0)(1,1)(0,2)

     (first three horizontal lines, then three vertical lines, then the
     prinzipal diagonal, then the antidiagonal).

     Running main with parameters (3,3,4) yields 14 "rows":

0: (0,0)(0,1)(0,2)
1: (0,1)(0,2)(0,3)
2: (1,0)(1,1)(1,2)
3: (1,1)(1,2)(1,3)
4: (2,0)(2,1)(2,2)
5: (2,1)(2,2)(2,3)
6: (0,0)(1,0)(2,0)
7: (0,1)(1,1)(2,1)
8: (0,2)(1,2)(2,2)
9: (0,3)(1,3)(2,3)
10: (0,0)(1,1)(2,2)
11: (0,1)(1,2)(2,3)
12: (2,0)(1,1)(0,2)
13: (2,1)(1,2)(0,3)

     (first 6 horizontal rows, then 4 vertical rows, then 2 diagonals
     top-left to bottom-right, then 2 diagonals bottom-left to top-right).
*/

class Rows {

  // computes an array "rows" of length r, each element an array "line" of
  // length k, whose elements are arrays of length 2: array line represents a
  // "row" of an m x n field, and array rows collects precisely all these
  // lines; for efficient array-allocation (upfront) the precise number r of
  // rows must also be given:
  public static int[][][] list_rows(final int k, final int m, final int n, final int r) {
    assert(k >= 2);
    assert(m >= 1);
    assert(n >= 1);
    assert(k <= m || k <= n);
    assert(r >= 1);
    int[][][] rows = new int[r][][];
    int row_index = 0;
    // horizontal rows:
    for (int i = 0; i < m; ++i)
      for (int j = 0; j <= n-k; ++j, ++row_index) {
        rows[row_index] = new int[k][2];
        for (int p = 0; p < k; ++p) {
          rows[row_index][p][0] = i;
          rows[row_index][p][1] = j+p;
        }
      }
    // vertical rows:
    for (int j = 0; j < n; ++j)
      for (int i = 0; i <= m-k; ++i, ++row_index) {
        rows[row_index] = new int[k][2];
        for (int p = 0; p < k; ++p) {
          rows[row_index][p][0] = i+p;
          rows[row_index][p][1] = j;
        }
      }
    // diagonal rows, top-left to bottom-right:
    for (int i = 0; i < m+n-1; ++i) {
      final int tl_i = Math.max(m-1-i,0);
      final int tl_j = Math.max(i-m+1,0);
      final int br_i = m-1-Math.max(i-n+1,0);
      final int br_j = Math.min(i,n-1);
      final int length = br_i - tl_i + 1;
      assert(length == br_j - tl_j + 1);
      for (int d = 0; d <= length-k; ++d, ++row_index) {
        rows[row_index] = new int[k][2];
        for (int p = 0; p < k; ++p) {
          rows[row_index][p][0] = tl_i + d + p;
          rows[row_index][p][1] = tl_j + d + p;
        }
      }
    }
    // diagonal rows, bottom-left to top-right:
    for (int i = 0; i < m+n-1; ++i) {
      final int bl_i = Math.min(i,m-1);
      final int bl_j = Math.max(i-m+1,0);
      final int tr_i = Math.max(i-n+1,0);
      final int tr_j = Math.min(i,n-1);
      final int length = bl_i - tr_i + 1;
      assert(length == tr_j - bl_j + 1);
      for (int d = 0; d <= length-k; ++d, ++row_index) {
        rows[row_index] = new int[k][2];
        for (int p = 0; p < k; ++p) {
          rows[row_index][p][0] = bl_i - d - p;
          rows[row_index][p][1] = bl_j + d + p;
        }
      }
    }
    assert(row_index == r);
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
        if (! Field.valid_coordinates(field, lr[r][p][0]+1, lr[r][p][1]+1)) return false;
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
