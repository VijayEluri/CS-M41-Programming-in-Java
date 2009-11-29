// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class EvaluatedOutcome {

  // For a given hand and an exchange request, an "evaluated outcome"
  // is one of the possible hand-ranks which can arise after exchange,
  // together with the probability that this hand-rank will be obtained.

  public EvaluatedOutcome(HandRank hr, double p) {
    assert p >= 0;
    assert p <= 1;
    hand_rank = hr;
    prob = p;
  }

  public final HandRank hand_rank;
  public final double prob;
}
