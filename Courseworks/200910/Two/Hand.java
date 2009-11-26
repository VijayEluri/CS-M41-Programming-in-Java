class Hand {

  // Provides poker hands in a standardised form, first sorted by descending
  // ranks, and then "sorted" by suites as by the order given implicitly in
  // class Suit.

  public static final int hand_size = 5;
  public static final int num_hands = 2598960;

  public Hand(final Card c1, final Card c2, final Card c3, final Card c4, final Card c5) {
    cards = new Card[hand_size];
    cards[0] = c1;
    cards[1] = c2;
    cards[2] = c3;
    cards[3] = c4;
    cards[4] = c5;
    prepare_hand();
  }
  public Hand(final Card[] h) {
    assert h != null;
    assert h.length == hand_size;
    cards = h;
    prepare_hand();
  }
  // Reading from standard input:
  public Hand() {
    for (int i = 0; i < hand_size; ++i) {
      final CardRank rank = new CardRank(StdIn.readString());
      if (! StdIn.readString().equals("of"))
        throw new RuntimeException("After the card-rank a string different from \"of\" has been found.");
      final Suit suit = new Suit(StdIn.readString());
      cards[i] = new Card(rank, suit);
    }
    prepare_hand();
  }

  public int get(index i) {
    assert i >= 1;
    assert i <= hand_size;
    return cards[i-1];
  }

  private final Card[] cards;

  private static void sort_by_ranks(Card[] h) {
    // YYY
  }
  private static void sort_by_suites(Card[] h) {
    // YYY
  }
  private void prepare_hand() {
    sort_by_ranks(cards);
    sort_by_suites(cards);
    for (int i = 0; i < hand_size - 1; ++i)
      if (cards[i].equals(cards[i+1]))
        throw RuntimeException("Two identical cards were found in a hand.");
  }

}
