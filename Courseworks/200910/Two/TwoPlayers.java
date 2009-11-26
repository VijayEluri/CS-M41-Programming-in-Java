class TwoPlayers {

  // Plays a single game between two players, using all available strategies
  // from the Strategy class, and returns an object of type Storage with
  // entries from {-1,0,+1}, where an entry -1 means player 2 wins, +1 means
  // player 1 wins, and 0 means draw:
  public static Storage play() {
    Storage R = new Storage(Strategy.number_strategies); // for the results
    ExchangeRequest[] E = new ExchangeRequest[Strategy.number_strategies]; // for the exchange requests (per strategy)
    Bank b = new Bank(2);
    // XXX
  }

}
