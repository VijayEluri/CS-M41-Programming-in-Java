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

  // these are the "user"-coordinates, and thus 1-based:
  public static boolean valid_coordinates(final int[][] field, final int i, final int j) {
    assert(valid_field(field));
    return i >= 1 && j >= 1 && i <= field.length && j <= field[0].length;
  }

  // i, j are "user-coordinates", and thus 1-based:
  public static boolean valid_move(final int[][] field, final int i, final int j) {
    return valid_coordinates(field, i, j) && field[i-1][j-1] == fe;
  }

  // enters a valid move into the field and into the move-list, with update
  // of move_index and occupation; returns the maximum of the number of fields
  // occupied by the player for the involved rows:
  public static int enter_move(
      final int[][] field,
      final int i, final int j,
      final boolean first_player,
      final int[][] move_list, final int[] move_index,
      final int[][][] occurrences,
      final int[][] occupation) {
    assert(valid_move(field, i, j));
    final int player = (first_player) ? f1 : f2;
    field[i-1][j-1] = player;
    assert(move_index != null);
    assert(move_index.length == 1);
    assert(move_list != null);
    assert(move_index[0] < move_list.length);
    move_list[move_index[0]][0] = i;
    move_list[move_index[0]][1] = j;
    ++move_index[0];
    final int[] occ = occurrences[i-1][j-1];
    assert(occ != null);
    int max = Integer.MIN_VALUE;
    for (int r = 0; r < occ.length; ++r) {
      final int new_length = Occupation.update(occupation, r, first_player);
      if (new_length > max) max = new_length;
    }
    return max;
  }

  // printing out a field:
  public static void output_field(final int[][] field) {
    assert(valid_field(field));
    final int M = field.length, N = field[0].length;
    final int horiz_spaces = (N <= 9) ? 1 : (N <= 99) ? 2 : 3;
    final int vert_spaces = (M <= 9) ? 1 : (M <= 99) ? 2 : 3;
    StdOut.printf("%" + (vert_spaces+2) + "s", " ");
    for (int j = 0; j < N; ++j) StdOut.printf("%" + horiz_spaces + "s", j+1);
    System.out.println();
    for (int i = 0; i < M; ++i) {
      StdOut.printf("%" + vert_spaces + "d: ",i+1);
      for (int j = 0; j < N; ++j) StdOut.printf("%" + horiz_spaces + "s", cell(field[i][j]));
      System.out.println();
    }
    System.out.println();
  }
  private static String cell(final int f) {
    assert(f == fe || f == f1 || f == f2);
    if (f == fe) return ".";
    if (f == f1) return "X";
    return "0";
  }

  // printing out a move-list:
  public static void output_movelist(final int[][] move_list) {
    assert(move_list != null);
    final int number_moves = move_list.length;
    for (int m = 0; m < number_moves; ++m) {
      assert(move_list[m] != null);
      assert(move_list[m].length == 2);
      final int i = move_list[m][0];
      assert(i >= 1);
      final int j = move_list[m][1];
      assert(j >= 1);
      final String label = (m%2 == 0) ? "I" : "II";
      StdOut.printf("%3d.%-2s: %2d %2d\n", m/2+1, label, i, j);
    }
  }
}
