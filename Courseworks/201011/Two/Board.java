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
        // XXX
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
        // XXX
    }

    public void do_white_promotion(final char pawn_file, final char figure) {
        assert(M.check_white_promotion(pawn_file, figure));
        // XXX
    }
    public void do_black_promotion(final char pawn_file, final char figure) {
        assert(M.check_black_promotion(pawn_file, figure));
        // XXX
    }

    public void do_normal_white_move(final char file0, final char rank0,
                                     final char file1, final char rank1) {
        assert(M.check_normal_white_move(file0,rank0,file1,rank1));
        // XXX
    }
    public void do_normal_black_move(final char file0, final char rank0,
                                     final char file1, final char rank1) {
        assert(M.check_normal_black_move(file0,rank0,file1,rank1));
        // XXX
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
        boolean result = true;
        // XXX
        return result;
    }
    // auxiliary functions for checking the six fields of a fen-record:
    private static boolean valid_placement(final String p) {
      assert(! p.isEmpty());
      // XXX
      return true;
    }
    // helper function for valid_placement:
    private static boolean check_fen_row(final String row) {
      assert(! row.isEmpty());
      // XXX
      return true;
    }
    private static boolean valid_colour(final String c) {
      assert(! c.isEmpty());
      // XXX
      return true;
    }
    private static boolean valid_castling(final String c) {
      assert(! c.isEmpty());
      // XXX
      return true;
    }
    private static boolean valid_enpassant(final String e) {
      assert(! e.isEmpty());
      // XXX
      return true;
    }
    private static boolean valid_halfmoves(final String h) {
      assert(! h.isEmpty());
      // XXX
      return true;
    }
    private static boolean valid_fullmoves(final String f) {
      assert(! f.isEmpty());
      // XXX
      return true;
    }

    // set the position according to description in Forsyth-Edwards notation:
    private void fromFEN(final String position) {
        assert(validFEN(position));
        // XXX
    }
    // describe the position in Forsyth-Edwards notation:
    public String toFEN() {
        String result = "";
        // XXX
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
        /*
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
        */
      }
      // testing construction from fen
      {

      }
      // testing get-functions
      {

      }
      // testing castling moves
      {

      }
      // testing promotion moves
      {

      }
      // testing normal moves
      {
        final Board b = new Board();
        /*
        b.do_normal_white_move('e','2','e','4');
        assert(b.toFEN().equals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        b.do_normal_black_move('c','7','c','5');
        assert(b.toFEN().equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));
        b.do_normal_white_move('g','1','f','3');
        assert(b.toFEN().equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2"));
        */
      }
      // testing seting and reseting
      {

      }
      // testing validation of figures
      {

      }
      // testing validFEN
      {
        /*
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
        */
      }
      // testing fromFEN
      {

      }
      // testing toFEN
      {

      }
      // testing equals
      {

      }
      // testing conversions
      {

      }
    }
}
