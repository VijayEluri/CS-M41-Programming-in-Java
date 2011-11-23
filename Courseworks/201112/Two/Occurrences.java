// Oliver Kullmann, 21.11.2011 (Swansea)

class Occurrences {

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

}
