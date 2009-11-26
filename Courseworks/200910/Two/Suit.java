class Suit {

  // Wrapper class around suites as integer indices.

  public static final int num_suites = 4;
  public static final int clubs = 0;
  public static final int spades = 1;
  public static final int hearts = 2;
  public static final int diamonds = 3;

  public final int index;

  Suit(final int i) {
    assert(i >= 0);
    assert(i < num_suites);
    index = i;
  }
  Suit(final String str) {
    for (int i = 0; i < num_suites; ++i)
      if (s.equals(suit_names[i])) {
        index = i;
        return;
      }
    throw new RuntimeException("Does not represent a suit: " + str);
  }

  String toString() {
    return suit_names[index];
  }

  boolean equals(Suit s) {
    return s.index == index;
  }

  private static final String[] suit_names = {"Clubs", "Spades", "Hearts", "Diamonds" };

}
