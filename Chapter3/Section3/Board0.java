class Board0 {
  private final String en_passant;
  public Board0(final String enpassant) {
    en_passant = enpassant;
  }
  public String get_en_passant() {
    return en_passant;
  }

  public static void main(final String[] args) {
    final Board0 b = new Board0("Ha!");
    assert(b.get_en_passant() == "Ha!");
  }
}
 