class Concatenate {
  public static String concatenate(final String[] s) {
    String res = "";
    if (s == null) return res;
    for (int i = 0; i < s.length; ++i) res += s[i];
    return res;
  }
  public static String concatenate_spaces(final String[] s) {
    String res = "\"";
    if (s == null) return res;
    for (int i = 0; i < s.length-1; ++i) res += s[i] + " ";
    if (s.length != 0) res += s[s.length-1];
    res += "\"";
    return res;
  }
  public static void main(final String[] args) {
    System.out.println(concatenate(args));
    System.out.println(concatenate_spaces(args));
  }
}
