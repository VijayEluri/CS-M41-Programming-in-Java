// Oliver Kullmann, 18.10.2011 (Swansea)

public class IntOpsI {
  public static void main(final String[] args) {
    if (args.length < 2) {
      System.err.print("ERROR[IntOps]: We need two arguments, ");
      System.err.println("but you entered only " + args.length + " many arguments.");
    }
    else {
      final int a = Integer.parseInt(args[0]);
      final int b = Integer.parseInt(args[1]);
      final int p = a*b;
      System.out.println(a + " * " + b + " = " + p);
      if (b == 0) {
        System.out.println("No division, since b = 0.");
       }
      else {
        final int q = a/b;
        final int r = a%b;
        System.out.println(a + " / " + b + " = " + q);
        System.out.println(a + " % " + b + " = " + r);
        System.out.println(a + " = " + q + " * " + b + " + " + r);
      }
    }
  }
}
