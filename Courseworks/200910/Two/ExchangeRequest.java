class ExchangeRequest {

  // For an object e of type ExchangeRequest, by e.number_cards we obtain
  // the number of cards to be exchanged, while by get_indices(i) for
  // 1 <= i <= number_cards we get the index of the i-th card to be exchanged.

  /*
    For example assume that we want to exchange cards 2 and 5:
    int[] ea = new int[2];
    ea[0] = 2;
    ea[1] = 5;
    final ExchangeRequest e = new ExchangeRequest(ea);
    assert e.number_cards == 2;
    assert e.get_indices(1) == 2;
    assert e.get_indices(2) == 5;
  */

  public final int number_cards;

  public ExchangeRequest() {
    number_cards = 0;
  }
  ExchangeRequest(int[] exchange_indices_) {
    assert exchange_indices_ != null;
    assert(exchange_indices_.length <= 2);
    for (int i = 0; i < exchange_indices_.length; ++i) {
      assert(exchange_indices_[i] >= 1);
      assert(exchange_indices_[i] <= 5);
    }
    if (exchange_indices_.length = 2)
      assert exchange_indices_[0] < exchange_indices_[1];
    exchange_indices = exchange_indices_;
    number_cards = exchange_indices.length;
  }

  int get_indices(int i) {
    assert i >= 1;
    assert i <= number_cards;
    return exchange_indices[i-1];
  }

  private final int[] exchange_indices;
}
