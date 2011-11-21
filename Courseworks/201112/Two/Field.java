// Oliver Kullmann, 21.11.2011 (Swansea)

class Field {

  public static final int fe = 0; // field empty
  public static final int f1 = 1; // field occupied by player 1
  public static final int f2 = 2; // field occupied by player 2

  // creates the empty field of size m x n:
  public static int[][] empty_field(final int m, final int n) {
    assert(m >= 1);
    assert(n >= 1);
    return new int[m][n];
  }

  // checks whether field is a rectangular non-empty array with entries
  // fe, f1, or f2:
  public static boolean valid_field(int[][] field) {
    if (field == null) return false;
    final int M = field.length;
    if (M == 0) return false;
    if (field[0] == null) return false;
    final int N = field[0].length;
    if (N == 0) return false;
    for (int i = 0; i < M; ++i) {
      if (field[i] == null) return false;
      if (field[i].length != N) return false;
      for (int j = 0; j < N; ++j)
        if (! valid_entry(field[i][j])) return false;
    }
    return true;
  }

  public static boolean valid_entry(final int e) {
    return e == fe || e == f1 || e == f2;
  }

  public static boolean valid_coordinates(final int[][] field, final int i, final int j) {
    assert(valid_field(field));
     return i >= 1 && j >= 1 && i <= field.length && j <= field[0].length;
   }

  public static boolean valid_move(final int[][] field, final int i, final int j) {
    return valid_coordinates(field, i, j) && field[i-1][j-1] == fe;
  }

  // enters a valid move into the field and into the move-list:
  public static void enter_move(final int[][] field, final int[][] move_list,
  final int i, final int j, final int player, final int move_index) {
    assert(valid_move(field, i, j));
    assert(player == f1 || player == f2);
    field[i-1][j-1] = player;
    move_list[move_index][0] = i;
    move_list[move_index][1] = j;
  }

  public static void output_field(final int[][] field) {
    assert(valid_field(field));
    // XXX to be completed XXX
  }

  public static void output_movelist(final int[][] move_list) {
    // XXX to be completed XXX
  }
}
