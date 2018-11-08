class Deck352 {
// How the distinction between 32 and 52 is done is a little exercise in
// code reading.
  public static void main(final String args[]) {
    final String suit[] = {"Clubs", "Diamonds", "Hearts", "Spades" };
    final int nr_suits = suit.length;
    assert(nr_suits == 4);
    final boolean short_deck = args.length != 0;
    final int nr_ranks = (short_deck) ? 8 : 13;
    final String rank[] = (short_deck) ?
        new String[]{"7","8","9","10","Jack","Queen","King","Ace"}
      : new String[]{"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    assert(rank.length == nr_ranks);
    final int nr_cards = nr_suits * nr_ranks;
    assert(nr_cards == ((short_deck) ? 32 : 52));

    final String deck[] = new String[nr_cards];
    for (int r = 0; r < nr_ranks; ++r)
      for (int s=0; s < nr_suits; ++s)
        deck[r*nr_suits+s] = rank[r] + " of " + suit[s];

    for (int i = 0; i < nr_cards; ++i) {
      final int rand = i + (int) (Math.random() * (nr_cards-i));
      final String t = deck[rand];
      deck[rand] = deck[i];
      deck[i] = t;
    }

    for (int i = 0; i < nr_cards; ++i) System.out.println(deck[i]);
  }
}
