// Oliver Kullmann, 21.11.2011 (Swansea)

class GeneralisedTicTacToe {

  public static final String program_name = "GeneralisedTicTacToe";
  public static final String message_start = "Starting " + program_name + ".";
  public static final String message_win_1 = "The first player wins.";
  public static final String message_win_2 = "The second player wins.";
  public static final String message_error_exit = "Exiting after input errors.";
  public static final String message_not_implemented = "Functionality not implemented.";
  public static final String message_win1_always = "The first player will always win after the first move.";
  public static final String message_no_win = "Every game must end in a draw.";
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

    final int[][] occupation = Occupation.occupation_vector(R);
    assert(Occupation.valid_occupation(occupation, K));
    assert(occupation[0][0] == 0);
    assert(occupation[0][1] == 0);

    // Reading resp. computing the moves

    // XXX to be completed XXX
    System.out.println(message_not_implemented); // yet nothing implemented
    boolean first_player_moves = true;
    // XXX to be completed XXX

    // Final output

    System.out.println(message_output_field);
    Field.output_field(field);
    System.out.println(message_output_movelist);
    Field.output_movelist(move_list);
  }
}
