class Crossfoot {
  public static void main(final String[] args) {
    if (args.length != 1) {
      System.err.println("ERROR[Crossfoot]: Exactly one argument is needed.");
      System.exit(1);
    }
    final String s = args[0];
    int sum = 0;
    String extract = "";
    for (int i = 0; i < s.length(); ++i) {
      final char c = s.charAt(i);
      if (c >= '0' && c <= '9') {
        final int value = c - '0';
        sum += value;
      }
      else extract += c;
    }
    System.out.println(sum);
    System.out.println("\"" + extract + "\"");
  }
}
