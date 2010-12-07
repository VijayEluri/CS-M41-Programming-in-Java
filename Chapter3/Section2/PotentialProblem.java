class PotentialProblem {

  private int x;

  public PotentialProblem() {
    x = 0;
  }
  public PotentialProblem(final PotentialProblem p) {
    x = p.x;
  }

  public void set(final int new_x) {
    x = new_x;
  }
  public int get() {
    return x;
  }

  public static void make(final PotentialProblem p, final int x) {
    p.set(x);
  }

  public static void main(final String[] args) {

    final PotentialProblem p = new PotentialProblem();
    System.out.println("p = " + p.get());
    make(p,77);
    System.out.println("p = " + p.get());

    final PotentialProblem p2 = new PotentialProblem(p);
    System.out.println("p2 = " + p2.get());
    make(p,88);
    System.out.println("p = " + p.get());
    System.out.println("p2 = " + p2.get());
  }
}
