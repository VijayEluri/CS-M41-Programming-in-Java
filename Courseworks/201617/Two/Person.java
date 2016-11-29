class Person {
  public static final String message = "ehpXdT3tie";
  public final String name;
  private int income;
  public Person(final String n) {name = n; income = 0;}
  public Person(final String n, final int i) {
    name = n;
    if (i >= 0) income = i;
    else income = 0;
  }
  public int income() {
    assert(income >= 0);
    return income;
  }
  public boolean increase(final int inc) {
    assert(income >= 0);
    if (inc < 0) return false;
    final long sum = (long)income + inc;
    if (sum > Integer.MAX_VALUE) return false;
    income = (int) sum;
    return true;
  }
  public boolean decrease(final int dec) {
    assert(income >= 0);
    if (dec < 0) return false;
    if (dec > income) income = 0;
    else income -= dec;
    return true;
  }
  public String toString() {
    assert(income >= 0);
    return name + ": " + income;
  }
  public boolean equals(final Person other) {
    assert(income >= 0);
    return name.equals(other.name);
  }
  
  public static void main(final String[] args) {
    final Person p1 = new Person("a");
    System.out.println(p1);
    final Person p2 = new Person("b", 7);
    System.out.println(p2);
    System.out.println(p1.equals(p2));
    p1.increase(11);
    p2.decrease(8);
    System.out.println(p1 + " " + p2);
  }
}