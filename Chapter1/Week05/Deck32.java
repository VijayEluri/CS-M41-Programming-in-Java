class Deck32 {
  public static void main(final String args[]) {
    final String suit[] = {"Clubs", "Diamonds", "Hearts", "Spades" };
    final String rank[] = {"7", "8", "9","10", "Jack", "Queen", "King", "Ace" };
    final int nr_suits = suit.length;
    final int nr_ranks = rank.length;
    final int nr_cards = nr_suits * nr_ranks;
    assert(nr_cards == 32);

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
