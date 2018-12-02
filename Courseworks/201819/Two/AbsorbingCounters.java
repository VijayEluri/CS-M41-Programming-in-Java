import java.util.Random;

class AbsorbingCounter {
  long counter;
  boolean r_min, r_max;
  final long min, max;
  public AbsorbingCounter(final long min_, final long max_) {
    if (max_ < min_) { min = max_; max = min_; }
    else {min = min_; max = max_; }
    assert(min <= max);
    counter = min + (max - min) / 2;
    assert(min <= counter);
    assert(counter <= max);
    r_min = counter == min;
    r_max = counter == max;
  }
  public long val() { return counter; }
  public void inc() {
    assert(min <= counter && counter <= max);
    if (r_min || r_max) return;
    if (counter < max) ++counter;
    r_max = counter == max;
  }
  public void dec() {
    assert(min <= counter && counter <= max);
    if (r_min || r_max) return;
    if (counter > min) --counter;
    r_min = counter == min;
  }
  public boolean reached_min() {
    assert(min <= counter && counter <= max);
    return r_min;
  }
  public boolean reached_max() {
    assert(min <= counter && counter <= max);
    return r_max;
  }

  public String toString() {
    return "[" + min + "," + counter + "," + max + "]";
  }
  public boolean equals(final AbsorbingCounter other) {
    return counter == other.counter && min == other.min &&
      max == other.max;
  }

  public static void main(final String[] args) {
    final AbsorbingCounter c1 = new AbsorbingCounter(0, 10);
    assert(c1.val() == 5);
    assert(! c1.reached_min());
    assert(! c1.reached_max());
    assert(c1.toString().equals("[0,5,10]"));
    assert(c1.equals(new AbsorbingCounter(0,10)));
    c1.inc(); assert(c1.val() == 6);
    assert(! c1.equals(new AbsorbingCounter(0,10)));
    c1.inc(); c1.inc(); c1.inc(); c1.inc(); assert(c1.val() == 10);
    assert(c1.reached_max());
    c1.inc(); assert(c1.val() == 10);
    c1.dec(); assert(c1.val() == 10);
    assert(c1.reached_max());
    final AbsorbingCounter c2 = new AbsorbingCounter(-10,10);
    assert(c2.val() == 0);
    assert(! c2.equals(c1));
    for (int i = 0; i < 10; ++i) c2.dec();
    assert(c2.val() == -10);
    assert(c2.reached_min());
    c2.dec(); assert(c2.val() == -10); assert(c2.reached_min());
    final AbsorbingCounter c3 = new AbsorbingCounter(0,10);
    assert(!c3.equals(c1));
    for (int i = 0; i < 5; ++i, c3.inc());
    assert(c3.equals(c1));
  }
}

class Stats {
  public long count_min_reached, count_max_reached;
  public double average;
  public String toString() {
    String res = "";
    res += "count min: " + count_min_reached + "\n";
    res += "count max: " + count_max_reached + "\n";
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
    final long T = Long.parseLong(args[2]);
    final int N = (args.length > 3) ? Integer.parseInt(args[3]) : 0;
    final long seed = (args.length > 4) ? Long.parseLong(args[4]) : 0;
    set_seed(seed);
    final AbsorbingCounter[] experiment = create_experiment(min, max, N);
    run_experiment(experiment, T);
    System.out.println("T=" + T + ", N=" + experiment.length +
      ", seed=" + seed);
    System.out.print(evaluate_experiment(experiment));
  }
}
