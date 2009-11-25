public class Poker {

    // Suites are represented by integers 0,...,3,
    // card ranks by integers 0,...,12, and cards by integers 0,...,51.
    // And hand ranks are represented by integer 1,...,9 (from highest
    // to lowest).

    static final int num_suites = 4;
    static final int num_ranks = 13;
    static final int num_cards = num_suites * num_ranks;
    static final int hand_size = 5;
    static final int num_hand_ranks = 9;

    // The integer-representation of suites and ranks are given as indices of
    // the following arrays:
    static final String[] suit_names = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static final String[] rank_names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    // The string representation of hand ranks:
    static final String[] hand_rank_names = {"Straight flush", "Four of a kind", "Full house", "Flush", "Straight", "Three of a kind", "Two pairs", "One pair", "High card"};
    // Explicit constants for the 9 hand ranks:
    static final int straight_flush = 1;
    static final int four_of_a_kind = 2;
    static final int full_house = 3;
    static final int flush = 4;
    static final int straight = 5;
    static final int three_of_a_kind = 6;
    static final int two_pairs = 7;
    static final int one_pair = 8;
    static final int high_card = 9;

    // Converts a string into a suit; returns -1, if the string doesn't
    // represent a suite:
    static int parse_suit(final String s) {
        for (int i = 0; i < num_suites; ++i)
            if (s.equals(suit_names[i])) return i;
        return -1;
    }
    // Converts a string into a card-rank; returns -1, if the string doesn't
    // represent a card-rank:
    static int parse_rank(final String s) {
        for (int i = 0; i < num_ranks; ++i)
            if (s.equals(rank_names[i])) return i;
        return -1;
    }

    // For a card, compute its suit:
    static int suit(final int card) {
        return card / num_ranks;
    }
    // For a card, compute its rank:
    static int rank(final int card) {
        return card % num_ranks;
    }

    // Given card-rank and suit, compute the corresponding card:
    static int card(final int rank, final int suit) {
        return suit * num_ranks + rank;
    }

    // Read a hand (and array of cards) from standard-input; returns null
    // if some parsing error occurs:
    static int[] read_hand() {
        int[] hand = new int[hand_size];
        for (int i = 0; i < hand_size; ++i) {
            final int rank = parse_rank(StdIn.readString());
            if (rank == -1) return null;
            if (! StdIn.readString().equals("of")) return null;
            final int suit = parse_suit(StdIn.readString());
            if (suit == -1) return null;
            hand[i] = card(rank, suit);
        }
        return hand;
    }

    // Creates a random hand:
    static int[] random_hand() {
        int[] hand = new int[hand_size];
        int[] cards = new int[num_cards];
        for (int i = 0; i < num_cards; ++i)
            cards[i] = i;
        for (int i = 0; i < hand_size; ++i) {
            int random_index = i + (int) (Math.random() * (num_cards - i));
            // i <= random_index < num_cards - i
            hand[i] = cards[random_index];
            cards[random_index] = cards[i];
        }
        return hand;
    }

    // Check that the cards of a hand are really all different:
    static boolean check_all_different(final int[] hand) {
        for (int i = 0; i < hand_size; ++i)
            for (int j = i+1; j < hand_size; ++j)
                if (hand[i] == hand[j]) return false;
        return true;
    }

    // Check whether a hand is a flush:
    static boolean is_flush(final int[] hand) {
        final int first_suit = suit(hand[0]);
        for (int i = 1; i < hand_size; ++i)
            if (suit(hand[i]) != first_suit) return false;
        return true;
    }
    // Check whether a hand is a straight:
    static boolean is_straight(final int[] hand) {
        boolean[] ranks = new boolean[num_ranks+1];
        // shifting ranks 0..12 to 1..13, and adding new rank 0 for card "1"
        for (int i = 0; i < hand_size; ++i)
            ranks[rank(hand[i])+1] = true;
        if (ranks[num_ranks] & ranks[1]) ranks[0] = true;
        int first = 0;
        while (! ranks[first]) ++first;
        final int num_remains = hand_size - 1;
        if (first + num_remains > num_ranks) return false;
        for (int i = first + 1; i <= first + num_remains; ++i)
            if (! ranks[i]) return false;
        return true;
    }
    /* Remark: An alternative algorithm is to first sort the hand by
       ranks, using the sort-algorithm from the Java-library. This would
       yield more compact code, however in this module we use only elements
       from the Java-library as discussed in the lectures.
    */

    // Determine the hand-rank of a hand:
    static int hand_rank(final int[] hand) {
        int[] rank_count = new int[num_ranks];
        for (int i = 0; i < hand_size; ++i)
            ++rank_count[rank(hand[i])];
        int[] count_of_counts = new int[num_suites+1];
        for (int i = 0; i < num_ranks; ++i)
            ++count_of_counts[rank_count[i]];
        if (count_of_counts[4] == 1) return four_of_a_kind;
        if (count_of_counts[3] == 1)
            if (count_of_counts[2] == 1) return full_house;
            else return three_of_a_kind;
        if (count_of_counts[2] == 2) return two_pairs;
        if (count_of_counts[2] == 1) return one_pair;
        final boolean is_flush = is_flush(hand);
        final boolean is_straight = is_straight(hand);
        if (is_flush && is_straight) return straight_flush;
        else if (is_flush) return flush;
        else if (is_straight) return straight;
        else return high_card;
    }

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
            final int[] hand = read_hand();
            if (hand == null) {
                System.err.println("ERROR[Poker]: Incorrect hand of cards.");
                return;
            }
            if (! check_all_different(hand)) {
                System.err.println("ERROR[Poker]: Two cards coincide.");
                return;
            }
            System.out.println("Hand rank = " + hand_rank_names[hand_rank(hand)-1]);
        }
        else {
            int[] counts = new int[num_hand_ranks+1];
            for (int i = 1; i <= N; ++i)
                ++counts[hand_rank(random_hand())];
            for (int hand_rank = 1; hand_rank <= num_hand_ranks; ++hand_rank)
                System.out.println(hand_rank_names[hand_rank-1] + ": " + (double) (counts[hand_rank]) / N * 100 + "%");
        }
    }
}
