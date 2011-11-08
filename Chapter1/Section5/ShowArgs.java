// Oliver Kullmann, 8.11.2011 (Swansea)

class ShowArgs {
  public static void main(final String[] args) {
    System.out.println(args);
    System.out.println(args.length);
    for (int i = 0; i < args.length; ++i)
      System.out.println(i + " : \"" + args[i] + "\"");
  }
}
