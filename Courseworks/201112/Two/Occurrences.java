// Oliver Kullmann, 21.11.2011 (Swansea)

class Occurrences {

  public static int[][][] occurrences_field(final int[][][] rows, final int[][] field) {
    assert(Field.valid_field(field));
    assert(Rows.check_list_rows(rows, field));
    final int M = field.length;
    final int N = field[0].length;
    int[][][] occ = new int[M][N][];
    // XXX to be completed XXX
    return occ;
  }

  // a consistency check, comparing rows and occurrences:
  public static boolean consistency_check(final int[][][] rows, final int[][][] occurrences) {
    if (occurrences == null) return false;
    final int M = occurrences.length;
    if (M == 0) return false;
    final int N = occurrences[0].length;
    if (N == 0) return false;
    if (occurrences == null) return false;
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

}
