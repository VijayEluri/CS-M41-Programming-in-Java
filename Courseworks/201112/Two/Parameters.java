// Oliver Kullmann, 21.11.2011 (Swansea)

/*
  Provides function process_parameters(args):
  - parses the parameters from the command line and returns them in an array;
  - in case of input errors, messages are printed on standard error, and
    null is returned.
*/

class Parameters {

  public static final String error_header = "ERROR[" + GeneralisedTicTacToe.program_name + "]: ";
  public static final String message_no_param = "The parameters are k, m, n " +
    "and one of \"hh\", \"hc\", \"ch\", \"cc\".";
  public static final String message_num_param = "Four parameters are needed, not ";
  public static final String message_parsing = "The first three parameters must be integers.";
  public static final String message_positive = "The first three parameters must be > 0.";
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
    // XXX to be completed: reading K,M,N XXX
    int m = 0;
    // XXX to be completed: reading m XXX
    int[] result = new int[4];
    result[0] = K; result[1] = M; result[2] = N; result[3] = m;
    return result;
  }

  private static void error_message(final String m) {
    System.err.println(error_header + m);
  }

}
