// Oliver Kullmann, 24.9.2009 (Swansea)

class Gambler {
  public static void main(String[] args) {

  try {
    final int stake = Integer.parseInt(args[0]);
    final int goal = Integer.parseInt(args[1]);
    final int T = Integer.parseInt(args[2]);
    int wins = 0, losses = 0;

    for (int t = 0; t < T; ++t) {
      int cash = stake;
      while (cash > 0 && cash < goal)
        if (Math.random() < 0.5) ++cash;
        else --cash;
      if (cash == goal) ++wins;
      if (cash == 0) ++losses;
    }
    assert wins+losses == T; // ATTENTION: Prerequisites are 0 <= stake <= goal and goal > 0, T >= 0. Left as exercise to check this.

    System.out.println(wins + " wins in " + T + " trials, and " + losses + " losses.");
  }

  catch(ArrayIndexOutOfBoundsException e) {
    System.err.println("ERROR[RandomInt]: Three parameter values are needed, namely the integers stake, goal and T (number of trials).");
  }
  catch(RuntimeException e) {
    System.err.println("ERROR[RandomInt]: The parameters must be integers within the range from -2147483648 to 2147483647.");
  }

  }
}
