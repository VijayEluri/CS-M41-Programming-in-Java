// Oliver Kullmann, 21.11.2011 (Swansea)

/*
  Provides function read_move(M,N):
  - returns (in an array), after successful reading from standard input, the
    two valid coordinates for a move;
  - invalid inputs are ignored (in pairs);
  - if the end of standard input is encountered, null is returned.
*/

class Input {

  public static final String message_invalid = "Invalid input.";

  // returns an array of length 2, with two valid coordinates,
  // or null if the end of standard input was encountered; does not throw any
  // exceptions:
  public static int[] read_move(final int M, final int N) {
    boolean waiting = true;
    int i=0, j=0;
    do {
      if (StdIn.isEmpty()) return null;
      final String inp_i = StdIn.readString();
      if (StdIn.isEmpty()) return null;
      final String inp_j = StdIn.readString();
      try {
        i = Integer.parseInt(inp_i);
        j = Integer.parseInt(inp_j);
        waiting = false;
      }
      catch (final Exception e) {}
      if (! waiting)
        if (i < 1 || i > M || j < 1 || j > N) waiting = true;
      if (waiting) System.out.println(message_invalid);
    } while (waiting);
    int[] result = new int[2];
    result[0] = i; result[1] = j;
    return result;
  }
}
