// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class TwoPlayers {

  // Plays a single game between two players, using all available strategies
  // from the Strategy class, and returns an object of type Storage with
  // entries from {-1,0,+1}, where an entry -1 means player 2 wins, +1 means
  // player 1 wins, and 0 means draw:
  public static Storage play() {
    Storage R = new Storage(Strategy.number_strategies); // for the results
    Bank b = new Bank(2);
    // XXX
    for (int s1 = 1; s1 < Strategy.number_strategies; ++s1)
      for (int s2 = s1+1; s2 <= Strategy.number_strategies; ++s2) {
        // XXX
      }
    return R;
  }

}
