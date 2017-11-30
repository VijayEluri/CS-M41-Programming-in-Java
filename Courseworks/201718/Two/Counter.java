class Counter {
  public static final int bound = 10;
  public final String name;
  private int count;
  public Counter(final String n) {
    assert(bound >= 0);
    assert(bound < Integer.MAX_VALUE);
    assert(n != null);
    assert(! n.equals(""));
    name = n;
    count = 0;
  }
  public int count() {
    assert(count >= 0);
    assert(count <= bound);
    return count;
  }
  public String name() {
    assert(name != null);
    assert(! name.equals(""));
    return name;
  }
  public boolean inc() {
    assert(count >= 0);
    assert(count <= bound);
    if (count < bound) {
      ++count;
      return true;
    }
    else return false;
  }
  public String toString() {
    assert(name != null);
    assert(! name.equals(""));
    assert(count >= 0);
    assert(count <= bound);
    return name + ": " + count;
  }
  public boolean equals(final Counter other) {
    assert(name != null);
    assert(! name.equals(""));
    assert(count >= 0);
    assert(count <= bound);
    return other != null && name.equals(other.name);
  }
  
  public static void main(final String[] args) {
    final Counter c1 = new Counter("a");
    System.out.println(c1);
    final Counter c2 = new Counter("b");
    System.out.println(c2);
    System.out.println(c1.equals(c2));
    c1.inc();
    System.out.println(c1.count());
    c2.inc(); c2.inc();
    System.out.println(c1 + " " + c2);
  }
}