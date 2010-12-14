class Board3 {
  private String en_passant;
  private char active_colour;

  public Board3(final String enpassant, final char colour) {
    en_passant = enpassant;
    assert(valid_colour(colour)); // design decision: no user errors here, but programming errors!
    active_colour = colour;
  }

  public String get_en_passant() {
    return en_passant;
  }
  public char get_active_colour() {
    return active_colour;
  }

  public void reset() {
    en_passant = "-";
    active_colour = 'w';
  }

  public boolean equals(final Board3 other) {
    return en_passant.equals(other.en_passant) &&
      active_colour == other.active_colour;
  }
  public String toString() {
    final char[] aux = {active_colour};
    return new String(aux) + " " + en_passant;
  }

  private static boolean valid_colour(final char c) {
    return c == 'w' || c == 'b';
  }

  public static void main(final String[] args) {
    final Board3 b = new Board3("Ha!",'w');
    assert(b.get_en_passant().equals("Ha!"));
    assert(b.get_active_colour() == 'w');
    assert(b.equals(b));
    assert(b.toString().equals("w Ha!"));
    Board3 b2 = new Board3("Ha!",'w');
    assert(b2.equals(b));
    b2 = new Board3("Ha",'w');
    assert(! b2.equals(b));
    b2 = new Board3("Ha!",'b');
    assert(! b2.equals(b));
    b.reset();
    assert(b.get_en_passant().equals("-"));
    assert(b.get_active_colour() == 'w');
    for (int x = 0; x < 256; ++x) {
      final char c = (char) x;
      assert(valid_colour(c) == (c == 'w' || c == 'b'));
    }
  }
}
 
