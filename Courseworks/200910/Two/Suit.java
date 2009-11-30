// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Suit {

  // Wrapper class around suites as integer indices.

  public static final int num_suites = 4;
  public static final int clubs = 0;
  public static final int spades = 1;
  public static final int hearts = 2;
  public static final int diamonds = 3;

  public final int index;

  public Suit(final int i) {
    assert(i >= 0);
    assert(i < num_suites);
    index = i;
  }
  public Suit(final String str) {
    index = determine_index(str);
  }

  public String toString() {
    return suit_names[index];
  }

  public boolean equals(final Suit s) {
    return s.index == index;
  }

  private static final String[] suit_names = {"Clubs", "Spades", "Hearts", "Diamonds" };

  private static int determine_index(final String str) {
    for (int i = 0; i < num_suites; ++i)
      if (str.equals(suit_names[i]))
        return i;
    throw new RuntimeException("Does not represent a suit: " + str);
  }
}
