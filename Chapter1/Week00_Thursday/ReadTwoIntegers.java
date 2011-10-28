// Oliver Kullmann, 14.12.2009 (Swansea)

/*
  Remarks:
   - "final" means constant.
   - The "+" as argument of println means concatenation of string.
     So println(a + " " + b) is the same as the sequence
       print(a); print(" "); println(b);
*/

class ReadTwoIntegers {
  public static void main(final String[] args) {
    final int a = Integer.parseInt(args[0]);
    final int b = Integer.parseInt(args[1]);
    if (a < b)
      System.out.println(a + " " + b);
    else
      System.out.println(b + " " + a);
  }
}
