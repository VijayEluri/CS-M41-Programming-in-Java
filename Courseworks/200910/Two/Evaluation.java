// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Evaluation {

  public Evaluation(final Hand h) {
    assert h != null;
    hand = h;
    other_cards = new Card[Card.num_cards - Hand.hand_size];
    int[] deck = new int[Card.num_cards];
    for (int i = 0; i < Card.num_cards; ++i)
      deck[i] = i;
    for (int i = 1; i <= Hand.hand_size; ++i)
      deck[h.get(i).index()] = -1;
    int next_index = 0;
    for (int i = 0; i < Card.num_cards; ++i)
      if (deck[i] != -1)
        other_cards[next_index++] = new Card(deck[i]);
  }

  // For an exchange-request e, compute the array of possible outcomes
  // as hand-ranks, together with their probabilities, sorted by
  // descending hand-ranks:
  public EvaluatedOutcome[] evaluate(ExchangeRequest e) {
    int[] count_outcomes = new int[HandRank.num_hand_ranks+1];
    // run through all possibilities, and enter the result into count_outcomes:
    // XXX
    int count_different_ranks = 0;
    // determine the number of different ranks entered into count_outcomes:
    // XXX
    // transfer the results into an array of evaluated outcomes:
    EvaluatedOutcome[] result = new EvaluatedOutcome[count_different_ranks];
    // XXX
    return result;
  }

  private final Hand hand;
  private final Card[] other_cards;


  public static void main(String[] args) {
    final Bank b = new Bank(1);
    final Hand h = b.orig_hand(1);
    System.out.println(h);
    final Evaluation E = new Evaluation(h);
    for (int i = 0; i < 47; ++i)
      System.out.println(E.other_cards[i]);
  }
}
