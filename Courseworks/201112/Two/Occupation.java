// Oliver Kullmann, 21.11.2011 (Swansea)

/*
  Provides the array "occupation", which for each "row" (given by its
  index) contains the current number of cells owned by the two players.
*/

class Occupation {

  // the occupation-array, initialised with all counts zero:
  public static int[][] occupation_vector(final int R) {
    assert(R >= 1);
    return new int[R][2];
  }

  // checking whether occupation has been fully allocated, and with each
  // count non-negative:
  public static boolean valid_occupation(final int[][] occupation) {
    assert(occupation != null);
    for (int i = 0; i < occupation.length; ++i) {
      if (occupation[i] == null) return false;
      if (occupation[i].length != 2) return false;
      if (occupation[i][0] < 0) return false;
      if (occupation[i][1] < 0) return false;
    }
    return true;
  }
  // checks additionally also whether the sum of counts per row is never
  // greater than the length of the row:
  public static boolean valid_occupation(final int[][] occupation, final int K) {
    if (! valid_occupation(occupation)) return false;
    for (int i = 0; i < occupation.length; ++i)
      if (occupation[i][0] + occupation[i][1] > K) return false;
    return true;
  }

  // function to support algorithmic playing: a row r is "owned" by a
  // player if the other player does not own cells in that row:
  public static boolean owns_row(final int[][] occupation, final int r, final boolean first_player) {
    assert(valid_occupation(occupation));
    assert(r < occupation.length);
    if (first_player) return occupation[r][0] == 0;
    return occupation[r][1] == 0;
  }

  // update the occupation-count for row r after a cell has been marked:
  public static int update(final int[][] occupation, final int r, final boolean first_player) {
    assert(valid_occupation(occupation));
    assert(r < occupation.length);
    if (first_player) return ++occupation[r][0];
    return ++occupation[r][1];
  }
}
