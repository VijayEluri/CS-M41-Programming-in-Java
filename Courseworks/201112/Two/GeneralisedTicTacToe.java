// Oliver Kullmann, 21.11.2011 (Swansea)

class GeneralisedTicTacToe {

  public static final String program_name = "GeneralisedTicTacToe";
  public static final String message_start = "Starting " + program_name + ".";
  public static final String message_win_1 = "The first player wins.";
  public static final String message_win_2 = "The second player wins.";
  public static final String message_error_exit = "Exiting after input errors.";
  public static final String message_not_implemented = "Functionality not implemented.";

  public static final int number_parameters = 4;

  public static final int mode_hh = 1;
  public static final int mode_hc = 2;
  public static final int mode_ch = 3;
  public static final int mode_cc = 4;


  public static void main(final String[] args) {
    System.out.println(message_start);

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

    // XXX to be completed XXX

    System.out.println(message_not_implemented); // yet nothing implemented
    return;
  }
}
