public class Poker {

    static final int num_suites = 4;
    static final int num_ranks = 13;
    static final int num_cards = num_suites * num_ranks;
    static final int hand_size = 5;
    static final int num_hand_ranks = 9;

    static final String[] suit_names = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static final String[] rank_names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    static final String[] hand_rank_names = {"Straight flush", "Four of a kind", "Full house", "Flush", "Straight", "Three of a kind", "Two pairs", "One pair", "High card"};

    static int parse_suit(final String s) {
        for (int i = 0; i < num_suites; ++i)
            if (s.equals(suit_names[i])) return i;
        return -1;
    }
    static int parse_rank(final String s) {
        for (int i = 0; i < num_ranks; ++i)
            if (s.equals(rank_names[i])) return i;
        return -1;
    }

    static int suit(final int card) {
        return card / num_ranks;
    }
    static int rank(final int card) {
        return card % num_ranks;
    }

    static int card(final int rank, final int suit) {
        return suit * num_ranks + rank;
    }

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

    static boolean check_all_different(final int[] hand) {
        for (int i = 0; i < hand_size; ++i)
            for (int j = i+1; j < hand_size; ++j)
                if (hand[i] == hand[j]) return false;
        return true;
    }

    static boolean flush(final int[] hand) {
        final int first_suit = suit(hand[0]);
        for (int i = 1; i < hand_size; ++i)
            if (suit(hand[i]) != first_suit) return false;
        return true;
    }
    static boolean straight(final int[] hand) {
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

    static int hand_rank(final int[] hand) {
        int[] rank_count = new int[num_cards];
        for (int i = 0; i < hand_size; ++i)
            ++rank_count[rank(hand[i])];
        int[] count_of_counts = new int[num_suites+1];
        for (int i = 0; i < num_cards; ++i)
            ++count_of_counts[rank_count[i]];
        if (count_of_counts[4] == 1) return 2;
        if (count_of_counts[3] == 1)
            if (count_of_counts[2] == 1) return 3;
            else return 6;
        if (count_of_counts[2] == 2) return 7;
        if (count_of_counts[2] == 1) return 8;
        final boolean is_flush = flush(hand);
        final boolean is_straight = straight(hand);
        if (is_flush && is_straight) return 1;
        else if (is_flush) return 4;
        else if (is_straight) return 5;
        else return 9;
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
