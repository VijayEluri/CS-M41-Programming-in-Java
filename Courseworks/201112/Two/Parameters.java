// Oliver Kullmann, 21.11.2011 (Swansea)

class Parameters {

  public static final String error_header = "ERROR[" + GeneralisedTicTacToe.program_name + "]: ";
  public static final String message_no_param = "The parameters are k, m, n " +
    "and one of \"hh\", \"hc\", \"ch\", \"cc\".";
  public static final String message_num_param = "Four parameters are needed, not ";
  public static final String message_parsing = "The first three parameters must be integers.";
  public static final String message_mode = "The fourth parameter must be one of \"hh\", \"hc\", \"ch\", \"cc\".";

  /*
    In case of errors, error-messages are output to System.err and
    null is returned.
    Otherwise an integer-array of length 4 is returned, containing
    k, m, n, and the code for the mode.
    No exception shall be thrown by this function.
  */
  public static int[] process_parameters(final String[] args) {
    assert(args != null);
    if (args.length == 0) {
      error_message(message_no_param);
      return null;
    }
    if (args.length != 4) {
      error_message(message_num_param + args.length + ".");
      return null;
    }
    int K,M,N;
    try {
      K = Integer.parseInt(args[0]);
      M = Integer.parseInt(args[1]);
      N = Integer.parseInt(args[2]);
    }
    catch (final Exception e) {
      error_message(message_parsing);
      return null;
    }
    int m = 0;
    if (args[3].equals("hh")) m = GeneralisedTicTacToe.mode_hh;
    else if (args[3].equals("hc")) m = GeneralisedTicTacToe.mode_hc;
    else if (args[3].equals("ch")) m = GeneralisedTicTacToe.mode_ch;
    else if (args[3].equals("cc")) m = GeneralisedTicTacToe.mode_cc;
    if (m == 0) {
      error_message(message_mode);
      return null;
    }
    int[] result = new int[4];
    result[0] = K; result[1] = M; result[2] = N; result[3] = m;
    return result;
  }

  private static void error_message(final String m) {
    System.err.println(error_header + m);
  }

}
