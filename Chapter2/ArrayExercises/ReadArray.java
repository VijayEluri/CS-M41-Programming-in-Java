// Oliver Kullmann 24.11.2011 (Swansea)

class ReadArray {
  public static final String program_name = "ReadArray";
  public static final String err = "ERROR[" + program_name + "]: ";
  public static void main(final String[] args) {
    try {
      final int M = Integer.parseInt(args[0]);
      final int N = Integer.parseInt(args[1]);
      int[][] a = new int[M][N];
      for (int i = 0; i < M; ++i)
        for (int j = 0; j < N; ++j)
          a[i][j] = StdIn.readInt();
      System.out.println("Output:");
      PrintArray.print_array(a);
    }
    catch (final ArrayIndexOutOfBoundsException e) {
      System.err.println(err + "Two parameters are needed.");
    }
    catch (final NumberFormatException e) {
      System.err.println(err + "Both parameters must be integers.");
    }
    catch (final NegativeArraySizeException e) {
      System.err.println(err + "Both parameters must be non-negative.");
    }
    catch (final java.util.NoSuchElementException e) {
      System.err.println(err + "End of standard input before end of matrix.");
    }
  }
}
