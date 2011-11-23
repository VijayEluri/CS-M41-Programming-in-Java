// Oliver Kullmann, 21.11.2011 (Swansea)

/* Library for handling the playing field ("board"), which provides the
   following functionality:

   - constants fe, f1, f2 as codes for cell-values;
   - valid_entry(e) checks whether e is one of these codes;
   - empty_field(M,N) returns the empty field of dimension M X N;
   - valid_field(field) checks whether field has been initialised, and
     all cells contain valid values;
   - valid_coordinates(field, i, j) checks whether i, j are valid
     (matrix-)coordinates (or matrix-indices) for field; these are
     the user-coordinates, which are 1-based, while the internal
     representations are all 0-based; the top-left corner thus has (here!)
     coordinates i=1, j=1 (internally i=0, j=0);
   - valid_move(field, i, j) checks whether occupying field i,j is valid
     for field;
   - enter_move(field, i, j, first_player, move_list, move_index, occurrences,
     occupation) enters the valid move given by i,j into field, where the
     boolean first_player states whether it is the first player's move or not;
     update of data-structures move_list, move_index, and occupation;
   - output_field(field) prints a simple representation of field to
     standard output;
   - output_movelist(move_list, number_moves) prints out a move-list.

*/

class Field {

  public static final int fe = 0; // cell empty
  public static final int f1 = 1; // cell occupied by player 1
  public static final int f2 = 2; // cell occupied by player 2

  public static boolean valid_entry(final int e) {
    return e == fe || e == f1 || e == f2;
  }

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
      final int new_length = Occupation.update(occupation, occ[r], first_player);
      if (new_length > max) max = new_length;
    }
    return max;
  }

  // printing out a field:
  public static void output_field(final int[][] field) {
    // XXX to be completed XXX
  }
  private static String cell(final int f) {
    assert(f == fe || f == f1 || f == f2);
    if (f == fe) return ".";
    if (f == f1) return "X";
    return "0";
  }

  // printing out a move-list:
  public static void output_movelist(final int[][] move_list, final int number_moves) {
    // XXX to be completed XXX
  }
}
