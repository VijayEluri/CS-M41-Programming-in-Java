// Oliver Kullmann, 28.12.2009 (Swansea)

/* Remarks:
  - Error checking not completed.
*/


class ModularAddition {
  public static void main(final String[] args) {
    if (args.length != 3) {
      System.err.print("Precisely three arguments are needed, but you used ");
      System.err.println(args.length + " argument(s).");
      System.exit(1);
    }
    final int n = Integer.parseInt(args[2]);
    final int a = Integer.parseInt(args[0]) % n;
    final int b = Integer.parseInt(args[1]) % n;
    System.out.println((a + b) % n);
  }
}
