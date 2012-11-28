class CountingChars {
  public static String[] copy_and_double(final String[] a) {
    if (a == null || a.length == 0) return null;
    final String[] res = new String[2*a.length];
    for (int i = 0; i < a.length; ++i) res[i] = a[i];
    return res;
  }
  public static String[] read() {
    String[] res = new String[1];
    int length = 1;
    int count = 0;
    while (! StdIn.isEmpty()) {
      assert(res.length == length);
      assert(count < length);
      res[count++] = StdIn.readString();
      if (count == length) {
        res = copy_and_double(res);
        length *= 2;
      }
    }
    return res;
  }
  public static int count_words(final String[] s) {
    if (s == null) return 0;
    int count = 0;
    for (int i = 0; i < s.length && s[i] != null; ++i, ++ count);
    return count;
  }
  public static int[] count_chars(final String[] s) {
    if (s == null) return null;
    final int number_chars = 65536;
    int[] res = new int[number_chars];
    for (int i = 0; i < s.length && s[i] != null; ++i) {
      final String str = s[i];
      for (int j = 0; j < str.length(); ++j) ++res[str.charAt(j)];
    }
    return res;
  }
  public static void output(final int[] count) {
    if (count == null) return;
    for (int i = 0; i < count.length; ++i)
      if (count[i] != 0)
        System.out.println("\"" + ((char) i) + "\" (" + i + "): " + count[i]);
  }
  public static void main(final String[] args) {
    final String[] text = read();
    System.out.println(count_words(text));
    output(count_chars(text));
  }
}
