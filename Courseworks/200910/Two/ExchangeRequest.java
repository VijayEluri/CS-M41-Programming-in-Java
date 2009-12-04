// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class ExchangeRequest {

  // For an object e of type ExchangeRequest, by e.number_cards we obtain
  // the number of cards to be exchanged, while by get_index(i) for
  // 1 <= i <= number_cards we get the index of the i-th card to be exchanged.

  /*
    For example assume that we want to exchange cards 2 and 5:
    int[] ea = new int[2];
    ea[0] = 2;
    ea[1] = 5;
    final ExchangeRequest e = new ExchangeRequest(ea);
    assert e.number_cards == 2;
    assert e.get_index(1) == 2;
    assert e.get_index(2) == 5;
  */

  public static final int max_exchanges = 2;
  public static final int total_num_requests = 1 + 5 + 10;

  public final int number_cards;

  public ExchangeRequest() {
    number_cards = 0;
    exchange_indices = null;
  }
  public ExchangeRequest(final int e1) {
    assert e1 >= 1;
    assert e1 <= Hand.hand_size;
    number_cards = 1;
    exchange_indices = new int[1];
    exchange_indices[0] = e1;
  }
  public ExchangeRequest(final int e1, final int e2) {
    assert e1 >= 1;
    assert e1 <= Hand.hand_size;
    assert e2 >= 1;
    assert e2 <= Hand.hand_size;
    assert e1 < e2;
    number_cards = 2;
    exchange_indices = new int[2];
    exchange_indices[0] = e1;
    exchange_indices[1] = e2;
  }
  public ExchangeRequest(int[] exchange_indices_) {
    assert exchange_indices_ != null;
    assert(exchange_indices_.length <= max_exchanges);
    for (int i = 0; i < exchange_indices_.length; ++i) {
      assert(exchange_indices_[i] >= 1);
      assert(exchange_indices_[i] <= Hand.hand_size);
    }
    if (exchange_indices_.length == max_exchanges)
      assert exchange_indices_[0] < exchange_indices_[1];
    exchange_indices = exchange_indices_;
    number_cards = exchange_indices.length;
  }

  public int get_index(final int i) {
    assert i >= 1;
    assert i <= number_cards;
    return exchange_indices[i-1];
  }

  public String toString() {
    if (number_cards == 0)
      return "()";
    if (number_cards == 1)
      return "(" + exchange_indices[0] + ")";
    return "(" + exchange_indices[0] + "," + exchange_indices[1] + ")";
  }

  public boolean equals(final ExchangeRequest E) {
    if (E.number_cards != number_cards) return false;
    if (number_cards == 0) return true;
    if (number_cards == 1) return E.exchange_indices[0] == exchange_indices[0];
    return E.exchange_indices[0] == exchange_indices[0] && E.exchange_indices[1] == exchange_indices[1];
  }

  public static ExchangeRequest[] all_requests() {
    final ExchangeRequest[] E = new ExchangeRequest[total_num_requests];
    E[0] = new ExchangeRequest();
    for (int i = 1; i <= Hand.hand_size; ++i)
      E[i] = new ExchangeRequest(i);
    int next_index = Hand.hand_size+1;
    for (int i = 1; i <= Hand.hand_size-1; ++i)
      for (int j =i+1; j <= Hand.hand_size; ++j)
        E[next_index++] = new ExchangeRequest(i,j);
    return E;
  }

  private final int[] exchange_indices;


  public static void main(String[] args) {
    final ExchangeRequest[] E = all_requests();
    assert E.length == total_num_requests;
    for (int i = 0; i < total_num_requests; ++i)
      System.out.println(E[i]);
  }

}
