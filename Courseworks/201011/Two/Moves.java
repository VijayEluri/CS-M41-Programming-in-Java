// Oliver Kullmann, 6.12.2010 (Swansea)

class Moves {

    private final Board B;
    private boolean regular;
    public Moves(final Board b) { B = b; regular = regular_position(); }

    public boolean get_regular_position() { return regular; }
    public void set_regular_position(final boolean new_reg) {
      regular = new_reg;
    }

    // checking whether B represents a "normal" position or not;
    // if not, then only simple checks regarding move-correctness should
    // be performed, only checking the direct characteristics of the figure
    // moved;
    // checks whether there is exactly one king of each colour, there are
    // no more figures than promotions allow, and there are no pawns on the
    // first or last rank;
    public boolean regular_position() {
      int[] counts = new int[256];
      for (char file = 'a'; file <= 'h'; ++file)
        for (char rank = '1'; rank <= '8'; ++rank)
          ++counts[(int) B.get(file,rank)];
      if (counts[Board.white_king] != 1 || counts[Board.black_king] != 1)
        return false;
      if (counts[Board.white_pawn] > 8 || counts[Board.black_pawn] > 8)
        return false;
      int count_w_promotions = 0;
      count_w_promotions += Math.max(counts[Board.white_queen]-1,0);
      count_w_promotions += Math.max(counts[Board.white_rook]-2,0);
      count_w_promotions += Math.max(counts[Board.white_bishop]-2,0);
      count_w_promotions += Math.max(counts[Board.white_knight]-2,0);
      if (count_w_promotions > 8 - counts[Board.white_pawn]) return false;
      int count_b_promotions = 0;
      count_b_promotions += Math.max(counts[Board.black_queen]-1,0);
      count_b_promotions += Math.max(counts[Board.black_rook]-2,0);
      count_b_promotions += Math.max(counts[Board.black_bishop]-2,0);
      count_b_promotions += Math.max(counts[Board.black_knight]-2,0);
      if (count_b_promotions > 8 - counts[Board.black_pawn]) return false;
      for (char file = 'a'; file <= 'h'; ++file) {
        final char fig1 = B.get(file,'1');
        if (fig1 == Board.white_pawn || fig1 == Board.black_pawn) return false;
        final char fig8 = B.get(file,'8');
        if (fig8 == Board.white_pawn || fig8 == Board.black_pawn) return false;
      }
      return true;
    }

    public boolean check_normal_white_move(final char file0, final char rank0,
                                           final char file1, final char rank1) {
        final char fig = B.get(file0,rank0);
        if (! Board.is_valid_white_figure(fig)) return false;
        if (B.get(file1,rank1) == Board.empty && ! Board.is_valid_black_figure(fig))
          return false;
        if (B.get_active_colour() != 'w') return false;
        if (! check_move_simple(file0,rank0,file1,rank1)) return false;
        if (! regular) return true;
        final Board test_board = new Board(B);
        test_board.normal_white_move_0(file0,rank0,file1,rank1);
        char file_king = 0, rank_king = 0;
        for (file_king = 'a'; file_king <= 'h'; ++file_king)
          for (rank_king = '1'; rank_king <= '8'; ++rank_king)
            if (B.get(file_king,rank_king) == Board.white_king) break;
        return black_not_attacking(file_king,rank_king);
    }
    public boolean check_normal_black_move(final char file0, final char rank0,
                                           final char file1, final char rank1) {
        // XXX
        return true;
    }

    // for checking a normal move by just applying the move-rules
    private boolean check_move_simple(final char file0, final char rank0,
                                      final char file1, final char rank1) {
        final char fig = B.get(file0,rank0);
        if (fig == Board.white_king || fig == Board.black_king)
          return check_king_move(file0,rank0,file1,rank1);
        if (fig == Board.white_queen || fig == Board.black_queen)
          return check_queen_move(file0,rank0,file1,rank1);
        if (fig == Board.white_rook || fig == Board.black_rook)
          return check_rook_move(file0,rank0,file1,rank1);
        if (fig == Board.white_bishop || fig == Board.black_bishop)
          return check_bishop_move(file0,rank0,file1,rank1);
        if (fig == Board.white_knight || fig == Board.black_knight)
          return check_knight_move(file0,rank0,file1,rank1);
        if (fig == Board.white_pawn)
          return check_white_pawn_move(file0,rank0,file1,rank1);
        else
          return check_black_pawn_move(file0,rank0,file1,rank1);
    }
    private boolean check_king_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      // XXX
      return true;
    }
    private boolean check_queen_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      // XXX
      return true;
    }
    private boolean check_rook_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      // XXX
      return true;
    }
    private boolean check_bishop_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      // XXX
      return true;
    }
    private boolean check_knight_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      // XXX
      return true;
    }
    private boolean check_white_pawn_move(final char file0, final char rank0,
                                          final char file1, final char rank1) {
      // XXX
      return true;
    }
    private boolean check_black_pawn_move(final char file0, final char rank0,
                                          final char file1, final char rank1) {
      // XXX
      return true;
    }
    
    public boolean check_white_kingside_castling() {
        // only demonstration code:
        final char c = B.get_white_castling();
        if (c == '-' || c == 'q') return false;
        if (B.get_active_colour() == 'b') return false;
        if (B.get('e','1') != 'K') return false;
        if (! black_not_attacking('e','1')) return false;
        if (! free_white('f','1')) return false;
        // XXX
        return true;
    }
    public boolean check_white_queenside_castling() {
        // only demonstration code:
        final char c = B.get_white_castling();
        if (c == '-' || c == 'k') return false;
        if (B.get_active_colour() == 'b') return false;
        // XXX
        return true;
    }
    public boolean check_black_kingside_castling() {
        // only demonstration code:
        final char c = B.get_black_castling();
        if (c == '-' || c == 'q') return false;
        if (B.get_active_colour() == 'w') return false;
        // XXX
        return true;
    }
    public boolean check_black_queenside_castling() {
        // only demonstration code:
        final char c = B.get_black_castling();
        if (c == '-' || c == 'k') return false;
        if (B.get_active_colour() == 'w') return false;
        // XXX
        return true;
    }

    public boolean check_white_promotion(final char pawn_file, final char figure) {
        // XXX
        return true;
    }
    public boolean check_black_promotion(final char pawn_file, final char figure) {
        // XXX
        return true;
    }

    // checks whether black doesn't attack the field:
    public boolean black_not_attacking(final char file, final char rank) {
        // XXX
        return true;
    }
    public boolean free_white(final char file, final char rank) {
        // XXX
        return black_not_attacking(file,rank) && B.get(file,rank) == Board.empty;
    }
    // checks whether white doesn't attack the field:
    public boolean white_not_attacking(final char file, final char rank) {
        // XXX
        return true;
    }
    public boolean free_black(final char file, final char rank) {
        // XXX
        return white_not_attacking(file,rank) && B.get(file,rank) == Board.empty;
    }


    public static void main(final String[] args) {
      // checking regular_position
      {
        Moves m = new Moves(new Board());
        assert(m.regular_position());
        m = new Moves(new Board("8/8/8/8/8/8/8/8 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("KK6/8/8/8/8/8/8/8 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("kk6/8/8/8/8/8/8/8 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/8/8/8/8/8/8/8 w - - 0 1"));
        assert(m.regular_position());
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/8 w - - 0 1"));
        assert(m.regular_position());
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/n7 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/N7 w - - 0 1"));
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/b7 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/B7 w - - 0 1"));
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/r7 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/R7 w - - 0 1"));
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/q7 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/qqqqqqqq/QQQQQQQQ/Q7/q7/rrbbnn2/RRBBNN2/Q7 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kkp5/8/8/8/8/8/8/8 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("KkP5/8/8/8/8/8/8/8 w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/8/8/8/8/8/8/7p w - - 0 1"));
        assert(!m.regular_position());
        m = new Moves(new Board("Kk6/8/8/8/8/8/8/7P w - - 0 1"));
        assert(!m.regular_position());
      }
    }

}
