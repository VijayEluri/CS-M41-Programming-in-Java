// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Evaluation {

  public Evaluation(final Hand h) {
    assert h != null;
    hand = h;
    other_cards = new Card[Card.num_cards - Hand.hand_size];
    // Set the 47 cards in other_cards to the cards not in h:
    // XXX
  }

  // For an exchange-request e, compute the array of possible outcomes
  // as hand-ranks, together with their probabilities, sorted by
  // descending hand-ranks:
  public EvaluationResult evaluate(ExchangeRequest e) {
    int[] count_outcomes = new int[HandRank.num_hand_ranks+1];
    // run through all possibilities, and enter the result into count_outcomes:
    // XXX
    int count_different_ranks = 0;
    // determine the number of different ranks entered into count_outcomes:
    // XXX
    // transfer the results into an array of evaluated outcomes:
    EvaluatedOutcome[] result = new EvaluatedOutcome[count_different_ranks];
    // XXX
    return new EvaluationResult(result);
  }

  private final Hand hand;
  private final Card[] other_cards;
}
