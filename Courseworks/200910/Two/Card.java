class Card {

  // A "card" as a pair of suit and (card-)rank.

  public static final int num_cards = Suit.num_suites * CardRank.num_ranks;

  public final Suit suit;
  public final CardRank rank;

  public Card(final Suit s, final CardRank r) {
    suit = s;
    rank = r;
  }
  public Card(final int i) {
    assert i >= 0;
    assert i < num_cards;
    suit = new Suit(i / CardRank.num_ranks);
    rank = new CardRank(i % CardRank.num_ranks);
  }

  public int index() {
    suit.index * CardRank.num_ranks + rank.index;
  }

  String toString() {
    return rank.toString() + " of " + suit.toString();
  }

  boolean equals(Card c) {
    return c.suit.equals(suit) && c.rank.equals(rank);
  }
}
