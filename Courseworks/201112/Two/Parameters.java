// Oliver Kullmann, 21.11.2011 (Swansea)

class Parameters {

  public static final String error_header = "ERROR[" + GeneralisedTicTacToe.program_name + "]: ";
  public static final String message_no_param = "The parameters are k, m, n " +
    "and one of \"hh\", \"hc\", \"ch\", \"cc\".";

  /*
    In case of errors, error-messages are output to System.err and
    null is returned.
    Otherwise an integer-array of length 4 is returned, containing
    k, m, n, and the code for the mode.
    No exception shall be thrown by this function.
  */
  public static int[] process_parameters(final String[] args) {
    assert(args != null);
    int[] result = null;
    if (args.length == 0) {
      error_message(message_no_param);
      return result;
    }

    // XXX to be completed XXX
    if (args[3] == "hh") result[3] = GeneralisedTicTacToe.mode_hh;
    // XXX to be completed XXX

    return result;
  }

  private static void error_message(final String m) {
    System.err.println(error_header + m);
  }

}
