class AllDifferent {
  public static void main(final String[] args) {
    final int[] a = new int[args.length];
    for (int i = 0; i != args.length; ++i) a[i] = Integer.parseInt(args[i]);
    boolean alldiff = true;
    for (int i = 0; alldiff && i < args.length-1; ++i)
      for (int j = i+1; j < args.length; ++j)
        if (a[i] == a[j]) {alldiff = false; break;}
    System.out.println(alldiff);
  }
}
