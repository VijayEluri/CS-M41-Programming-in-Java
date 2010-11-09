// Oliver Kullmann, 8/10/2009 (Swansea)

class RandomSeq { 
  public static void main(final String[] args) { 
  final int N = Integer.parseInt(args[0]);
  for (int i = 0; i < N; ++i)
    System.out.println(Math.random());
  }
}
