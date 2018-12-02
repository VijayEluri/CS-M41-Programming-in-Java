import java.util.Random;

class AbsorbingCounter {
  long counter;
  final long min, max;
  public AbsorbingCounter(final long min_, final long max_) {
    if (max_ < min_) { min = max_; max = min_; }
    else {min = min_; max = max_; }
    assert(min <= max);
    counter = min + (max - min) / 2;
    assert(min <= counter);
    assert(counter <= max);
  }
  public long val() { return counter; }
  public void inc() {
    assert(min <= counter && counter <= max);
    if (counter < max) ++counter;
  }
  public void dec() {
    assert(min <= counter && counter <= max);
    if (counter > min) -- counter;
  }
  public boolean reached_min() {
    assert(min <= counter && counter <= max);
    return counter == min;
  }
  public boolean reached_max() {
    assert(min <= counter && counter <= max);
    return counter == max;
  }

  public String toString() {
    return "[" + min + "," + counter + "," + max + "]";
  }
  public boolean equals(final AbsorbingCounter other) {
    return counter == other.counter && min == other.min &&
      max == other.max;
  }
}

class Stats {
  public long count_min_reached, count_max_reached;
  public double average;
  public String toString() {
    String res = "";
    res += "min: " + count_min_reached + "\n";
    res += "max: " + count_max_reached + "\n";
    res += "mean= " + average + "\n";
    return res;
  }
}

class Experiment {
  private static Random r = new Random(0);
  private static void set_seed(final long seed) {
    r.setSeed(seed);
  }
  private static double random() { return r.nextDouble(); }

  private static int default_size = 100;
  public static int get_default_size() {
    return default_size;
  }

  public static int make_positive(final int x) {
    if (x <= 0) return default_size;
    else return x;
  }
  public static int min(final int x, final long y) {
    if (y < x) return (int) y; else return x;
  }
  public static void add_to_default(final int x) {
    final long sum = default_size + x;
    if (sum < 0) default_size = 0;
    else default_size = min(Integer.MAX_VALUE, sum);
  }

  public static AbsorbingCounter[] create_experiment(final long min,
    final long max, final int N) {
    final int size = make_positive(N);
    assert(size >= 1);
    final AbsorbingCounter[] exp = new AbsorbingCounter[size];
    for (int i = 0; i < size; ++i)
      exp[i] = new AbsorbingCounter(min, max);
    return exp;
  }
  public static void run_experiment(final AbsorbingCounter[] e,
    final long T) {
    for (long i = 0; i < T; ++i) {
      final int index = (int)(random() * e.length);
      final boolean dec = random() < 0.5;
      if (dec) e[index].dec(); else e[index].inc();
    }
  }
  public static Stats evaluate_experiment(final AbsorbingCounter[] e) {
    Stats res = new Stats();
    assert(e.length > 0);
    for (int i = 0; i < e.length; ++i) {
      final AbsorbingCounter x = e[i];
      if (x.reached_min()) ++res.count_min_reached;
      if (x.reached_max()) ++res.count_max_reached;
      res.average += x.val();
    }
    res.average /= e.length;
    return res;
  }

  public static void main(final String[] args ) {
    final long min = Long.parseLong(args[0]);
    final long max = Long.parseLong(args[1]);
    final int N = Integer.parseInt(args[2]);
    final long T = Long.parseLong(args[3]);
    if (args.length > 4) set_seed(Long.parseLong(args[4]));
    final AbsorbingCounter[] experiment = create_experiment(min, max, N);
    run_experiment(experiment, T);
    System.out.println(evaluate_experiment(experiment));
  }
}
