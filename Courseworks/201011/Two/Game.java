// Oliver Kullmann, 6.12.2010 (Swansea)

class Game {

  public final String
    white_won = "1-0",
    black_won = "0-1",
    draw = "1/2-1/2",
    unknown = "*";

  public final String
    event, site, date, name_w, name_b, result, movetext, fen;
  public final int round;

  public final boolean monitor;

  public final int num_halfmoves;

  private final Board B;
  private final Moves M;

  private final char[][] move_seq;
  /* A single move is a char-array of length 5, 2 or 3, where the first
     char is 'w' or 'b', followed either
      - by the initial field and the target field (each as file, rank)
      - or by 'k', 'q' for the kingside resp. queenside castling
      - or by file and figure for a pawn promotion.
  */

  Game(final String ev, final String si, final String da, final int ro,
      final String nw, final String nb, final String re,
      final String mo, final String fe, final boolean mon) {
    assert(!ev.isEmpty());
    assert(!si.isEmpty());
    assert(!da.isEmpty());
    assert(!nw.isEmpty());
    assert(!nb.isEmpty());
    assert(re.equals(white_won) || re.equals(black_won) || re.equals(draw) || re.equals(unknown));
    assert(fe.isEmpty() || Board.validFEN(fe));
    // if fen is empty then the standard position is used
    event = ev; site = si; date = da; round = ro;
    name_w = nw; name_b = nb; result = re; movetext = mo;
    fen = fe; monitor = mon;
    if (fen.isEmpty()) B = new Board();
    else B = new Board(fen);
    num_halfmoves = valid_move_sequence(movetext);
    assert(num_halfmoves >= 0);
    M = new Moves(B);
    move_seq = fill_move_seq();
    if (monitor) System.out.println(this);
  }

  // checks for syntactical correctness (only!); returns -1 in case of
  // a syntactical error, and the number of halfmoves (>= 0) otherwise:
  public static int valid_move_sequence(final String seq) {
    // code will be provided YYY
    return 0;
  }

  private char[][] fill_move_seq() {
    char[][] ms = new char[num_halfmoves][];
    // XXX fill ms with the moves
    return ms;
  }

  public String toString() {
    String s = "";
    s += "Event: " + event + "\n";
    s += "Site: " + site + "\n";
    s += "Date: " + date + "\n";
    s += "Round: " + round + "\n";
    s += "White: " + name_w + "\n";
    s += "Black: " + name_b + "\n";
    s += "Result: " + result + "\n";
    s += B;
    return s;
  }

  // unit testing:
  public static void main(final String[] args) {
    // construction
    {
      final Game g = new Game(
        "F/S Return Match",
        "Belgrade, Serbia Yugoslavia|JUG",
        "1992.11.04",
        29,
        "Fischer, Robert J.",
        "Spassky, Boris V.",
        "1/2-1/2",
        "1. e4 e5 2. Nf3 Nc6 3. Bb5 {This opening is called Ruy Lopez.} 3... a6 4. Ba4 Nf6 5. 0-0 Be7 6. Re1 b5 7. Bb3 d6 8. c3 0-0 9. h3 Nb8 10. d4 Nbd7 11. c4 c6 12. cxb5 axb5 13. Nc3 Bb7 14. Bg5 b4 15. Nb1 h6 16. Bh4 c5 17. dxe5 Nxe4 18. Bxe7 Qxe7 19. exd6 Qf6 20. Nbd2 Nxd6 21. Nc4 Nxc4 22. Bxc4 Nb6 23. Ne5 Rae8 24. Bxf7+ Rxf7 25. Nxf7 Rxe1+ 26. Qxe1 Kxf7 27. Qe3 Qg5 28. Qxg5 hxg5 29. b3 Ke6 30. a3 Kd6 31. axb4 cxb4 32. Ra5 Nd5 33. f3 Bc8 34. Kfe Bf5 35. Ra7 g6 36. Ra6+ Kc5 37. Ke1 Nf4 38. g3 Nxh3 39. Kd2 Kb5 40. Rd6 Kc5 41. Ra6 Nf2 42. g4 Bd3 43. Re6 1/2-1/2",
        "",
        true);
    }
  }
}

