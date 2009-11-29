// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class CardRank {

  // Wrapper class around card ranks as integer indices.
  // Note that a smaller index means a higher card.

  public static final int num_ranks = 13;
  public static final int ace = 0;
  public static final int king = 1;
  public static final int queen = 2;
  public static final int jack = 3;
  public static final int ten = 4;
  public static final int nine = 5;
  public static final int eight = 6;
  public static final int seven = 7;
  public static final int six = 8;
  public static final int five = 9;
  public static final int four = 10;
  public static final int three = 11;
  public static final int two = 12;

  public final int index;

  public CardRank(final int i) {
    assert(i >= 0);
    assert(i < num_ranks);
    index = i;
  }
  public CardRank(final String str) {
    index = determine_index(str);
  }

  public String toString() {
    return rank_names[index];
  }

  public boolean equals(CardRank s) {
    return s.index == index;
  }

  private static final String[] rank_names = {"Ace", "King", "Queen", "Jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};

  private static int determine_index(final String str) {
    for (int i = 0; i < num_ranks; ++i)
      if (str.equals(rank_names[i]))
        return i;
    throw new RuntimeException("Does not represent a card rank: " + str);
  }
}
