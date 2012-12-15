class NullString {
  public static void main(final String[] args) {
    String s1;
    /* s1 has the value null now, however we can't check this directly,
       since then the compiler will say "not initialised" (try it out).
    */
    String s2 = null;
    System.out.println(s2);
    System.out.println(s2 == null);
    // not possible: s2.length() (null-pointer exception --- try it!)
    s1 = "a";
    /* s1 is now not null, but you can't print its memory address, since it's
       a string, whose *value* will be printed instead.
    */
    System.out.println(s1 != null);
    s1 = null;
    System.out.println(s1 == null);
    System.out.println(s1 == s2);
    s2 = ""; // that's a perfect string, not different from any other string
    System.out.println(s2 != null);
    System.out.println(s1 != s2);

    String[] a = new String[2];
    for (int i = 0; i < a.length; ++i)
      System.out.println(i + ": " + (a[i] == null));
  }
}
