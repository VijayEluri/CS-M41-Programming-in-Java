// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Tournament {

  // Read the number N of rounds from the command-line, and for each
  // pair 1 <= i < j <= Strategy.number_strategies of strategies from Strategy,
  // play N rounds, showing the difference of the number of games won by
  // strategy i minus the number of games won by strategy j.
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("ERROR[Tournament]: One parameter is neded, the number N of games.");
      return;
    }
    final long N = Long.parseLong(args[0]);
    final Storage S = new Storage(Strategy.number_strategies);
    for (long t = 1; t <= N; ++t) {
      final Storage R = TwoPlayers.play();
      for (int i = 1; i < Strategy.number_strategies; ++i)
        for (int j = i+1; j <= Strategy.number_strategies; ++j)
          S.set(i,j, S.get(i,j)+R.get(i,j));
    }
    System.out.println(S);
  }
}
