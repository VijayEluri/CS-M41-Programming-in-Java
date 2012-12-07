class ArrayNull {
  public static void main(final String[] args) {
    final int[][] a = new int[1][]; // an array of arrays
    assert(a != null); // only now we can access a.length !
    assert(a.length == 1); // only now we can access a[0] !
    assert(a[0] == null); // thus don't use a[0].length !
    System.out.println(a[0]);
    
    a[0] = new int[3];
    assert(a[0] != null);
    System.out.println(a[0]);
    assert(a[0].length == 3);
    final int[] b = a[0];
    for (int i = 0; i < b.length; ++i)
      assert(b[i] == 0);
  }
}
