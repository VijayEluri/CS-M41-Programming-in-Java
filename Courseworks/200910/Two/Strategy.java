// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Strategy {

  // For an object s of type Strategy by s.exchange(i) we obtain
  // the exchange request for the strategy with index i.

  public static final int number_strategies = 5; // to be updated

  public Strategy(final Hand h_) {
    h = h_;
  }
  public ExchangeRequest exchange(final int index_strategy) {
    assert valid_index(index_strategy);
    assert index_strategy <= number_strategies;
    switch (index_strategy) {
      case 1 : return no_exchange();
      case 2 : return always_first_two();
      case 3 : return always_last();
      case 4 : return always_last_two();
      case 5 : return minimise_expected_number_better_hands();
      case 6 : return risky();
      case 7 : return cautious();
    }
    return no_exchange(); // for the compiler; does not occur for valid indices
  }

  public static boolean valid_index(final int i) {
    return i >= 1 && i <= number_strategies;
  }

  private Hand h;

  private static ExchangeRequest no_exchange() {
    return new ExchangeRequest();
  }

  private static ExchangeRequest always_first_two() {
    int[] e = new int[2];
    e[0] = 1;
    e[1] = 2;
    return new ExchangeRequest(e);
  }

  private static ExchangeRequest always_last() {
    int[] e = new int[1];
    e[0] = 5;
    return new ExchangeRequest(e);
  }

  private static ExchangeRequest always_last_two() {
    int[] e = new int[2];
    e[0] = 4;
    e[1] = 5;
    return new ExchangeRequest(e);
  }

  private ExchangeRequest minimise_expected_number_better_hands() {
    final Evaluation eval = new Evaluation(h);
    final ExchangeRequest[] all_requests = ExchangeRequest.all_requests();
    ExchangeRequest best_exchange_request = null;
    double best_expected_value = Double.POSITIVE_INFINITY;
    for (int i = 0; i < all_requests.length; ++i) {
      final ExchangeRequest new_exchange_request = all_requests[i];
      final double new_expected_value = expected_number_better_hands(eval.evaluate(new_exchange_request));
      if (new_expected_value < best_expected_value) {
        best_expected_value = new_expected_value;
        best_exchange_request = new_exchange_request;
      }
    }
    return best_exchange_request;
  }
  private static double expected_number_better_hands(final EvaluationResult E) {
    double sum = 0;
    for (int i = 1; i <= E.length; ++i) {
      final EvaluatedOutcome e = E.get(i);
      sum += e.hand_rank.cumulated_count() * e.prob;
    }
    return sum;
  }


  // Go for the best hand achievable:
  private ExchangeRequest risky() {
    // XXX
    return new ExchangeRequest(); // temporarily
  }

  // Choose only amongst choices which can not impair the original hand:
  private ExchangeRequest cautious() {
    // XXX
    return new ExchangeRequest(); // temporarily
  }

  // Possibly further strategies to be implemented XXX


  // Tests:
  public static void main(String[] args) {
    {
      final Hand h = new Hand(new Card(0), new Card(1), new Card(2), new Card(3), new Card(25));
      final Strategy s = new Strategy(h);
      final ExchangeRequest e = s.exchange(5);
      assert (e.equals(new ExchangeRequest(4,5)));
    }
    final Bank b = new Bank(1);
    final Hand h = b.orig_hand(1);
    final Strategy s = new Strategy(h);
    System.out.println(h);
    for (int i = 1; i <= number_strategies; ++i) {
      System.out.println(i + ": " + s.exchange(i));
    }
  }
}

