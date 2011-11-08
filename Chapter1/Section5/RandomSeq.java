// Oliver Kullmann, 8/10/2009 (Swansea)

class RandomSeq { 
  public static void main(final String[] args) { 
  final long N = Long.parseLong(args[0]);
  for (long i = 0; i < N; ++i)
    System.out.println(Math.random());
  }
}
