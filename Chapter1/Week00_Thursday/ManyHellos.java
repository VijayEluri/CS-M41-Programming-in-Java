// Oliver Kullmann, 14.12.2009 (Swansea)

/*
  Remark: "x--" subtracts 1 from x, while the value of this
  expression is that of the *old* x.
*/

class ManyHellos {
  public static void main(String[] args) {
  // print 5 times using "while":
  int counter = 5;
  while (counter-- > 0)
    System.out.println("Hello, World!");
  // now printing 5 times using "for" (this is better here):
  for (int i = 1; i <= 5; ++i)
    System.out.println("Hello, World!");
  }
}
