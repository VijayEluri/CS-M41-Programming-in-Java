// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Card {

  // A "card" as a pair of (card-)rank and suit.

  public static final int num_cards = Suit.num_suites * CardRank.num_ranks;

  public final CardRank rank;
  public final Suit suit;

  public Card(final CardRank r, final Suit s) {
    rank = r;
    suit = s;
  }
  public Card(final int i) {
    assert i >= 0;
    assert i < num_cards;
    rank = new CardRank(i % CardRank.num_ranks);
    suit = new Suit(i / CardRank.num_ranks);
  }

  public int index() {
    return suit.index * CardRank.num_ranks + rank.index;
  }

  public String toString() {
    return rank.toString() + " of " + suit.toString();
  }

  public boolean equals(Card c) {
    return c.rank.equals(rank) && c.suit.equals(suit);
  }
}
