// Oliver Kullmann, 6.12.2010 (Swansea)

class Board {
    public static final int N = 8;
    public static final char
        white_king = 'K',
        white_queen = 'Q',
        white_rook = 'R',
        white_bishop = 'B',
        white_knight = 'N',
        white_pawn = 'P',
        black_king = 'k',
        black_queen = 'q',
        black_rook = 'r',
        black_bishop = 'b',
        black_knight = 'n',
        black_pawn = 'p',
        empty = 0;

    public static final String
        initial_position = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    
    private final char[][] board;
    private char active_colour; // 'w' or 'b'
    // Castling codes: '-' (none) or 'k' (only king-side) or
    // 'q' (only queen-side) or 'b' (both)
    private char white_castling;
    private char black_castling;
    private String en_passant;
    private int halfmoves;
    private int fullmoves;

    private final Moves M;

    public Board() { // standard position
        M = new Moves(this);
        board = new char[N][N];
        fromFEN(initial_position);
    }
    public Board(final String fen_position) {
        M = new Moves(this);
        board = new char[N][N];
        fromFEN(fen_position);
    }

    public char get(final char file, final char rank) {
        return board[rank2index(rank)][file2index(file)];
    }
    public char get_active_colour() { return active_colour; }
    public char get_white_castling() { return white_castling; }
    public char get_black_castling() { return black_castling; }
    public String get_en_passant() { return en_passant; }
    public int get_halfmoves() { return halfmoves; }
    public int get_fullmoves() { return fullmoves; }
    
    public void do_white_kingside_castling() {
        assert(M.check_white_kingside_castling());
        reset('e','1');
        reset('h','1');
        set('f','1','R');
        set('g','1','K');
        active_colour = 'b';
        white_castling = '-';
        ++halfmoves;
        en_passant = "-";
    }
    public void do_white_queenside_castling() {
        assert(M.check_white_queenside_castling());
        reset('e','1');
        reset('a','1');
        set('d','1','R');
        set('c','1','K');
        active_colour = 'b';
        white_castling = '-';
        ++halfmoves;
        en_passant = "-";
    }
    public void do_black_kingside_castling() {
        assert(M.check_black_kingside_castling());
        reset('e','8');
        reset('h','8');
        set('f','8','r');
        set('g','8','k');
        active_colour = 'w';
        black_castling = '-';
        ++halfmoves;
        ++fullmoves;
        en_passant = "-";
    }
    public void do_black_queenside_castling() {
        assert(M.check_black_queenside_castling());
        reset('e','8');
        reset('a','8');
        set('d','8','r');
        set('c','8','k');
        active_colour = 'w';
        black_castling = '-';
        ++halfmoves;
        ++fullmoves;
        en_passant = "-";
    }

    public void do_white_promotion(final char pawn_file, final char figure) {
        assert(M.check_white_promotion(pawn_file, figure));
        reset(pawn_file,'7');
        set(pawn_file,'8',figure);
        active_colour = 'b';
        halfmoves=0;
        en_passant = "-";
    }
    public void do_black_promotion(final char pawn_file, final char figure) {
        assert(M.check_black_promotion(pawn_file, figure));
        reset(pawn_file,'2');
        set(pawn_file,'1',figure);
        active_colour = 'w';
        halfmoves = 0;
        ++fullmoves;
        en_passant = "-";
    }

    public void do_normal_white_move(final char file0, final char rank0,
                                     final char file1, final char rank1) {
        assert(M.check_normal_white_move(file0,rank0,file1,rank1));
        final char figure = board[rank2index(rank0)][file2index(file0)];
        set(file1,rank1,figure);
        reset(file0,rank0);
        active_colour = 'b';
        if (figure == white_king) white_castling = '-';
        else if (figure == white_rook && rank0 == '1') {
          if (file0 == 'a') {
            if (white_castling == 'b') white_castling = 'k';
            else if (white_castling == 'q') white_castling = '-';
          }
          else if (file0 == 'h') {
            if (white_castling == 'b') white_castling = 'q';
            else if (white_castling == 'k') white_castling = '-';
          }
        }
        else if (figure == white_pawn) {
          halfmoves = 0;
          if (rank0 == '2' && rank1 == '4')
            en_passant = rankfile(file0,'3');
          else {
            en_passant = "-";
            if (en_passant == (rankfile(file1,rank1)))
              reset(file1,'5'); 
          }
          return;
        }
        en_passant = "-";
        ++halfmoves;
    }
    public void do_normal_black_move(final char file0, final char rank0,
                                     final char file1, final char rank1) {
        assert(M.check_normal_black_move(file0,rank0,file1,rank1));
        final char figure = board[rank2index(rank0)][file2index(file0)];
        set(file1,rank1,figure);
        reset(file0,rank0);
        ++fullmoves;
        active_colour = 'w';
        if (figure == black_king) black_castling = '-';
        else if (figure == black_rook && rank0 == '8') {
          if (file0 == 'a') {
            if (black_castling == 'b') black_castling = 'k';
            else if (black_castling == 'q') black_castling = '-';
          }
          else if (file0 == 'h') {
            if (black_castling == 'b') black_castling = 'q';
            else if (black_castling == 'k') black_castling = '-';
          }
        }
        else if (figure == black_pawn) {
          halfmoves = 0;
          if (rank0 == '7' && rank1 == '5')
            en_passant = rankfile(file0,'6');
          else {
            en_passant = "-";
            if (en_passant == (rankfile(file1,rank1)))
              reset(file1,'3');
          }
          return;
        }
        en_passant = "-";
        ++halfmoves;
    }

    // set figure on field:
    private void set(final char file, final char rank, final char figure) {
        assert(is_valid_figure(figure));
        board[rank2index(rank)][file2index(file)] = figure;
    }
    // delete figure on field:
    private void reset(final char file, final char rank) {
        board[rank2index(rank)][file2index(file)] = empty;
    }

    public static boolean is_valid_figure(final char figure) {
        return is_valid_white_figure(figure) || is_valid_black_figure(figure);
    }
    public static boolean is_valid_white_figure(final char figure) {
        return figure == white_king || figure == white_queen ||
            figure == white_rook || figure == white_bishop ||
            figure == white_knight || figure == white_pawn;
    }
    public static boolean is_valid_black_figure(final char figure) {
        return figure == black_king || figure == black_queen ||
            figure == black_rook || figure == black_bishop ||
            figure == black_knight || figure == black_pawn;
    }

    // checks purely for syntactical validness
    // (while their might be, e.g., 64 pawns, or no figures at all):
    public static boolean validFEN(final String position) {
        final String[] parts = position.split("\\s+");
        return parts.length == 6 && valid_placement(parts[0]) &&
          valid_colour(parts[1]) && valid_castling(parts[2]) &&
          valid_enpassant(parts[3]) && valid_halfmoves(parts[4]) &&
          valid_fullmoves(parts[5]);
    }
    // auxiliary functions for checking the six fields of a fen-record:
    private static boolean valid_placement(final String p) {
      assert(! p.isEmpty());
      final String[] rows = p.split("/");
      if (rows.length != N) return false;
      for (int r = 0; r < N; ++r)
        if (! check_fen_row(rows[r])) return false;
      return true;
    }
    // helper function for valid_placement:
    private static boolean check_fen_row(final String row) {
      assert(! row.isEmpty());
      int num_fields = 0;
      for (int i = 0; i < row.length(); ++i) {
        final char c = row.charAt(i);
        if (is_valid_figure(c)) ++num_fields;
        else
          if (c < '1' || c > '8') return false;
          else num_fields += c - '0';
      }
      return num_fields == N;
    }
    private static boolean valid_colour(final String c) {
      assert(! c.isEmpty());
      return c.equals("w") || c.equals("b");
    }
    private static boolean valid_castling(final String c) {
      assert(! c.isEmpty());
      if (! c.equals("-")) {
        int count = 0;
        int max = -1;
        final int K = c.indexOf("K");
        if (K != -1) ++count;
        max = Math.max(max,K);
        final int Q = c.indexOf("Q",max);
        if (Q != -1) ++count;
        max = Math.max(max,Q);
        final int k = c.indexOf("k",max);
        if (k != -1) ++count;
        max = Math.max(max,k);
        final int q = c.indexOf("q",max);
        if (q != -1) ++count;
        if (count != c.length()) return false;
      }
      return true;
    }
    private static boolean valid_enpassant(final String e) {
      assert(! e.isEmpty());
      if (! e.equals("-")) {
        if (e.length() != 2) return false;
        final char file = e.charAt(0);
        if (file < 'a' || file > 'h') return false;
        final char rank = e.charAt(1);
        if (rank < '1' || rank > '8') return false;
      }
      return true;
    }
    private static boolean valid_halfmoves(final String h) {
      assert(! h.isEmpty());
      try { if (Integer.parseInt(h) < 0) return false; }
      catch (RuntimeException e) { return false; }
      return true;
    }
    private static boolean valid_fullmoves(final String f) {
      assert(! f.isEmpty());
      try { if (Integer.parseInt(f) <= 0) return false; }
      catch (RuntimeException e) { return false; }
      return true;
    }

    // set the position according to description in Forsyth-Edwards notation:
    private void fromFEN(final String position) {
        assert(validFEN(position));
        final String[] parts = position.split("\\s+");
        final String[] rows = parts[0].split("/");
        for (int x = 0; x < N; ++x) {
          final String row = rows[(N-1)-x];
          int y = 0;
          for (int i = 0; i < row.length(); ++i) {
            final char c = row.charAt(i);
            if (c >= '1' && c <= '8') {
              final int num_empty = c - '0';
              for (int j = 0; j < num_empty; ++j) board[x][y++] = empty;
            }
            else board[x][y++] = c;
          }
        }
        active_colour = parts[1].charAt(0);
        if (parts[2].contains("K"))
          if (parts[2].contains("Q")) white_castling = 'b';
          else white_castling = 'k';
        else if (parts[2].contains("Q")) white_castling = 'q';
        else white_castling = '-';
        if (parts[2].contains("k"))
          if (parts[2].contains("q")) black_castling = 'b';
          else black_castling = 'k';
        else if (parts[2].contains("q")) black_castling = 'q';
        else black_castling = '-';
        en_passant = parts[3];
        halfmoves = Integer.parseInt(parts[4]);
        fullmoves = Integer.parseInt(parts[5]);
    }
    // describe the position in Forsyth-Edwards notation:
    public String toFEN() {
        String result = "";
        for (int x = N-1; x >= 0; --x) {
          for (int y = 0; y < N;) {
           if (! is_empty(x,y)) result += board[x][y++];
           else {
             int num_empty = 1;
             while (++y < N && is_empty(x,y)) ++num_empty;
             result += num_empty;
           }
          }
          if (x > 0) result += "/";
        }
        result += " ";
        result += active_colour;
        result += " ";
        if (white_castling == '-' && black_castling == '-') result += "-";
        else {
          if (white_castling == 'b') result += "KQ";
          else if (white_castling == 'k') result += "K";
          else if (white_castling == 'q') result += "Q";
          if (black_castling == 'b') result += "kq";
          else if (black_castling == 'k') result += "k";
          else if (black_castling == 'q') result += "q";
        }
        result += " ";
        result += en_passant + " ";
        result += halfmoves + " ";
        result += fullmoves;
        return result;
    }
    // printing out position in 8 rows of characters,
    // using spaces for empty fields:
    public String toString() {
        String result = "";
        for (int x = N-1; x >= 0; --x) {
            result += (x+1) + ":";
            for (int y = 0; y < N; ++y) {
                final char figure = board[x][y];
                result += (figure == empty) ? '.' : figure;
            }
            result += "\n";
        }
        result += "Active: " + active_colour + " ";
        result += "White/black castling: " + white_castling + black_castling + " ";
        result += "En passant: " + en_passant + " ";
        result += "Half moves: " + halfmoves + " ";
        result += "Full moves: " + fullmoves + " ";
        return result;
    }

    public boolean equals(final Board other) {
        for (int x = 0; x <= 7; ++x)
            for (int y = 0; y <= 7; ++y)
                if (board[x][y] != other.board[x][y]) return false;
        return active_colour == other.active_colour &&
            white_castling == other.white_castling &&
            black_castling == other.black_castling &&
            halfmoves == other.halfmoves &&
            fullmoves == other.fullmoves;
    }

    private boolean is_empty(final int x, final int y) {
        assert(0 <= x);
        assert(x <= 7);
        assert(0 <= y);
        assert(y <= 7);
        return board[x][y] == empty;
    }
    // from file a, ..., h to index 0, ..., 7:
    private static int file2index(final char file) {
        assert(file >= 'a');
        assert(file <= 'h');
        return file - 'a';
    }
    // from rank 1, ..., 8 to index 0, ..., 7:
    private static int rank2index(final char rank) {
        assert(rank >= '1');
        assert(rank <= '8');
        return rank - '1';
    }
    // rank and file together as a string with 2 characters:
    private static String rankfile(final char rank, final char file) {
      StringBuffer f = new StringBuffer();
      f.append(rank); f.append(file);
      return new String(f);
    }

    // Unit testing:
    public static void main(final String[] args) {
      // testing default construction
      {
        final Board b = new Board();
        assert(b.toFEN().equals(initial_position));
        assert(b.active_colour == 'w');
        assert(b.white_castling == 'b');
        assert(b.black_castling == 'b');
        assert(b.halfmoves == 0);
        assert(b.fullmoves == 1);
        int count_blacks = 0, count_whites = 0;
        for (int x = 0; x < N; ++x)
          for (int y = 0; y < N; ++y)
            if (! b.is_empty(x,y)) {
              final char f = b.board[x][y];
              assert(is_valid_figure(f));
              if (is_valid_white_figure(f)) ++count_whites;
              else ++count_blacks;
            }
        assert(count_whites == 16);
        assert(count_blacks == 16);
      }
      // testing construction from fen
      {
        Board b = new Board("8/8/8/8/8/8/8/8 w Kq - 0 3");
        for (int x = 0; x < N; ++x)
          for (int y = 0; y < N; ++y)
            assert(b.board[x][y] == empty);
        assert(b.get_active_colour() == 'w');
        assert(b.get_white_castling() == 'k');
        assert(b.get_black_castling() == 'q');
        assert(b.get_en_passant().equals("-"));
        assert(b.get_halfmoves() == 0);
        assert(b.get_fullmoves() == 3);
        b = new Board("pppppppp/pppppppp/pppppppp/pppppppp/pppppppp/pppppppp/pppppppp/pppppppp b Qk d5 88 317");
        for (int x = 0; x < N; ++x)
          for (int y = 0; y < N; ++y)
            assert(b.board[x][y] == black_pawn);
        assert(b.get_active_colour() == 'b');
        assert(b.get_white_castling() == 'q');
        assert(b.get_black_castling() == 'k');
        assert(b.get_en_passant().equals("d5"));
        assert(b.get_halfmoves() == 88);
        assert(b.get_fullmoves() == 317);
      }
      // testing get-functions
      {
        Board b = new Board();
        assert(b.get('a','1') == 'R');
        assert(b.get('h','8') == 'r');
        assert(b.get_active_colour() == 'w');
        assert(b.get_white_castling() == 'b');
        assert(b.get_black_castling() == 'b');
        assert(b.get_en_passant().equals("-"));
        assert(b.get_halfmoves() == 0);
        assert(b.get_fullmoves() == 1);
      }
      // testing castling moves
      {
        Board b = new Board("4k2r/8/8/8/8/8/8/4K2R w Kk - 0 1");
        b.do_white_kingside_castling();
        assert(b.toFEN().equals("4k2r/8/8/8/8/8/8/5RK1 b k - 1 1"));
        b.do_black_kingside_castling();
        assert(b.toFEN().equals("5rk1/8/8/8/8/8/8/5RK1 w - - 2 2"));

        b = new Board("4k2r/8/8/8/8/8/8/4K2R w KQk - 0 1");
        b.do_white_kingside_castling();
        assert(b.toFEN().equals("4k2r/8/8/8/8/8/8/5RK1 b k - 1 1"));
        b.do_black_kingside_castling();
        assert(b.toFEN().equals("5rk1/8/8/8/8/8/8/5RK1 w - - 2 2"));

        b = new Board("r3k3/8/8/8/8/8/8/R3K3 w Qq - 0 1");
        b.do_white_queenside_castling();
        assert(b.toFEN().equals("r3k3/8/8/8/8/8/8/2KR4 b q - 1 1"));
        b.do_black_queenside_castling();
        assert(b.toFEN().equals("2kr4/8/8/8/8/8/8/2KR4 w - - 2 2"));

        b = new Board("r3k3/8/8/8/8/8/8/R3K3 w Qkq - 0 1");
        b.do_white_queenside_castling();
        assert(b.toFEN().equals("r3k3/8/8/8/8/8/8/2KR4 b kq - 1 1"));
        b.do_black_queenside_castling();
        assert(b.toFEN().equals("2kr4/8/8/8/8/8/8/2KR4 w - - 2 2"));

        b = new Board("r3k3/8/8/8/8/8/8/R3K3 b Qkq - 0 1");
        b.do_black_queenside_castling();
        assert(b.toFEN().equals("2kr4/8/8/8/8/8/8/R3K3 w Q - 1 2"));
      }
      // testing promotion moves
      {
        Board b = new Board("8/P7/8/8/8/8/7p/8 w - - 0 1");
        b.do_white_promotion('a','Q');
        assert(b.toFEN().equals("Q7/8/8/8/8/8/7p/8 b - - 0 1"));
        b.do_black_promotion('h','q');
        assert(b.toFEN().equals("Q7/8/8/8/8/8/8/7q w - - 0 2"));
      }
      // testing normal moves
      {
        final Board b = new Board();
        b.do_normal_white_move('e','2','e','4');
        assert(b.toFEN().equals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        b.do_normal_black_move('c','7','c','5');
        assert(b.toFEN().equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));
        b.do_normal_white_move('g','1','f','3');
        assert(b.toFEN().equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2"));
        b.do_normal_black_move('b','8','c','6');
        assert(b.toFEN().equals("r1bqkbnr/pp1ppppp/2n5/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3"));
        b.do_normal_white_move('f','1','c','4');
        assert(b.toFEN().equals("r1bqkbnr/pp1ppppp/2n5/2p5/2B1P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 3"));
        b.do_normal_black_move('d','7','d','6');
        assert(b.toFEN().equals("r1bqkbnr/pp2pppp/2np4/2p5/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 4"));
        b.do_white_kingside_castling();
        assert(b.toFEN().equals("r1bqkbnr/pp2pppp/2np4/2p5/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 1 4"));
        b.do_normal_black_move('c','8','d','7');
        assert(b.toFEN().equals("r2qkbnr/pp1bpppp/2np4/2p5/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 w kq - 2 5"));
        b.do_normal_white_move('d','2','d','4');
        assert(b.toFEN().equals("r2qkbnr/pp1bpppp/2np4/2p5/2BPP3/5N2/PPP2PPP/RNBQ1RK1 b kq d3 0 5"));
        b.do_normal_black_move('c','5','d','4');
        assert(b.toFEN().equals("r2qkbnr/pp1bpppp/2np4/8/2BpP3/5N2/PPP2PPP/RNBQ1RK1 w kq - 0 6"));
        b.do_normal_white_move('f','3','d','4');
        assert(b.toFEN().equals("r2qkbnr/pp1bpppp/2np4/8/2BNP3/8/PPP2PPP/RNBQ1RK1 b kq - 1 6"));
        b.do_normal_black_move('d','8','c','7');
        assert(b.toFEN().equals("r3kbnr/ppqbpppp/2np4/8/2BNP3/8/PPP2PPP/RNBQ1RK1 w kq - 2 7"));
        b.do_normal_white_move('b','1','c','3');
        assert(b.toFEN().equals("r3kbnr/ppqbpppp/2np4/8/2BNP3/2N5/PPP2PPP/R1BQ1RK1 b kq - 3 7"));
        b.do_black_queenside_castling();
        assert(b.toFEN().equals("2kr1bnr/ppqbpppp/2np4/8/2BNP3/2N5/PPP2PPP/R1BQ1RK1 w - - 4 8"));
      }
      // testing setting and resetting
      {
        final Board b =  new Board();
        b.set('a','1','k');
        assert(b.toFEN().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/kNBQKBNR w KQkq - 0 1"));
        b.reset('a','1');
        assert(b.toFEN().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/1NBQKBNR w KQkq - 0 1"));
      }
      // testing validation of figures
      {
        final String white_figures = "KQRBNP";
        final String black_figures = "kqrbnp";
        for (char c = '0'; c <= (char) 255; ++c) {
          final StringBuffer buf = new StringBuffer();
          buf.append(c);
          final String cs = new String(buf);
          if (white_figures.contains(cs)) {
            assert(is_valid_white_figure(c));
            assert(!is_valid_black_figure(c));
            assert(is_valid_figure(c));
          }
          else if (black_figures.contains(cs)) {
            assert(is_valid_black_figure(c));
            assert(!is_valid_white_figure(c));
            assert(is_valid_figure(c));
          }
          else {
            assert(!is_valid_black_figure(c));
            assert(!is_valid_white_figure(c));
            assert(!is_valid_figure(c));
          }
        }
      }
      // testing validFEN
      {
        assert(valid_placement("8/8/8/8/8/8/8/8"));
        assert(!valid_placement("PKQNBRkp/pPkKpP3r/8/p6P/5Qqp/8/8/2rr1Q1P"));
        assert(valid_placement("PKQNBRkp/pPkKpP1r/8/p6P/5Qqp/8/8/2rr1Q1P"));
        assert(!valid_placement("/"));
        assert(!valid_placement(" "));
        assert(!valid_placement("///////"));
        assert(!valid_placement("8/8/7/8/8/8/8/8"));
        assert(!valid_placement("8/8/8/8/8/8/8/7y"));
        assert(!valid_placement("PKNBRkkk/pPpPpP3r/8/p7P/5Qqp/8/8"));
        assert(validFEN("8/8/8/8/8/8/8/8 w - - 0 1"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w - - 0 0"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w - - -1 1"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w j - 0 1"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w QK - 0 1"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w qk - 0 1"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w kK - 0 1"));
        assert(!validFEN("8/8/8/8/8/8/8/8 w KK - 0 1"));
        assert(validFEN("8/8/8/8/8/8/8/8 b - - 6 99"));
        assert(validFEN("PKQNBR2/8/8/8/8/8/8/2pKQNBR b - - 6 99"));
        assert(validFEN("8/8/bBp5/8/8/2K4p/8/8 b Kq - 0 2"));
        assert(!validFEN("8/8/bBp5/8/8/2K3p/8/8 b Kq - 0 2"));
      }
      // testing fromFEN
      {
        final Board b = new Board();
        b.fromFEN("PKQNBR2/8/8/8/8/8/8/2pKQNBR b - - 6 99");
        assert(b.toFEN().equals("PKQNBR2/8/8/8/8/8/8/2pKQNBR b - - 6 99"));
      }
      // testing equals
      {
        /*
        final Board b = new Board();
        assert(b.equals(b));
        final Board b1 = new Board(); b1.reset('a','1');
        assert(! b.equals(b1));
        final Board b2 = new Board(); b2.active_colour = 'b';
        assert(! b.equals(b2));
        final Board b3 = new Board(); b3.white_castling = 'k';
        assert(! b.equals(b3));
        final Board b4 = new Board(); b4.black_castling = 'k';
        assert(! b.equals(b4));
        final Board b5 = new Board(); b5.en_passant = "e3";
        assert(! b.equals(b5));
        final Board b6 = new Board(); b6.halfmoves = 1;
        assert(! b.equals(b6));
        final Board b7 = new Board(); b7.fullmoves = 1;
        assert(! b.equals(b7));
        */
      }
      // testing conversions
      {

      }
    }
}
