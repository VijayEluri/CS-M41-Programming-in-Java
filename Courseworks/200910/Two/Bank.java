// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Bank {

  // By Bank(n) for 0 <= n <= 7 we create n hands, which can be obtained
  // by orig_hand(p). Then by perform_exchanges(E) for an array E of
  // exchange requests we exchange cards in the hands, where the new hands
  // are available then by new_hand(p). Such exchange requests can be
  // used arbitrarily often (always using the original deck of cards and the
  // original hands).

  public Bank(int n) {
    assert n >= 0;
    assert n <= max_number_players;
    number_players = n;
    deck = new int[Card.num_cards];
    for (int i = 0; i < Card.num_cards; ++i)
      deck[i] = i;
    shuffle_deck();
    orig_hands = new Hand[number_players];
    set_orig_hands();
  }

  public static final int max_number_players = 7; // 7*(5+2) = 49
  public final int number_players;

  // Returns the hand of a player before exchange:
  public Hand orig_hand(int player) {
    assert player >= 1;
    assert player <= number_players;
    return orig_hands[player-1];
  }
  // Returns the hand of a player after exchange:
  public Hand new_hand(int player) {
    assert player >= 1;
    assert player <= number_players;
    assert new_hands != null;
    return new_hands[player-1];
  }

  // Given an array E of exchange-requests (one for each player),
  // set new_hands accordingly; this function can be applied
  // arbitrarily often:
  public void perform_exchanges(ExchangeRequest[] E) {
    assert E.length == number_players;
    new_hands = new Hand[number_players];
    // XXX
  }

  private final int[] deck; // here the card *indices* are used
  private final Hand[] orig_hands;
  private Hand[] new_hands;

  // Random shuffling of the deck of cards:
  private void shuffle_deck() {
    // XXX
  }
  // Set orig_hands:
  private void set_orig_hands() {
    // XXX
  }
}
 
