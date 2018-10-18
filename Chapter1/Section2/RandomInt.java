// Oliver Kullmann, 23.9.2009 (Swansea)

public class RandomInt {
  public static void main(final String[] args) {

    try {
      final int N = Integer.parseInt(args[0]);
      System.out.println("N = " + N);
      final int n = (int) (Math.random() * N);
      System.out.println(n);
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.err.println("ERROR[RandomInt]: One parameter values is needed, an integer.");
    }
    catch(RuntimeException e) {
      System.err.println("ERROR[RandomInt]: The parameter must be an integer within the range from -2147483648 to 2147483647.");
    }
  }
}
