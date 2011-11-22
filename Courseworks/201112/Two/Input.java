// Oliver Kullmann, 21.11.2011 (Swansea)

class Input {

  // returns an array of length 2, with two valid coordinates,
  // or null the end of standard input was encountered; does not throw any
  // exception:
  public static int[] read_move(final int M, final int N) {
    boolean waiting = true;
    int i=0,j=0;
    while (waiting) {
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
    }
    int[] result = new int[2];
    result[0] = i; result[1] = j;
    return result;
  }
}
