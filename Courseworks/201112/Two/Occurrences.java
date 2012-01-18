// Oliver Kullmann, 21.11.2011 (Swansea)

/* Provides functions

   - occurrences_field(rows, field) for computing the field of occurrences
     of the corresponding cell in the rows-array;
   - consistency_check(rows, occurrences) for checking whether occurrences
     has been properly initialised, and has in total the number of occurrences
     as given by rows;
   - show_occurrence_counts(occurrences) for printing out the occurrence-counts
     in matrix form.

   Running main with parameters (3,3,3) yields the following occurrences of
   cells (i,j) in rows (see Rows.java for the output of the "rows"):

0,0: 0 3 6
0,1: 0 4
0,2: 0 5 7
1,0: 1 3
1,1: 1 4 6 7
1,2: 1 5
2,0: 2 3 7
2,1: 2 4
2,2: 2 5 6

  3  2  3
  2  4  2
  3  2  3

   The matrix shows the occurrence-counts per cell. For example the occurrences
   of cell (0,0) mean that cell (0,0) is element of three rows, and these
   have indices 0, 3, 6 (in the array rows).

   Note that the sum over all matrix elements is 24, which is 3 * 8, where
   3 = K and 8 = R (this holds in general).
*/

class Occurrences {

  // computing the 2-dimensional array "occurrences", each entry an array
  // of indices, namely the indices of rows which include the respective cell:
  public static int[][][] occurrences_field(final int[][][] rows, final int[][] field) {
    assert(Field.valid_field(field));
    assert(Rows.check_list_rows(rows, field));
    final int M = field.length;
    final int N = field[0].length;
    final int R = rows.length;
    int[][][] occ = new int[M][N][R];
    int[][] curr_index = new int[M][N];
    for (int r = 0; r < R; ++r)
      for (int p = 0; p < rows[r].length; ++p) {
        final int i = rows[r][p][0];
        final int j = rows[r][p][1];
        occ[i][j][ curr_index[i][j]++ ] = r;
      }
    // resizing the occurrence-arrays to the actual lengths:
    for (int i = 0; i < M; ++i)
      for (int j = 0; j < N; ++j) {
        final int length = curr_index[i][j];
        int[] copy = new int[length];
        for (int l = 0; l < length; ++l) copy[l] = occ[i][j][l];
        occ[i][j] = copy;
      }
    return occ;
  }

  // a consistency check, comparing rows and occurrences:
  public static boolean consistency_check(final int[][][] rows, final int[][][] occurrences) {
    if (occurrences == null) return false;
    final int M = occurrences.length;
    if (M == 0) return false;
    final int N = occurrences[0].length;
    if (N == 0) return false;
    int sum = 0;
    for (int i = 0; i < M; ++i)
      for (int j = 0; j < N; ++j)
        sum += occurrences[i][j].length;
    assert(rows != null);
    final int R = rows.length;
    assert(R != 0);
    final int K = rows[0].length;
    return(sum == R*K);
  }

  public static void show_occurrence_counts(final int[][][] occurrences) {
    assert(occurrences != null);
    final int M = occurrences.length;
    assert(M != 0);
    final int N = occurrences[0].length;
    assert(N != 0);
    final int spaces = (M*N <= 99) ? 3 : 4;
    for (int i = 0; i < M; ++i) {
      for (int j = 0; j < N; ++j) {
        assert(occurrences[i][j] != null);
        final int count = occurrences[i][j].length;
        StdOut.printf("%" + spaces + "d", count);
      }
      System.out.println();
    }
  }


  // for testing:
  public static void main(final String[] args) {
    final int K = Integer.parseInt(args[0]);
    final int M = Integer.parseInt(args[1]);
    final int N = Integer.parseInt(args[2]);
    final int R = Counting.number_rows(K,M,N);
    final int[][][] occurrences = occurrences_field(Rows.list_rows(K,M,N,R), Field.empty_field(M,N));
    for (int i = 0; i < M; ++i)
      for (int j = 0; j < N; ++j) {
        System.out.print(i + "," + j + ": ");
        final int l = occurrences[i][j].length;
        for (int p = 0; p < l; ++p) System.out.print(occurrences[i][j][p] + " ");
        System.out.println();
      }
    System.out.println();
    show_occurrence_counts(occurrences);
  }

}
