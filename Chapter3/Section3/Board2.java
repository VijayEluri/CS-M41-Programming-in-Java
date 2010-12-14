class Board2 {
  private String en_passant;
  public Board2(final String enpassant) {
    en_passant = enpassant;
  }
  public String get_en_passant() {
    return en_passant;
  }
  public void reset() {
    en_passant = "-";
  }
  public boolean equals(final Board2 other) {
    return en_passant.equals(other.en_passant);
  }

  public static void main(final String[] args) {
    final Board2 b = new Board2("Ha!");
    assert(b.get_en_passant().equals("Ha!"));
    assert(b.equals(b));
    final Board2 b2 = new Board2("Ha!");
    assert(b2.equals(b));
    final Board2 b3 = new Board2("Ha");
    assert(! b3.equals(b));
    b.reset();
    assert(b.get_en_passant().equals("-"));
  }
}
 
