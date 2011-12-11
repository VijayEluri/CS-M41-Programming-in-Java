class PotentialProblem2 {

  private String x;

  public PotentialProblem2() {
    x = "INITIAL";
  }
  public PotentialProblem2(final PotentialProblem2 p) {
    x = p.x;
  }

  public void set(final String new_x) {
    x = new_x;
  }
  public String get() {
    return x;
  }

  public static void make(final PotentialProblem2 p, final String x) {
    p.set(x);
  }

  public static void main(final String[] args) {

    final PotentialProblem2 p = new PotentialProblem2();
    System.out.println("p = " + p.get());
    make(p,"77");
    System.out.println("p = " + p.get());

    final PotentialProblem2 p2 = new PotentialProblem2(p);
    System.out.println("p2 = " + p2.get());
    make(p,"88");
    System.out.println("p = " + p.get());
    System.out.println("p2 = " + p2.get());
  }
}
