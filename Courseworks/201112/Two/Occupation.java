// Oliver Kullmann, 21.11.2011 (Swansea)

class Occupation {

  public static int[][] occupation_vector(final int R) {
    assert(R >= 1);
    return new int[R][2];
  }

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
  public static boolean valid_occupation(final int[][] occupation, final int K) {
    if (! valid_occupation(occupation)) return false;
    for (int i = 0; i < occupation.length; ++i)
      if (occupation[i][0] + occupation[i][1] > K) return false;
    return true;
  }

  public static boolean owns_row(final int[][] occupation, final int r, final boolean first_player) {
    assert(valid_occupation(occupation));
    assert(r < occupation.length);
    if (first_player) return occupation[r][0] == 0;
    return occupation[r][1] == 0;
  }

  public static int update(final int[][] occupation, final int r, final boolean first_player) {
    assert(valid_occupation(occupation));
    assert(r < occupation.length);
    if (first_player) return ++occupation[r][0];
    return ++occupation[r][1];
  }
}
