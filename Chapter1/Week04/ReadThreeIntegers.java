// Oliver Kullmann, 14.12.2009 (Swansea)

/* Remarks:
    - This solution uses no assignments; with assignments it gets a
      bit easier.
    - A better solution checks the number of arguments.
*/

class ReadThreeIntegers {
  public static void main(final String[] args) {
    final int a = Integer.parseInt(args[0]);
    final int b = Integer.parseInt(args[1]);
    final int c = Integer.parseInt(args[2]);
    if (a < b)
      if (b < c)
        System.out.println(a + " " + b + " " + c);
      else if (a < c)
        System.out.println(a + " " + c + " " + b);
      else
        System.out.println(c + " " + a + " " + b);
    else
      if (a < c)
        System.out.println(b + " " + a + " " + c);
      else if (b < c)
        System.out.println(b + " " + c + " " + a);
      else
        System.out.println(c + " " + b + " " + a);
  }
}
