public class CreateArray {

  XXX create(XXX) {
    XXX
  }

  public static void main(final String[] args) {
    final int[] a = create(Integer.parseInt(args[0]));
    for (int i = 0; i < a.length; ++i)
      System.out.print(a[i] + " ");
    System.out.println();
  }
}
