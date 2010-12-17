class SplitCharacter {
  public static void main(final String[] args) {
    if (args.length != 1) {
      System.err.println("ERROR[SplitCharacter]: Exactly one argument is needed.");
      System.exit(1);
    }
    final String[] parts = args[0].split("@");
    System.out.println(parts.length);
    for (int i = 0; i < parts.length; ++i)
      System.out.println(i + ":\"" + parts[i] + "\"");
  }
}
