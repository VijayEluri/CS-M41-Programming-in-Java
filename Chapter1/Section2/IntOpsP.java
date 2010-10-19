// Oliver Kullmann, 22.9.2009 (Swansea)

// IntOpsP is a complete program, which reads two integers from the command
// line, and prints out their product, integer quotient and remainder.

// IntOpsP is the improved version of IntOps, now handling all errors
// ("P" is for "perfect").

// Version = 1.0

public class IntOpsP {
  // Error codes:
  public static final int err_num_par = 1, err_not_int = 2;
  public static void main(final String[] args) {
    if (args.length <= 1) {
      System.err.println("ERROR[IntOps]: Two arguments are expected (namely two numbers).");
      System.exit(err_num_par);
    }
    try { // catching parsing errors
      // Parsing input:
      final int a = Integer.parseInt(args[0]);
      final int b = Integer.parseInt(args[1]);
      // Computing product, quotient and remainder:
      final double pd = (double) a*b; // in double two integers can be multiplied
      final boolean overflow = (pd > Integer.MAX_VALUE) || (pd < Integer.MIN_VALUE);
      final int p = (! overflow) ? (int) pd : 0;
      final boolean zero_div = (b == 0);
      final int q = (! zero_div) ? a/b : 0;
      final int r = (! zero_div) ? a%b : 0;
      // Output of all values:
      if (! overflow)
        System.out.println(a + " * " + b + " = " + p);
      else {
	System.out.println("Product overflow in int!");
	System.out.println("Recall the integer range is from " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE + ".");
	System.out.println("The double result is " + pd + ".");
      }
      if (! zero_div) {
	System.out.println(a + " / " + b + " = " + q);
	System.out.println(a + " % " + b + " = " + r);
	System.out.println(a + " = " + q + " * " + b + " + " + r);
      }
	else
	System.out.println("No division, since divisor is zero.");
    }
    catch (final Exception e) {
      System.err.println("ERROR[IntOps]: The two parameters must be integers!");
      System.exit(err_not_int);
    }
  }
}
