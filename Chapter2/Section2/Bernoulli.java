public class Bernoulli {

  // computing the number of heads for N fair coin flips:
  public static int binomial(final int N) {
    XXX use StdRandom for fair coin flips XXX
  }

  public static void main(final String[] args) {     
    final int N = Integer.parseInt(args[0]);
    final int T = Integer.parseInt(args[1]);

    // computing how often 0, ..., N heads occurred for T experiments:
    final int[] freq = new int[N+1];
    for (int i = 0; i < T; ++i)
      XXX at the end, freq[i] counts how often we had (exactly) i heads
      with N coin flips, when performing T times N coin flips XXX

    // for plotting normalise the counts (i.e., compute relative frequencies):
    final double[] normalised = new double[N+1];
    for (int i = 0; i <= N; ++i) 
      normalised[i] = XXX divide freq by the total number of trials XXX
    StdStats.plotBars(normalised); 

    // for comparison, compute the Gaussian density (yielding a good
    // approximation for N >= 10):
    final double mean = N / 2.0, stddev = Math.sqrt(N) / 2.0;  
    final double[] phi  = new double[N+1];
    for (int i = 0; i <= N; ++i) 
      phi[i] = XXX call Gaussian from Chapter2/Libraries XXX
    StdStats.plotLines(phi);
  }
}
