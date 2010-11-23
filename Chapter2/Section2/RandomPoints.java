public class RandomPoints {
  public static void main(final String args[]) {
    final int N = Integer.parseInt(args[0]);
    for (int i = 0; i < N; ++i) {
      // create a random point (x,y) according to the Gaussian
      // distribution with mean 0.5 and standard deviation 0.2:
      XXX look up the function in Chapter2/Libraries/StdRandom.java XXX
      StdDraw.point(x, y);
    }
    System.out.println("Finished.");
  }
}
