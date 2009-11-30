// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class HandRank {

  // Poker has 3614 ranks, where a rank is a number from 1 to 3614 assigned
  // to a (Poker) hand such that a hand wins over another hand if and only if
  // its rank is strictly lower (in case of equality thus we have a draw).
  // The ranks are subdivided into 9 major ranks, where each is subdivided
  // further into minor ranks.
  // All three kinds of ranks are enumerated starting with value 1 for the
  // highest possibility.

  public static final int num_major_hand_ranks = 9;
  public static final int straight_flush = 1;
  public static final int four_of_a_kind = 2;
  public static final int full_house = 3;
  public static final int flush = 4;
  public static final int straight = 5;
  public static final int three_of_a_kind = 6;
  public static final int two_pairs = 7;
  public static final int one_pair = 8;
  public static final int high_card = 9;

  // The number of minor ranks in a major rank:
  public static final int num_straight_flush_ranks = 10;
  public static final int num_four_of_a_kind_ranks = 13;
  public static final int num_full_house_ranks = 13;
  public static final int num_flush_ranks = 1277;
  public static final int num_straight_ranks = 10;
  public static final int num_three_of_a_kind_ranks = 13;
  public static final int num_two_pairs_ranks = 286;
  public static final int num_one_pair_ranks = 715;
  public static final int num_high_card_ranks = 1277;

  // The number of ranks up to a major rank:
  public static final int cum_straight_flush_ranks = num_straight_flush_ranks;
  public static final int cum_four_of_a_kind_ranks = cum_straight_flush_ranks + num_four_of_a_kind_ranks;
  public static final int cum_full_house_ranks = cum_four_of_a_kind_ranks + num_full_house_ranks;
  public static final int cum_flush_ranks = cum_full_house_ranks + num_flush_ranks;
  public static final int cum_straight_ranks = cum_flush_ranks + num_straight_ranks;
  public static final int cum_three_of_a_kind_ranks = cum_straight_ranks + num_three_of_a_kind_ranks;
  public static final int cum_two_pairs_ranks = cum_three_of_a_kind_ranks + num_two_pairs_ranks;
  public static final int cum_one_pair_ranks = cum_two_pairs_ranks + num_one_pair_ranks;
  public static final int cum_high_card_ranks = cum_one_pair_ranks + num_high_card_ranks;

  // Access for a given major rank to the cumulated ranks via an array:
  public static final int[] cumulated_num_ranks = {0, cum_straight_flush_ranks, cum_four_of_a_kind_ranks, cum_full_house_ranks, cum_flush_ranks, cum_straight_ranks, cum_three_of_a_kind_ranks, cum_two_pairs_ranks, cum_one_pair_ranks, cum_high_card_ranks};

  public static final int num_hand_ranks = cum_high_card_ranks; // = 3614

  // For a given rank, how many hands of that rank are there (this depends
  // only on the major rank):
  public static final int size_straight_flush_rank = 4;
  public static final int size_four_of_a_kind_rank = 48;
  public static final int size_full_house_rank = 288;
  public static final int size_flush_rank = 4;
  public static final int size_straight_rank = 1020;
  public static final int size_three_of_a_kind_rank = 4224;
  public static final int size_two_pairs_rank = 432;
  public static final int size_one_pair_rank = 1536;
  public static final int size_high_card_rank = 1020;

  // Access for a given rank to the sizes via an array (through the associated
  // major rank):
  public static final int[] size_ranks = {0, size_straight_flush_rank, size_four_of_a_kind_rank, size_full_house_rank, size_flush_rank, size_straight_rank, size_three_of_a_kind_rank, size_two_pairs_rank, size_one_pair_rank, size_high_card_rank};

  // The number of hands of a given major rank:
  public static final int num_straight_flushes = size_straight_flush_rank * num_straight_flush_ranks;
  public static final int num_four_of_a_kinds = size_four_of_a_kind_rank * num_four_of_a_kind_ranks;
  public static final int num_full_houses = size_full_house_rank * num_full_house_ranks;
  public static final int num_flushes = size_flush_rank * num_flush_ranks;
  public static final int num_straights = size_straight_rank * num_straight_ranks;
  public static final int num_three_of_a_kinds = size_three_of_a_kind_rank * num_three_of_a_kind_ranks;
  public static final int num_two_pairs = size_two_pairs_rank * num_two_pairs_ranks;
  public static final int num_one_pairs = size_one_pair_rank * num_one_pair_ranks;
  public static final int num_high_cards = size_high_card_rank * num_high_card_ranks;

  // Access for a given major rank to its size via an array:
  public static final int[] size_major_ranks = {0, num_straight_flushes, num_four_of_a_kinds, num_full_houses, num_flushes, num_straights, num_three_of_a_kinds, num_two_pairs, num_one_pairs, num_high_cards};

  // The number of hands up to a given major rank:
  public static final int cum_num_straight_flushes = num_straight_flushes;
  public static final int cum_num_four_of_a_kinds = cum_num_straight_flushes + num_four_of_a_kinds;
  public static final int cum_num_full_houses = cum_num_four_of_a_kinds + num_full_houses;
  public static final int cum_num_flushes = cum_num_full_houses + num_flushes;
  public static final int cum_num_straights = cum_num_flushes + num_straights;
  public static final int cum_num_three_of_a_kinds = cum_num_straights + num_three_of_a_kinds;
  public static final int cum_num_two_pairs = cum_num_three_of_a_kinds + num_two_pairs;
  public static final int cum_num_one_pairs = cum_num_two_pairs + num_one_pairs;
  public static final int cum_num_high_cards = cum_num_one_pairs + num_high_cards;

  // Access for a given major rank to the cumulated sizes via an array:
  public static final int[] cumulated_size_major_ranks = {0, cum_num_straight_flushes, cum_num_four_of_a_kinds, cum_num_full_houses, cum_num_flushes, cum_num_straights, cum_num_three_of_a_kinds, cum_num_two_pairs, cum_num_one_pairs, cum_num_high_cards};


  public static final String[] major_hand_rank_names = {"Straight flush", "Four of a kind", "Full house", "Flush", "Straight", "Three of a kind", "Two pairs", "One pair", "High card"};


  public static boolean valid_hand_rank(final int r) {
    return r >= 1 && r <= num_hand_ranks;
  }

  public static int major_rank_(final int r) {
    assert valid_hand_rank(r);
    for (int i = 1; i <= num_major_hand_ranks; ++i)
      if (r <= cumulated_num_ranks[i]) return i;
    return -1; // for the compiler; won't be executed for valid r
  }

  // Analyses a hand and returns an array with major-rank and minor-rank:
  public static int[] hand_rank(final Hand h) {
    int[] result = new int[2];
    int[] rank_count = new int[CardRank.num_ranks];
    for (int i = 1; i <= Hand.hand_size; ++i)
      ++rank_count[h.get(i).rank.index];
    int[] count_of_counts = new int[Suit.num_suites+1];
    for (int i = 0; i < CardRank.num_ranks; ++i)
      ++count_of_counts[rank_count[i]];
    if (count_of_counts[4] == 1) { // four of a kind
      result[0] = four_of_a_kind;
      final int rank_quad = h.get(2).rank.index;
      result[1] = rank_quad + 1;
      return result;
    }
    if (count_of_counts[3] == 1) {
      final int rank_triple = h.get(3).rank.index;
      result[1] = rank_triple + 1;
      if (count_of_counts[2] == 1) { // full house
        result[0] = full_house;
        return result;
      }
      else { // three of a kind
        result[0] = three_of_a_kind;
        return result;
      }
    }
    if (count_of_counts[2] == 2) { // two pairs
      result[0] = two_pairs;
      int[] ranks = new int[3];
      transfer_ranks(rank_count, ranks);
      result[1] = lex_order(ranks[0],ranks[1],ranks[2]);
      return result;
    }
    if (count_of_counts[2] == 1) { // one pair
      result[0] = one_pair;
      int[] ranks = new int[4];
      transfer_ranks(rank_count, ranks);
      result[1] = lex_order(ranks[0],ranks[1],ranks[2],ranks[3]);
      return result;
    }
    if (is_straight(h)) {
      if (h.get(1).rank.index == CardRank.ace && h.get(2).rank.index == CardRank.five)
        result[1] = CardRank.five + 1;
      else
        result[1] = h.get(1).rank.index + 1;
      if (is_flush(h))
        result[0] = straight_flush;
      else
        result[0] = straight;
      return result;
    }
    else {
      int[] ranks = new int[5];
      transfer_ranks(rank_count, ranks);
      result[1] = lex_order(ranks[0],ranks[1],ranks[2],ranks[3],ranks[4]);
      result[1] -= h.get(1).rank.index+1;
      if (h.get(1).rank.index != CardRank.ace) // subtracting the low-ace-case
        --result[1];
      if (is_flush(h))
        result[0] = flush;
      else
        result[0] = high_card;
      return result;
    }
  }


  HandRank(final int r) {
    assert valid_hand_rank(r);
    rank = r;
    major_rank = major_rank_(rank);
    minor_rank = r - cumulated_num_ranks[major_rank-1];
  }

  HandRank(final Hand h) {
    final int[] hr = hand_rank(h);
    major_rank = hr[0];
    minor_rank = hr[1];
    rank = cumulated_num_ranks[major_rank-1] + minor_rank;
  }

  public final int rank;
  public final int major_rank;
  public final int minor_rank;

  public String toString() {
    return "Major rank: " + major_hand_rank_names[major_rank-1] + "\nMinor rank: " + minor_rank + "\nRank: " + rank;
  }

  public boolean equals(final HandRank hr) {
    return hr.rank == rank;
  }
        

  // The probability that a (strictly) better hand than the given hand
  // occurs for a random hand:
  public double cumulated_probability() {
    return (double) cumulated_count() / Hand.num_hands;
  }
  // The number of (strictly) better hands (than the given hand):
  public int cumulated_count() {
    return cumulated_size_major_ranks[major_rank-1] + (minor_rank-1) * size_ranks[major_rank];
  }

  public static boolean is_flush(final Hand h) {
    final Suit first = h.get(1).suit;
    for (int i = 2; i <= 5; ++i)
      if (! h.get(i).suit.equals(first))
        return false;
    return true;
  }
  public static boolean is_straight(final Hand h) {
    if (h.get(1).rank.index == CardRank.ace && h.get(2).rank.index == CardRank.five && h.get(5).rank.index == CardRank.two)
      return true;
    else if (h.get(1).rank.index + 5 - 1 == h.get(5).rank.index)
      return true;
    else
      return false;
  }

  // Transfer the ranks from rank_count to ranks, where rank_count[j] > 0
  // means that rank j is present, and will be entered into the ordered list
  // "ranks" of ranks:
  private static void transfer_ranks(final int[] rank_count, final int[] ranks) {
    int i = 0;
    for (int j = 0; j < CardRank.num_ranks; ++j)
      if (rank_count[j] > 0) {
        ranks[i] = j;
        ++i;
      }
  }

  // Functions for ranking subsets S of {0,1,...,12} for sizes 3,4,5;
  // the elements of S are given by x1 < ... < x5:
  private static int lex_order(final int x1, final int x2, final int x3) {
    return 274+(-(10-x1)*(11-x1)*(12-x1))/6-(11-x2)*(12-x2)/2+x3;
  }
  private static int lex_order(final int x1, final int x2, final int x3, final int x4) {
    return 703+(-(9-x1)*(10-x1)*(11-x1)*(12-x1))/24+(-(10-x2)*(11-x2)*(12-x2))/6-(11-x3)*(12-x3)/2+x4;
  }
  private static int lex_order(final int x1, final int x2, final int x3, final int x4, final int x5) {
    return 1275+(-(8-x1)*(9-x1)*(10-x1)*(11-x1)*(12-x1))/120+(-(9-x2)*(10-x2)*(11-x2)*(12-x2))/24+(-(10-x3)*(11-x3)*(12-x3))/6-(11-x4)*(12-x4)/2+x5;
  }

  // Tests (run by "java -ea HandRank", enabling assertions):
  public static void main(String[] args) {
    assert num_hand_ranks == 3614;
    assert num_straight_flushes == 40;
    assert num_four_of_a_kinds == 624;
    assert num_full_houses == 3744;
    assert num_flushes == 5108;
    assert num_straights == 10200;
    assert num_three_of_a_kinds == 54912;
    assert num_two_pairs == 123552;
    assert num_one_pairs == 1098240;
    assert num_high_cards == 1302540;
    assert cum_num_high_cards == Hand.num_hands;

    // Testing the various functions for ranking subsets according to
    // lexicographical order:
    assert lex_order(0,1,2) == 1;
    assert lex_order(10,11,12) == 286;
    assert lex_order(0,1,2,3) == 1;
    assert lex_order(9,10,11,12) == 715;
    assert lex_order(0,1,2,3,4) == 1;
    assert lex_order(8,9,10,11,12) == 1277 + 10;

    // Running through all possible hands, and detemining the ranks:
    int[] statistics_ranks = new int[num_hand_ranks+1];
    int[] statistics_major_ranks = new int[num_major_hand_ranks+1];
    for (int c1 = 0; c1 < Card.num_cards - 4; ++c1) {
      final Card C1 = new Card(c1);
      for (int c2 = c1+1; c2 < Card.num_cards - 3; ++c2) {
        final Card C2 = new Card(c2);
        for (int c3 = c2+1; c3 < Card.num_cards - 2; ++c3) {
          final Card C3 = new Card(c3);
          for (int c4 = c3+1; c4 < Card.num_cards - 1; ++c4) {
            final Card C4 = new Card(c4);
            for (int c5 = c4+1; c5 < Card.num_cards; ++c5) {
              final Card C5 = new Card(c5);
              final Hand h = new Hand(C1,C2,C3,C4,C5);
              final HandRank hr = new HandRank(h);
              ++statistics_ranks[hr.rank];
              ++statistics_major_ranks[hr.major_rank];
            }
          }
        }
      }
    }
    statistics_major_ranks[0] = 0;
    for (int i = 0; i <= num_major_hand_ranks; ++i)
      assert statistics_major_ranks[i] == size_major_ranks[i];
    for (int rank = 1; rank <= num_hand_ranks; ++rank) {
      final HandRank hr = new HandRank(rank);
      assert statistics_ranks[rank] == size_ranks[hr.major_rank];
    }
  }
}
