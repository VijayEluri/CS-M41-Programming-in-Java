// Oliver Kullmann, 11.12.2009 (Swansea)

/*
  Compile with

    javac Client.java Old/Storage.java

  or

    javac Client.java New/Storage.java


  Run with

    java -cp .:Old Client N

  or

    java -cp .:New Client N
*/

class Client {

  public static void main(String[] args) {
    final int N = Integer.parseInt(args[0]);
    Storage S = new Storage(N);
    for (int i = 1; i < N; ++i)
      for (int j = i+1; j <= N; ++j)
        S.set(i,j,i*j);
    System.out.println(S);
  }
}

