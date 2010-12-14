class Board1 {
  private final String en_passant;
  public Board1(final String enpassant) {
    en_passant = enpassant;
  }
  public String get_en_passant() {
    return en_passant;
  }
  public boolean equals(final Board1 other) {
    return en_passant == other.en_passant;
  }

  public static void main(final String[] args) {
    final Board1 b = new Board1("Ha!");
    assert(b.get_en_passant() == "Ha!");
    assert(b.equals(b));
    final Board1 b2 = new Board1("Ha!");
    assert(b2.equals(b));
    final Board1 b3 = new Board1("Ha");
    assert(! b3.equals(b));
  }
}
 
