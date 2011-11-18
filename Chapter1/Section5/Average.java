// Oliver Kullmann, 8/10/2009 (Swansea)

class Average { 
  public static void main(final String[] args) { 
    long N = 0;           // number input values
    double sum = 0.0;    // sum of input values

    // read data and compute statistics
    try {
      while (!StdIn.isEmpty()) {
        final double x = StdIn.readDouble();
        ++N;
        sum += x;
      }
    }
    catch (RuntimeException e) {
      StdOut.println("ERROR[Average]: Input error.");
      return;
    }

    StdOut.println();
    if (N == 0)
      StdOut.println("No input.");
    else {
      final double average = sum / N;
      StdOut.println("Average is " + average);
    }
  }
}
