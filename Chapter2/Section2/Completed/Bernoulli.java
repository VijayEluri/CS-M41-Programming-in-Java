public class Bernoulli {

  // computing the number of heads for N fair coin flips:
  public static int binomial(final int N) {
    int heads = 0;
    for (int j = 0; j < N; ++j)
      if (StdRandom.bernoulli(0.5)) ++heads;
    return heads;
  }

  public static void main(final String[] args) {     
    final int N = Integer.parseInt(args[0]);
    final int T = Integer.parseInt(args[1]);

    // computing how often 0, ..., N heads occurred for T experiments:
    final int[] freq = new int[N+1];
    for (int i = 0; i < T; ++i)
      ++freq[binomial(N)];

    // for plotting normalise the counts (i.e., compute relative frequencies):
    final double[] normalised = new double[N+1];
    for (int i = 0; i <= N; ++i) 
      normalised[i] = (double) freq[i] / T;
    StdStats.plotBars(normalised); 

    // for comparison, compute the Gaussian density (yielding a good
    // approximation for N >= 10):
    final double mean = N / 2.0, stddev = Math.sqrt(N) / 2.0;  
    final double[] phi  = new double[N+1];
    for (int i = 0; i <= N; ++i) 
      phi[i] = Gaussian.phi(i, mean, stddev);
    StdStats.plotLines(phi);
  }
}
