// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Hand {

  // Provides poker hands in a standardised form, sorted by descending
  // ranks.

  // Three constructors are given, for reading five cards, an array of cards,
  // or for reading from standard input.

  // If the hand does not consist of five different cards, then an exception
  // is thrown.

  // Access to cards by get(i), where 1 <= i <= 5.

  // Dependencies: In.java.

  public static final int hand_size = 5;
  public static final int num_hands = 2598960; // = binom(52,5)

  // This constructor takes over ownership of c1,...,c5:
  public Hand(final Card c1, final Card c2, final Card c3, final Card c4, final Card c5) {
    cards = new Card[hand_size];
    cards[0] = c1;
    cards[1] = c2;
    cards[2] = c3;
    cards[3] = c4;
    cards[4] = c5;
    prepare_hand();
  }
  // This constructor takes over ownership of the five elements of h:
  public Hand(final Card[] h) {
    assert h != null;
    assert h.length == hand_size;
    cards = new Card[hand_size];
    for (int i = 0; i < hand_size; ++i)
      cards[i] = h[i];
    prepare_hand();
  }
  // Reading from an input stream:
  public Hand(final In in) {
    cards = new Card[hand_size];
    for (int i = 0; i < hand_size; ++i) {
      final CardRank rank = new CardRank(in.readString());
      if (! in.readString().equals("of"))
        throw new RuntimeException("After the card-rank a string different from \"of\" has been found.");
      final Suit suit = new Suit(in.readString());
      cards[i] = new Card(rank, suit);
    }
    prepare_hand();
  }

  public Card get(final int i) {
    assert i >= 1;
    assert i <= hand_size;
    return cards[i-1];
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < hand_size; ++i) {
      final Card c = cards[i];
      result += c.rank.toString();
      result += " of ";
      result += c.suit.toString();
      result += "; ";
    }
    return result;
  }

  public boolean equals(final Hand h) {
    for (int i = 0; i < hand_size; ++i)
      if (! h.cards[i].equals(cards[i]))
        return false;
    return true;
  }

  private final Card[] cards;

  // Sorting by selection sort:
  private static void sort_by_ranks(final Card[] h) {
    for (int i = 0; i < h.length-1; ++i) {
      int index_min = i;
      for (int j = i+1; j < h.length; ++j)
        if (h[j].rank.index < h[index_min].rank.index)
          index_min = j;
      if (index_min != i) {
        final Card temp = h[i];
        h[i] = h[index_min];
        h[index_min] = temp;
      }
    }
  }
  /* Remarks: If we wanted to use a sorting algorithm from the Java library,
     then we needed some means to "tell" that algorithm the sorting criterions;
     by what we learned in the module, yet we cannot provide such means.
     By the above private method we can provide a specialised method,
     tailored for our needs.
  */

  public static void check_all_different(final Card[] h) {
    for (int i = 0; i < hand_size - 1; ++i)
      if (h[i].equals(h[i+1]))
        throw new RuntimeException("Two identical cards were found in a hand.");
  }

  private void prepare_hand() {
    sort_by_ranks(cards);
    check_all_different(cards);
  }

}
