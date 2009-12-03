// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class EvaluatedOutcome {

  // For a given hand and an exchange request, an "evaluated outcome"
  // is one of the possible hand-ranks which can arise after exchange,
  // together with the probability that this hand-rank will be obtained.

  public EvaluatedOutcome(final HandRank hr, final double p) {
    assert p >= 0;
    assert p <= 1;
    hand_rank = hr;
    prob = p;
  }

  public final HandRank hand_rank;
  public final double prob;

  public String toString() {
    return hand_rank + "\nProbability: " + prob;
  }

  public boolean equals(final EvaluatedOutcome e) {
    return e.hand_rank.equals(hand_rank) && e.prob == prob;
  }


  public static void main(String[] args) {
    System.out.println(new EvaluatedOutcome(new HandRank(1), 0.9));
  }
}
