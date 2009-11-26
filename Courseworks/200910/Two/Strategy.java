class Strategy {

  // For an object s of type Strategy by s.exchange(i) we obtain
  // the exchange request for the strategy with index i.

  public static final int number_strategies = 2; // to be updated

  public Strategy(final Hand h_) {
    h = h_;
  }
  public static ExchangeRequest exchange(final int index_strategy) {
    assert valid_index(index_strategy);
    assert i <= number_strategies;
    switch (i) {
      case 1 : return no_exchange();
      case 2 : return always_first_two();
      case 3 : return risky();
      case 4 : return cautious();
    }
  }

  public static boolean valid_index(final int i) {
    return i >= 1 && i <= number_strategies;
  }

  private Hand h;

  private static ExchangeRequest no_exchange() {
    return new ExchangeRequest();
  }

  private static ExchangeRequest always_first_two() {
    int[] e = int[2];
    e[0] = 1;
    e[1] = 2;
    return new ExchangeRequest(e);
  }

  // Go for the best hand achievable:
  private static ExchangeRequest risky() {
    // XXX
  }

  // Choose only amongst choices which can not impair the original hand:
  private static ExchangeRequest cautious() {
    // XXX
  }

  // Possibly further strategies to be implemented XXX
}

