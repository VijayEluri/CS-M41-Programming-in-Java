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
    int next_card_index = number_players * Hand.hand_size;
    for (int i = 0; i < number_players; ++i) {
      Card[] h = new Card[5];
      for (int j = 0; j < Hand.hand_size; ++j)
        h[j] = orig_hands[i].get(j+1);
      final ExchangeRequest e = E[i];
      for (int j = 1; j <= e.number_cards; ++j) {
        h[e.get_indices(j)-1] = new Card(deck[next_card_index]);
        ++next_card_index;
      }
      new_hands[i] = new Hand(h);
    }
  }

  private final int[] deck; // here the card *indices* are used
  private final Hand[] orig_hands;
  private Hand[] new_hands;

  // Random shuffling of the deck of cards:
  private void shuffle_deck() {
    for (int i = 0; i < Card.num_cards; ++i) {
      final int rand = i + (int) (Math.random() * (Card.num_cards-i));
      final int t = deck[rand];
      deck[rand] = deck[i];
      deck[i] = t;
    }
  }
  // Set orig_hands:
  private void set_orig_hands() {
    Card[] h = new Card[5];
    int current_card_index = 0;
    for (int i = 0; i < number_players; ++i) {
      for (int c = 0; c < Hand.hand_size; ++c, ++current_card_index)
        h[c] = new Card(deck[current_card_index]);
      orig_hands[i] = new Hand(h);
    }
  }


  // Tests:
  public static void main(String[] args) {
    final int num_players = 7;
    Bank B = new Bank(num_players);
    for (int i = 0; i < Card.num_cards; ++i)
        System.out.println((i+1) + ": " + new Card(B.deck[i]));
    assert B.number_players == num_players;
    assert B.deck.length == 52;
    assert B.orig_hands.length == num_players;
    assert B.new_hands == null;
    for (int i = 1; i <= num_players; ++i)
      System.out.println(i + ": " + B.orig_hand(i));
    ExchangeRequest[] E = new ExchangeRequest[num_players];
    for (int i = 0; i < num_players; ++i) {
      int[] e = new int[2];
      e[0] = 1; e[1] = 3;
      E[i] = new ExchangeRequest(e);
    }
    B.perform_exchanges(E);
    for (int i = 1; i <= num_players; ++i)
      System.out.println(i + ": " + B.new_hand(i));
    for (int i = 0; i < num_players; ++i) {
      int[] e = new int[0];
      E[i] = new ExchangeRequest(e);
    }
    B.perform_exchanges(E);
    for (int i = 1; i <= num_players; ++i)
      System.out.println(i + ": " + B.new_hand(i));

  }
}
 
