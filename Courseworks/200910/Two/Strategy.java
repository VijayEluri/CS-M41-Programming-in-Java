// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Strategy {

  // For an object s of type Strategy by s.exchange(i) we obtain
  // the exchange request for the strategy with index i.

  public static final int number_strategies = 4; // to be updated

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
      case 5 : return risky();
      case 6 : return cautious();
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
}

