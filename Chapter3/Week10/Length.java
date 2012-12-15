class Length {
  public static void main(final String[] args) {
    final String s = "abc";
    assert(s.length() == 3);
    final int[] a = {1,2,3};
    assert(a.length == 3);
        
    /* Array-objects and String-objects have *fixed sizes*, so both classes could hold
       *final* instance-variables with the length. (So changing size can only happen via
       a new object; elements can be changed, but not the whole thing.)
       The Array class exposes that data-member directly, which is fine since it's *final*.
       The String class puts a wrapper around that data-member --- this wouldn't be needed,
       however it's also natural, since the implementation of String just uses an array a, and
       thus String.length() just shows a.length, and this happens via a function.
    */
  }
}
