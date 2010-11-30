// Oliver Kullmann, 29.11.2010 (Swansea)

/* Usage:

     Shuffle N

   where N is a non-negative integer, will create a random permutation of
   the sequence 0, ..., N-1, and prints it to standard output.
*/

class Shuffle {

  public static final int
    error_missing_argument = 1,
    error_not_an_int = 2,
    error_negative_number = 3;
  static final String error_header = "ERROR[Shuffle]: ";

  public static void main(final String[] args) {
    if (args.length == 0) {
      System.err.println(error_header + "One argument is needed (the number N of integers to be shuffled).");
      System.exit(error_missing_argument);
    }
    try {
      final int N = Integer.parseInt(args[0]);
      if (N < 0) {
        System.err.println(error_header + "The number N of items must not be negative.");
        System.exit(error_negative_number);
      }
      final int[] A = new int[N];
      for (int i = 1; i < N; ++i) A[i] = i;
      // shuffling:
      for (int i = 0; i < N; ++i) {
        final int rand = i + (int) (Math.random() * (N-i));
        final int t = A[rand];
        A[rand] = A[i];
        A[i] = t;
      }
      for (int i = 0; i < N; ++i) System.out.print(A[i] + " ");
      System.out.println();
    }
    catch (final RuntimeException e) {
      System.err.println(error_header + "The command-line argument \"" + args[0] + "\" is not an integer.");
      System.exit(error_not_an_int);
    }
  }
}
