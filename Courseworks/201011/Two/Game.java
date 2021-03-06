// Oliver Kullmann, 6.12.2010 (Swansea)

/*
  After construction, an object G of type Game offers G.get_move_sequence(),
  a copy of the array of valid half-moves (null iff the move-sequence was
  syntactically invalid), plus the input data via constant data members.
*/

class Game {

  public static final String
    white_won = "1-0",
    black_won = "0-1",
    draw = "1/2-1/2",
    unknown = "*",
    kingside_castling = "O-O",
    queenside_castling = "O-O-O";

  public final String
    event, site, date, name_w, name_b, result, movetext, fen;
  public final int round;

  public final boolean monitor;

  private int num_halfmoves; // -1 iff invalid movetext
  private int num_valid_halfmoves;
  private String simplified_movetext;

  private final Board B;
  private final Moves M;

  private char[][] move_seq;
  /* A single move is a char-array of length 6, 3 or 4, where the first
     char is 'w' or 'b' ("white" or "black"), followed by 'c' for "check"
     or 'm' for "mate" or '-' for neither, followed either
      - by the initial field and the target field (each as (file, rank))
      - or by 'k' or 'q' for the kingside resp. queenside castling
      - or by file and figure for a pawn promotion.
     If an invalid move is found, then from this move on all array-pointers
     will be null.
     If the move-sequence was syntactically invalid, then move_seq is null.
  */

  public Game(final String ev, final String si, final String da, final int ro,
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
    num_halfmoves = -1;
    valid_move_sequence();
    M = new Moves(B);
    num_valid_halfmoves = 0;
    move_seq = null;
    if (num_halfmoves != -1) fill_move_seq();
    if (monitor) System.out.println(this);
  }

  // checks for syntactical correctness (only!); sets num_halfmoves and
  // simplified_movetext, where num_halfmoves == -1 in case of
  // a syntactical error, and simplified movetex just contains the
  // space-separated sequence of moves in SAN (without any comments and
  // without numbering):
  private void valid_move_sequence() {
    simplified_movetext = remove_comments(movetext);
    if (simplified_movetext.isEmpty()) return;
    final String[] parts = simplified_movetext.split("\\s+");
    int num_parts = parts.length;
    assert(num_parts > 0);
    if (! result.equals(unknown))
      if (! parts[--num_parts].equals(result)) return;
    boolean white_current_colour = (B.get_active_colour() == 'w');
    int fullmoves = B.get_fullmoves();
    String new_movetext = "";
    boolean read_number = true;
    num_halfmoves = 0;
    for (int i = 0; i < num_parts; ++i) {
      if (white_current_colour) {
        if (read_number) {
          if (convert(parts[i],true) != fullmoves) {
            num_halfmoves = -1; return;
          }
          else read_number = false;
        }
        else {
          if (! valid_movement(parts[i])) { num_halfmoves = -1; return; }
          else {
            ++num_halfmoves; new_movetext += parts[i] + " ";
            white_current_colour = false; read_number = true;
          }
        }
      }
      else {
        if (read_number)
          if (convert(parts[i],false) == fullmoves) {
            read_number = false; continue;
          }
        if (! valid_movement(parts[i])) { num_halfmoves = -1; return; }
        else {
          ++num_halfmoves; new_movetext += parts[i] + " ";
          white_current_colour = true; read_number = true;
          ++fullmoves;
        }
      }
    }
    simplified_movetext = new_movetext;
  }
  // removes comments, returning the empty string in case of error; assumes
  // that "{" or "}" are not used in comments opened by ";", and just removes
  // characters '?' and '!' throughout:
  private static String remove_comments(String seq) {
    seq = seq.replaceAll("\\?","");
    seq = seq.replaceAll("\\!","");
    // first removing comments of the form "{...}":
    for (int opening_bracket = seq.indexOf("{"); opening_bracket != -1;
         opening_bracket = seq.indexOf("{")) {
      final int closing_bracket = seq.indexOf("}");
      if (closing_bracket < opening_bracket) return "";
      seq = seq.substring(0,opening_bracket) + seq.substring(closing_bracket+1);
    }
    if (seq.contains("}")) return "";
    // now removing comments of the form ";... EOL":
    for (int semicolon = seq.indexOf(";"); semicolon != -1;
         semicolon = seq.indexOf(";")) {
      final int eol = seq.indexOf("\n",semicolon);
      if (eol == -1) return "";
      seq = seq.substring(0,semicolon) + seq.substring(eol+1);
    }
    return seq;
  }
  // converts for example "32." into 32 (for white) and "4..." into 4 (for
  // black), while invalid move-numbers result in -1:
  private static int convert(final String s, final boolean white) {
    assert(!s.contains(" "));
    final int index = s.indexOf(".");
    if (index == -1) return -1;
    if (white) { if (index+1 != s.length()) return -1; }
    else {
      if (s.length() - index != 3) return -1;
      if (s.charAt(index+1) != '.' || s.charAt(index+2) != '.') return -1;
    }
    int result;
    try { result = Integer.parseInt(s.substring(0,index)); }
    catch (RuntimeException e) { return -1; }
    if (result < 1) return -1;
    return result;
  }
  // checks whether m represents a valid SAN (like "e4" or "Bb5xa6+"):
  private static boolean valid_movement(String m) {
    assert(!m.isEmpty());
    if (m.length() < 2) return false;
    if (m.equals(kingside_castling) || m.equals(queenside_castling))
      return true; // handles castling
    final boolean pawn_move = !valid_piece(m.charAt(0));
    if (! pawn_move) m = m.substring(1);
    if (m.length() < 2) return false;
    if (m.contains("+")) { // handles check
      if (m.charAt(m.length()-1) != '+') return false;
      m = m.substring(0,m.length()-1);
      if (m.contains("+")) return false;
    }
    if (m.length() < 2) return false;
    if (m.contains("#")) { // handles checkmate
      if (m.charAt(m.length()-1) != '#') return false;
      m = m.substring(0,m.length()-1);
      if (m.contains("#")) return false;
    }
    if (m.length() < 2) return false;
    if (m.contains("=")) { // handles promotions
      if (! pawn_move) return false;
      if (m.length() < 4) return false;
      if (m.indexOf("=") != m.length()-2) return false;
      if (m.charAt(m.length()-3) != '8') return false;
      if (! valid_piece(m.charAt(m.length()-1))) return false;
      m = m.substring(0,m.length()-2);
    }
    if (m.length() < 2) return false;
    if (m.contains("x")) { // handles capture
      final int pos_x = m.indexOf("x");
      if (m.indexOf("x",pos_x+1) != -1) return false;
      m = m.substring(0,pos_x) + m.substring(pos_x+1);
    }
    if (m.length() < 2) return false;
    if (! Board.valid_file(m.charAt(m.length()-2)) || ! Board.valid_rank(m.charAt(m.length()-1)))
      return false;
    m = m.substring(0,m.length()-2);
    if (m.length() == 0) return true;
    else if (m.length() == 1)
      return Board.valid_file(m.charAt(0)) || Board.valid_rank(m.charAt(0));
    else
      return Board.valid_file(m.charAt(0)) && Board.valid_rank(m.charAt(1));
  }
  private static boolean valid_piece(final char p) {
    return  p == 'K' || p == 'Q' || p == 'R' || p == 'B' || p == 'N';
  }

  // computing the move-sequence from the from simplified_movetext, determining
  // num_valid_halfmoves and move_seq:
  private void fill_move_seq() {
    assert(num_halfmoves >= 0);
    move_seq = new char[num_halfmoves][];
    if (num_halfmoves == 0) return;
    final String[] parts = simplified_movetext.split(" ");
    assert(parts.length == num_halfmoves);
    boolean white_move = (B.get_active_colour() == 'w');
    for (int i = 0; i < num_halfmoves;
         ++i, white_move=!white_move, ++num_valid_halfmoves) {
      final char active_colour = (white_move) ? 'w' : 'b';
      String move = parts[i];
      move = move.replaceAll("x","");
      final boolean check = move.contains("+");
      final boolean mate = move.contains("#");
      final char check_mate = (mate) ? 'm' : (check) ? 'c' : '-';
      if (check || mate) move = move.substring(0,move.length()-1);
      if (move.equals(kingside_castling)) {
        if (white_move) {
          if (! M.check_white_kingside_castling()) break;
          B.do_white_kingside_castling();
        }
        else {
          if (! M.check_black_kingside_castling()) break;
          B.do_black_kingside_castling();
        }
        final char[] move_a = new char[3];
        move_a[0] = active_colour;
        move_a[1] = check_mate;
        move_a[2] = 'k';
        move_seq[i] = move_a;
        continue;
      }
      else if (move.equals(queenside_castling)) {
        if (white_move) {
          if (! M.check_white_queenside_castling()) break;
          B.do_white_queenside_castling();
        }
        else {
          if (! M.check_black_queenside_castling()) break;
          B.do_black_queenside_castling();
        }
        final char[] move_a = new char[3];
        move_a[0] = active_colour;
        move_a[1] = check_mate;
        move_a[2] = 'q';
        move_seq[i] = move_a;
        continue;
      }
      final char promotion = move.contains("=") ? move.charAt(move.length()-1) : '-';
      if (promotion != '-') move = move.substring(0,move.length()-2);
      final char target_file = move.charAt(move.length()-2);
      final char target_rank = move.charAt(move.length()-1);
      final char figure = extract_figure(move,white_move);
      if (figure != 'P' && figure != 'p') move = move.substring(1);
      move = move.substring(0,move.length()-2); // now move contains just the source-field information
      assert(move.length() <= 2);
      char source_file = 0, source_rank = 0;
      if (move.length() == 2) {
        source_file = move.charAt(0);
        source_rank = move.charAt(0);
      }
      else if (move.length() == 1) {
        if (Board.valid_file(move.charAt(0))) {
          source_file = move.charAt(0);
          for (source_rank = '1'; source_rank <= '8'; ++source_rank)
            if (can_move(source_file,source_rank,target_file,target_rank,figure,white_move))
              break;
          if (source_rank == '9') break;
          {char further_rank = (char)(source_rank + 1);
           for (; further_rank <= '8'; ++further_rank)
             if (can_move(source_file,further_rank,target_file,target_rank,figure,white_move))
               break;
           if (further_rank != '9') break;
          }
        }
        else {
          source_rank = move.charAt(0);
          for (source_file = 'a'; source_file <= 'h'; ++source_file)
            if (can_move(source_file,source_rank,target_file,target_rank,figure,white_move))
              break;
          if (source_file == 'i') break;
          {char further_file = (char)(source_file + 1);
           for (; further_file <= 'h'; ++further_file)
             if (can_move(further_file,source_rank,target_file,target_rank,figure,white_move))
               break;
           if (further_file != 'i') break;
          }
        }
      }
      else {
        for (source_file = 'a'; source_file <= 'h'; ++source_file) {
          for (source_rank = '1'; source_rank <= '8'; ++source_rank)
            if (can_move(source_file,source_rank,target_file,target_rank,figure,white_move))
              break;
          if (source_rank <= '8') break;
        }
        if (source_file == 'i') break;
        {char further_source_rank = (char)(source_rank+1);
         for (; further_source_rank <= '8'; ++further_source_rank)
           if (can_move(source_file,further_source_rank,target_file,target_rank,figure,white_move))
             break;
         if (further_source_rank != '9') break;
        }
        {char further_source_file = (char)(source_file+1);
         for (; further_source_file <= 'h'; ++further_source_file) {
           char further_source_rank = '1';
           for (; further_source_rank <= '8'; ++further_source_rank)
             if (can_move(further_source_file,further_source_rank,target_file,target_rank,figure,white_move))
               break;
           if (further_source_rank <= '8') break;
         }
         if (further_source_file != 'i') break;
        }
      }
      if (promotion != '-') {
        if (white_move) {
          if (! M.check_white_promotion(source_file,figure)) break;
          final char[] move_a = new char[4];
          move_a[0] = active_colour;
          move_a[1] = check_mate;
          move_a[2] = source_file;
          move_a[3] = figure;
          move_seq[i] = move_a;
          B.do_white_promotion(source_file,figure);
        }
        else {
          if (! M.check_black_promotion(source_file,figure)) break;
          final char[] move_a = new char[4];
          move_a[0] = active_colour;
          move_a[1] = check_mate;
          move_a[2] = source_file;
          move_a[3] = figure;
          move_seq[i] = move_a;
          B.do_black_promotion(source_file,figure);
        }
      }
      else {
        if (white_move) {
          if (! M.check_normal_white_move(source_file,source_rank,target_file,target_rank))
            break;
          final char[] move_a = new char[6];
          move_a[0] = active_colour;
          move_a[1] = check_mate;
          move_a[2] = source_file;
          move_a[3] = source_rank;
          move_a[4] = target_file;
          move_a[5] = target_rank;
          move_seq[i] = move_a;
          B.do_normal_white_move(source_file,source_rank,target_file,target_rank);
        }
        else {
          if (! M.check_normal_black_move(source_file,source_rank,target_file,target_rank))
            break;
          final char[] move_a = new char[6];
          move_a[0] = active_colour;
          move_a[1] = check_mate;
          move_a[2] = source_file;
          move_a[3] = source_rank;
          move_a[4] = target_file;
          move_a[5] = target_rank;
          move_seq[i] = move_a;
          B.do_normal_black_move(source_file,source_rank,target_file,target_rank);
        }
      }
    }
  }
  private static char extract_figure(final String m, final boolean white) {
    assert(!m.isEmpty());
    final char c = m.charAt(0);
    if (!valid_piece(c)) return (white) ? 'P' : 'p';
    if (white) return c;
    return (char)(c - ('A' - 'a'));
  }
  private boolean can_move(final char file0, final char rank0, final char file1, final char rank1, final char figure, final boolean white) {
    if (B.get(file0,rank0) != figure) return false;
    if (white)
      return M.check_normal_white_move(file0,rank0,file1,rank1);
    else
      return M.check_normal_black_move(file0,rank0,file1,rank1);
  }

  public char[][] get_move_sequence() {
    if (move_seq == null) return null;
    assert(num_valid_halfmoves >= 0);
    final char[][] result = new char[num_valid_halfmoves][];
    for (int i = 0; i < num_valid_halfmoves; ++i) {
      assert(move_seq[i] != null);
      final int items_move = move_seq[i].length;
      result[i] = new char[items_move];
      for (int j = 0; j < items_move; ++j)
        result[i][j] = move_seq[i][j];
    }
    return move_seq;
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
    if (num_halfmoves == -1) s += "Invalid move sequence.\n";
    return s;
  }

  // unit testing:
  public static void main(final String[] args) {
    // construction
    {
      final String
        ev1 = "F/S Return Match", si1 = "Belgrade, Serbia Yugoslavia|JUG",
        da1 = "1992.11.04", nw1 = "Fischer, Robert J.",
        nb1 = "Spassky, Boris V.", re1 = "1/2-1/2",
        mo1_0 = "1. e4 e5 2. Nf3 Nc6 3. Bb5 {This opening is called Ruy Lopez.} 3... a6 4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 7. Bb3 d6 8. c3 O-O 9. h3 Nb8 10. d4 Nbd7 11. c4 c6 12. cxb5 axb5 13. Nc3 Bb7 14. Bg5 b4 15. Nb1 h6 16. Bh4 c5 17. dxe5 Nxe4 18. Bxe7 Qxe7 19. exd6 Qf6 20. Nbd2 Nxd6 21. Nc4 Nxc4 22. Bxc4 Nb6 23. Ne5 Rae8 24. Bxf7+ Rxf7 25. Nxf7 Rxe1+ 26. Qxe1 Kxf7 27. Qe3 Qg5 28. Qxg5 hxg5 29. b3 Ke6 30. a3 Kd6 31. axb4 cxb4 32. Ra5 Nd5 33. f3 Bc8 34. Kf2 Bf5 35. Ra7 g6 36. Ra6+ Kc5 37. Ke1 Nf4 38. g3 Nxh3 39. Kd2 Kb5 40. Rd6 Kc5 41. Ra6 Nf2 42. g4 Bd3 43. Re6",
        mo1_1 = " 1/2-1/2", mo1 = mo1_0 + mo1_1, fe1 = "";
      final int ro1 = 29;
      final Game g1 = new Game(ev1,si1,da1,ro1,nw1,nb1,re1,mo1,fe1,true);
      assert(g1.event == ev1);
      assert(g1.site == si1);
      assert(g1.date == da1);
      assert(g1.round == ro1);
      assert(g1.name_w == nw1);
      assert(g1.name_b == nb1);
      assert(g1.result == re1);
      assert(g1.movetext == mo1);
      assert(g1.fen == fe1);
      final char[][] ms1 = g1.get_move_sequence();
      assert(ms1 != null);
      assert(ms1.length == 85);
      final Game g1b = new Game(ev1,si1,da1,ro1,nw1,nb1,re1,mo1_0,fe1,true);
      assert(g1b.get_move_sequence() == null);
      final String ev2="x",si2="x",da2="x",nw2="x",nb2="x",re2="1/2-1/2",mo2="1. 1/2-1/2",fe2="";
      final int ro2 = 0;
      final Game g2 = new Game(ev2,si2,da2,ro2,nw2,nb2,re2,mo2,fe2,true);
      final char[][] ms2 = g2.get_move_sequence();
      assert(ms2 != null);
      assert(ms2.length == 0);
      final Game g3 = new Game(ev2,si2,da2,ro2,nw2,nb2,re2,"1. e4",fe2,true);
      final char[][] ms3 = g3.get_move_sequence();
      assert(ms3 == null);
      final String
        ev4 = "Scholar's mate", si4 = "X",
        da4 = "X", nw4 = "X",
        nb4 = "X", re4 = "1-0",
        mo4 = "1. e4 e5 2. Qh5!? Nc6 3. Bc4 Nf6?? 4. Qxf7# 1-0", fe4 = "";
      final int ro4 = 0;
      final Game g4 = new Game(ev4,si4,da4,ro4,nw4,nb4,re4,mo4,fe4,true);
      final char[][] ms4 = g4.get_move_sequence();
      assert(ms4 != null);
      assert(ms4.length == 7);
    }
    // syntax check
    {
      assert(remove_comments("").equals(""));
      assert(remove_comments("{").equals(""));
      assert(remove_comments("}").equals(""));
      assert(remove_comments("{}").equals(""));
      assert(remove_comments("xyz { jyt } kjh { bvc po5 } ").equals("xyz  kjh  "));
      assert(remove_comments(";  \nabc\nxyz;333\n").equals("abc\nxyz"));
      assert(remove_comments("sdjd{,,l;}; djsks\n{   ]}sjfdk ").equals("sdjdsjfdk "));
      assert(remove_comments(";abc").equals(""));
      assert(remove_comments(";]\na").equals("a"));
      assert(remove_comments(";}\na").equals(""));
      assert(remove_comments("?!?!").equals(""));
      assert(remove_comments("?sdjd{,,l;!}; dj!sk?s\n{ ??  ]}sjfdk ?!").equals("sdjdsjfdk "));
      assert(convert("",true) == -1);
      assert(convert("",false) == -1);
      assert(convert("x",true) == -1);
      assert(convert("x",false) == -1);
      assert(convert(".",true) == -1);
      assert(convert(".",false) == -1);
      assert(convert("44..",true) == -1);
      assert(convert("44..",false) == -1);
      assert(convert("33.",true) == 33);
      assert(convert("33.",false) == -1);
      assert(convert("13...",true) == -1);
      assert(convert("13...",false) == 13);
      assert(convert("0.",true) == -1);
      assert(convert("0.",false) == -1);
      assert(convert("-2.",true) == -1);
      assert(convert("-2.",false) == -1);
      assert(convert("3[3.",true) == -1);
      assert(convert("3[3.",false) == -1);
      assert(valid_movement("O-O"));
      assert(valid_movement("O-O-O"));
      assert(valid_movement("e2"));
      assert(valid_movement("c7c5"));
      assert(valid_movement("cc5"));
      assert(valid_movement("7c5"));
      assert(valid_movement("c4xd3"));
      assert(valid_movement("e8=N"));
      assert(valid_movement("d7xe8=N"));
      assert(valid_movement("Be2"));
      assert(valid_movement("Qc7c5"));
      assert(valid_movement("Rcc5"));
      assert(valid_movement("N7c5"));
      assert(valid_movement("Kc4xd3"));
      assert(valid_movement("e2+"));
      assert(valid_movement("c7c5+"));
      assert(valid_movement("cc5+"));
      assert(valid_movement("7c5+"));
      assert(valid_movement("c4xd3+"));
      assert(valid_movement("e8=N+"));
      assert(valid_movement("d7xe8=N+"));
      assert(valid_movement("Be2+"));
      assert(valid_movement("Qc7c5+"));
      assert(valid_movement("Rcc5+"));
      assert(valid_movement("N7c5+"));
      assert(valid_movement("Kc4xd3+"));
      assert(valid_movement("e2#"));
      assert(valid_movement("c7c5#"));
      assert(valid_movement("cc5#"));
      assert(valid_movement("7c5#"));
      assert(valid_movement("c4xd3#"));
      assert(valid_movement("e8=N#"));
      assert(valid_movement("d7xe8=N#"));
      assert(valid_movement("Be2#"));
      assert(valid_movement("Qc7c5#"));
      assert(valid_movement("Rcc5#"));
      assert(valid_movement("N7c5#"));
      assert(valid_movement("Kc4xd3#"));
    }
  }
}

