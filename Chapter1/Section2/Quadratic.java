// Oliver Kullmann, 22.9.2009 (Swansea)

public class Quadratic {
  public static void main(final String[] args) {
    try {
      final double b = Double.parseDouble(args[0]);
      final double c = Double.parseDouble(args[1]);
      System.out.println("b = " + b);
      System.out.println("c = " + c);
      // ATTENTION: value Infinity is possible

      final double discriminant = b*b - 4*c;
      if (discriminant < 0)
        System.out.println("No real roots.");
      else {
        final double d = Math.sqrt(discriminant);
        final double root1 = (-b+d)/2;
        final double root2 = (-b-d)/2;
        System.out.println("first root  = " + root1);
        System.out.println("second root = " + root2);
      }
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.err.println("ERROR[Quadratic]: Two parameter values are expected, the coefficients b and c.");
    }
    catch(RuntimeException e) {
      System.err.println("ERROR[Quadratic]: The two parameters must be floating point numbers.");
    }
  }
}
