// Oliver Kullmann, 15/10/2010 (Swansea)

public class LeftAssociative {
  public static void main(final String[] args) {
    final int a = Integer.parseInt(args[0]);
    final int b = Integer.parseInt(args[1]);
    System.out.println(a + " + " + b + " = " + a + b); // not what is meant
    System.out.println(a + " + " + b + " = " + (a + b));
    System.out.println(a + b + " + " + "13" + " = " + (a + b + 13)); // understand this
  }
}
