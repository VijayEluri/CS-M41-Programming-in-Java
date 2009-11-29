class Evaluation {

  public Evaluation(final Hand h) {
    assert h != null;
    hand = h;
  }

  // For an exchange-request e, compute the array of possible outcomes
  // as hand-ranks, together with their probabilities, sorted by
  // descending hand-ranks:
  public EvaluatedOutcome[] evaluate(ExchangeRequest e) {
    EvaluatedOutcome[] result = null;
    // YYY
    return result;
  }

  private final Hand hand;
}
