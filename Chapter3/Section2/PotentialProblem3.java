class PotentialProblem3 {

  private String[] x;

  public PotentialProblem3() {
    x = new String[1];
    x[0] = "INITIAL";
  }
  public PotentialProblem3(final PotentialProblem3 p) {
    x = p.x;
  }

  public void set(final String new_x) {
    assert(x != null);
    assert(x.length != 0);
    x[0] = new_x;
  }
  public String get() {
    assert(x != null);
    assert(x.length != 0);
    return x[0];
  }

  public static void make(final PotentialProblem3 p, final String x) {
    p.set(x);
  }

  public static void main(final String[] args) {

    final PotentialProblem3 p = new PotentialProblem3();
    System.out.println("p = " + p.get());
    make(p,"77");
    System.out.println("p = " + p.get());

    final PotentialProblem3 p2 = new PotentialProblem3(p);
    System.out.println("p2 = " + p2.get());
    make(p,"88");
    System.out.println("p = " + p.get());
    System.out.println("p2 = " + p2.get());
  }
}
