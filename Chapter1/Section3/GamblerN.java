class GamblerN {
  public static void main(final String[] args) {
    if (args.length < 3) {
      System.err.println("Three arguments are needed: stake, target and number of trials (with an optional fourth argument p).");
      return;
    }
    final long stake = Long.parseLong(args[0]);
    if (stake < 1) {
      System.err.println("Stake=" + stake + " must be positive.");
      return;
    }
    final long target = Long.parseLong(args[1]);
    if (target <= stake) {
      System.err.println("Target=" + target + " is not greater than stake=" + stake + ".");
    }
    final long num_trials = Long.parseLong(args[2]);
    if (num_trials < 0) {
      System.err.println("Negative number of trials" + num_trials + ".");
      return;
    }
    final double p;
    if (args.length == 3) p = 0.5;
    else p = Double.parseDouble(args[3]);
    if (p < 0 || p > 1) {
      System.err.println("Probability p=" + p + " is not between 0 and 1.");
      return;
    }
    
    long wins = 0, losses = 0;
    long min_steps = Long.MAX_VALUE, max_steps = 0, sum_steps = 0;
    
    for (long i = 0; i < num_trials; ++i) {
      long cash = stake;
      long steps=0;
      while (cash > 0 && cash < target) {
        ++steps;
        if (Math.random() < p) ++cash;
        else --cash;
      }
      assert(cash == 0 || cash == target);
      if (cash == 0) ++losses;
      else ++wins;
      if (steps < min_steps) min_steps = steps;
      if (steps > max_steps) max_steps = steps;
      sum_steps += steps;
    }
    assert(wins + losses == num_trials);
    
    System.out.println("stake=" + stake + ", target=" + target + ", trials=" +
      num_trials + ", p = " + p + ".");
    System.out.println("wins = " + wins + " = " +
      wins/(double) num_trials + "%");
    System.out.println("losses = " + losses + " = " +
      losses/(double) num_trials + "%");
    System.out.println("Number of steps for each games:");
    System.out.println("minimum = " + min_steps);
    System.out.println("maximum = " + max_steps);
    System.out.println("average = " + sum_steps/num_trials);
  }
}
