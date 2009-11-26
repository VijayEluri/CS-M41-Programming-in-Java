class Coursework1 {

  // Simulating the behaviour of the Poker-program from the first
  // coursework (with additional information when evaluating a single hand):
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("ERROR[Poker]: One argument N is required.");
      return;
    }
    final int N = Integer.parseInt(args[0]);
    if (N < 0) {
      System.err.println("ERROR[Poker]: N < 0.");
      return;
    }
    if (N == 0) {
      Hand h = new Hand();
      final HandRank hr = new HandRank(h);
      System.println(hr);
    }
    else {
      int[] counts = new int[HandRank.num_major_hand_ranks+1];
      for (int i = 1; i <= N; ++i) {
        final Bank b = new Bank(1);
        final HandRank hr = b.orig_hand(1);
        ++counts[hr.major_rank];
      }
      for (int r = 1; r <= HandRank.num_major_hand_ranks; ++r)
        System.out.println(HandRank.major_hand_rank_names[r-1] + ": " + (double) (counts[r]) / N * 100 + "%");
    }
}
