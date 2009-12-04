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

  public boolean equals(final Card c) {
    return c.rank.equals(rank) && c.suit.equals(suit);
  }


  // Demonstration of functionality:
  public static void main(String[] args) {
    System.out.println(new Card(0));
    System.out.println(new Card(51));
    final Card c1 = new Card(new CardRank("Jack"), new Suit("Spades"));
    System.out.println(c1);
    // Demonstration that here references are harmless, since CardRank, Suit
    // and also Card behave similar to value-types (they are non-mutable):
    CardRank cr2 = new CardRank("5");
    Suit s2 = new Suit("Hearts");
    final Card c2 = new Card(cr2, s2);
    System.out.println(c2);
    cr2 = new CardRank("King");
    s2 = new Suit("Clubs");
    System.out.println(c2); // still the same, since the old cr2- and s2-objects didn't change
    System.out.println(new Card(cr2,s2));
  }
}
