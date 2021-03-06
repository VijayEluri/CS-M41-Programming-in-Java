// Oliver Kullmann, 21.11.2011 (Swansea)

/*
  Call by

> java GeneralisedTicTacToe K M N s

  to play "K in a row on an M x N board", where

  - K >= 1 is the size of the "row" to be filled with own marks
  - M is the number of rows of the rectangular board
  - N is the number of columns of the rectangular board.

  In "K in a row" a "row" means actually a "line", that is a vertically,
  horizontally or diagonally connected sequence of k cells of the board.

  After printing out basic information, moves are read from standard input,
  alternatingly for the two players, starting with the first player.
  A move is given by two natural numbers (separated by space symbols) i, j,
  with 1 <= i <= M and 1 <= j <= N.

  The string s can be one of
  - "hh" : human against human
  - "hc" : human against computer
  - "ch" : computer against human
  - "cc" : computer against computer.
*/

class GeneralisedTicTacToe {

  public static final String program_name = "GeneralisedTicTacToe";
  public static final String message_start = "Starting " + program_name + ".";
  public static final String message_R = "Number of rows: ";
  public static final String message_occ = "Occurrences:";
  public static final String message_game = "The game begins.";
  public static final String message_mv_expected_1 = "Player I has to move.";
  public static final String message_mv_expected_2 = "Player II has to move.";
  public static final String message_win_1 = "The first player wins.";
  public static final String message_win_2 = "The second player wins.";
  public static final String message_draw = "Game ended with draw.";
  public static final String message_interrupt = "Game was interrupted.";
  public static final String message_error_exit = "Exiting after input errors.";
  public static final String message_not_implemented = "Functionality not implemented.";
  public static final String message_win1_always = "The first player will always win after the first move.";
  public static final String message_no_win = "Every game must end in a draw.";
  public static final String message_move_1 = "Move of player I: ";
  public static final String message_move_2 = "Move of player II: ";
  public static final String message_max_occupation = "Maximal occupation number of new cell: ";
  public static final String message_output_field = "The final position is:";
  public static final String message_output_movelist = "The complete list of moves is:";

  public static final int mode_hh = 1;
  public static final int mode_hc = 2;
  public static final int mode_ch = 3;
  public static final int mode_cc = 4;


  public static void main(final String[] args) {
    System.out.println(message_start);

    // Parameter reading

    final int[] parameters = Parameters.process_parameters(args);
    if (parameters == null) {
      System.err.println(message_error_exit);
      System.exit(1);
    }
    assert(parameters.length == 4);
    final int K = parameters[0];
    assert(K >= 1);
    final int M = parameters[1];
    assert(M >= 1);
    final int N = parameters[2];
    assert(N >= 1);
    final int mode = parameters[3];
    assert(mode == mode_hh || mode == mode_hc || mode == mode_ch || mode == mode_cc);

    // Handling of trivial cases

    if (K == 1) {
      System.out.println(message_win1_always);
      return;
    }
    if (M < K && N < K) {
      System.out.println(message_no_win);
      return;
    }

    // Preparing fundamental data and data structures

    final int number_cells = M*N;
    final int R = Counting.number_rows(K,M,N);
    assert(R >= 1);
    System.out.println(message_R + R);

    final int[][] field = Field.empty_field(M,N);
    assert(field.length == M);
    assert(field[0].length == N);
    assert(Field.valid_field(field));

    final int[][] move_list = new int[number_cells][2];
    final int[] move_index = new int[1];
    move_index[0] = 0;
    assert(move_list[move_index[0]][0] == 0);
    assert(move_list[move_index[0]][1] == 0);

    final int[][][] rows = Rows.list_rows(K,M,N,R);
    assert(rows.length == R);
    assert(Rows.check_list_rows(rows, field));

    final int[][][] occurrences = Occurrences.occurrences_field(rows, field);
    assert(occurrences.length == M);
    assert(occurrences[0].length == N);
    assert(Occurrences.consistency_check(rows, occurrences));
    System.out.println(message_occ);
    Occurrences.show_occurrence_counts(occurrences);

    final int[][] occupation = Occupation.occupation_vector(R);
    assert(Occupation.valid_occupation(occupation, K));
    assert(occupation[0][0] == 0);
    assert(occupation[0][1] == 0);

    // Reading resp. computing the moves

    System.out.println(message_game); System.out.println();
    if (mode != mode_hh) {
      System.out.println(message_not_implemented);
      return;
    }
    Field.output_field(field);
    boolean first_player_moves = true;
    boolean draw = true;
    for (; move_index[0] < number_cells; first_player_moves = ! first_player_moves) {
      int move_i = 0, move_j = 0;
      boolean interrupt = false;
      if (first_player_moves) System.out.println(message_mv_expected_1);
      else System.out.println(message_mv_expected_2);
      do {
        final int[] reading = Input.read_move(M,N);
        if (reading == null) { interrupt = true; break; }
        move_i = reading[0]; move_j = reading[1];
      } while (! Field.valid_move(field,move_i,move_j));
      if (interrupt) break;
      if (first_player_moves) System.out.print(message_move_1);
      else System.out.print(message_move_2);
      System.out.println(move_i + " " + move_j);
      final int max = Field.enter_move(field, move_i, move_j, first_player_moves, move_list, move_index, occurrences, occupation);
      Field.output_field(field);
      System.out.println(message_max_occupation + max);
      if (max == K) {
        draw = false;
        break;
      }
    }

    // Result

    System.out.println();
    if (draw)
      if (move_index[0] == number_cells) System.out.println(message_draw);
      else System.out.println(message_interrupt);
    else if (first_player_moves) System.out.println(message_win_1);
    else System.out.println(message_win_2);

    // Final output

    System.out.println(message_output_field);
    Field.output_field(field);
    System.out.println(message_output_movelist);
    Field.output_movelist(move_list, move_index[0]);
  }
}
