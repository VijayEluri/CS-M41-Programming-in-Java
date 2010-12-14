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
        if (! Board.is_valid_white_figure(B.get(file0,rank0))) return false;
        if (! B.is_empty(file1,rank1) && ! Board.is_valid_black_figure(B.get(file1,rank1)))
          return false;
        if (B.get_active_colour() != 'w') return false;
        if (! check_move_simple(file0,rank0,file1,rank1)) return false;
        if (! regular) return true;
        final Board test_board = new Board(B);
        test_board.normal_white_move_0(file0,rank0,file1,rank1);
        char file_king, rank_king = 0;
        for (file_king = 'a'; file_king <= 'h'; ++file_king)
          for (rank_king = '1'; rank_king <= '8'; ++rank_king)
            if (test_board.get(file_king,rank_king) == Board.white_king) break;
        final Moves test_move = new Moves(test_board);
        return test_move.black_not_attacking(file_king,rank_king);
    }
    public boolean check_normal_black_move(final char file0, final char rank0,
                                           final char file1, final char rank1) {
        if (! Board.is_valid_black_figure(B.get(file0,rank0))) return false;
        if (! B.is_empty(file1,rank1) && ! Board.is_valid_white_figure(B.get(file1,rank1)))
          return false;
        if (B.get_active_colour() != 'b') return false;
        if (! check_move_simple(file0,rank0,file1,rank1)) return false;
        if (! regular) return true;
        final Board test_board = new Board(B);
        test_board.normal_black_move_0(file0,rank0,file1,rank1);
        char file_king, rank_king = 0;
        for (file_king = 'a'; file_king <= 'h'; ++file_king)
          for (rank_king = '1'; rank_king <= '8'; ++rank_king)
            if (test_board.get(file_king,rank_king) == Board.black_king) break;
        final Moves test_move = new Moves(test_board);
        return test_move.white_not_attacking(file_king,rank_king);
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
      return Math.abs(file0-file1) <= 1 && Math.abs(rank0-rank1) <= 1;
    }
    private boolean check_queen_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      return check_rook_move(file0,rank0,file1,rank1) || check_bishop_move(file0,rank0,file1,rank1);
    }
    private boolean check_rook_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      if (file0 == file1) {
        final int step = (rank0 < rank1) ? +1 : -1;
        for (char rank = (char)(rank0+step); rank != rank1; rank+=step)
          if (! B.is_empty(file0,rank)) return false;
        return true;
      }
      else if (rank0 == rank1) {
        final int step = (file0 < file1) ? +1 : -1;
        for (char file = (char)(file0+step); file != file1; file+=step)
          if (! B.is_empty(file,rank0)) return false;
        return true;
      }
      else return false;
    }
    private boolean check_bishop_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      if (file0 == file1 || rank0 == rank1) return false;
      else {
        final int step_f = (file0 < file1) ? +1 : -1;
        final int step_r = (rank0 < rank1) ? +1 : -1;
        char file = (char)(file0+step_f), rank = (char)(rank0+step_r);
        while (file != file1) {
          if (! B.is_empty(file,rank)) return false;
          file += step_f; rank += step_r;
        }
        return rank == rank1;
      }
    }
    private boolean check_knight_move(final char file0, final char rank0,
                                    final char file1, final char rank1) {
      return (Math.abs(file0-file1) == 1 && Math.abs(rank0-rank1) == 2) ||
        (Math.abs(file0-file1) == 2 && Math.abs(rank0-rank1) == 1);
    }
    private boolean check_white_pawn_move(final char file0, final char rank0,
                                          final char file1, final char rank1) {
      final int diff_f = Math.abs(file0-file1), diff_r = rank1-rank0;
      if (diff_f > 1 || diff_r <= 0 || diff_r > 2) return false;
      if (diff_r == 2)
        return diff_f == 0 && rank0 == '2' && rank1 == '4' && B.is_empty(file0,'3');
      if (diff_f == 0) return true;
      return ! B.is_empty(file1,rank1) || Board.eq_filerank(file1,rank1,B.get_en_passant());
    }
    private boolean check_black_pawn_move(final char file0, final char rank0,
                                          final char file1, final char rank1) {
      final int diff_f = Math.abs(file0-file1), diff_r = rank0-rank1;
      if (diff_f > 1 || diff_r <= 0 || diff_r > 2) return false;
      if (diff_r == 2)
        return diff_f == 0 && rank0 == '7' && rank1 == '5' && B.is_empty(file0,'6');
      if (diff_f == 0) return true;
      return ! B.is_empty(file1,rank1) || Board.eq_filerank(file1,rank1,B.get_en_passant());
    }
    
    public boolean check_white_kingside_castling() {
        final char c = B.get_white_castling();
        if (c == '-' || c == 'q') return false;
        if (B.get_active_colour() == 'b') return false;
        if (B.get('e','1') != 'K') return false;
        if (B.get('h','1') != 'R') return false;
        if (! black_not_attacking('e','1')) return false;
        if (! free_white('f','1')) return false;
        if (! free_white('g','1')) return false;
        if (! black_not_attacking('h','1')) return false;
        return true;
    }
    public boolean check_white_queenside_castling() {
        final char c = B.get_white_castling();
        if (c == '-' || c == 'k') return false;
        if (B.get_active_colour() == 'b') return false;
        if (B.get('e','1') != 'K') return false;
        if (B.get('a','1') != 'R') return false;
        if (! black_not_attacking('e','1')) return false;
        if (! free_white('d','1')) return false;
        if (! free_white('c','1')) return false;
        if (! free_white('b','1')) return false;
        if (! black_not_attacking('a','1')) return false;
        return true;
    }
    public boolean check_black_kingside_castling() {
        final char c = B.get_black_castling();
        if (c == '-' || c == 'q') return false;
        if (B.get_active_colour() == 'w') return false;
        if (B.get('e','8') != 'k') return false;
        if (B.get('h','8') != 'r') return false;
        if (! white_not_attacking('e','8')) return false;
        if (! free_black('f','8')) return false;
        if (! free_black('g','8')) return false;
        if (! white_not_attacking('h','8')) return false;
        return true;
    }
    public boolean check_black_queenside_castling() {
        final char c = B.get_black_castling();
        if (c == '-' || c == 'k') return false;
        if (B.get_active_colour() == 'w') return false;
        if (B.get('e','8') != 'k') return false;
        if (B.get('a','8') != 'r') return false;
        if (! white_not_attacking('e','8')) return false;
        if (! free_black('d','8')) return false;
        if (! free_black('c','8')) return false;
        if (! free_black('b','8')) return false;
        if (! white_not_attacking('a','8')) return false;
        return true;
    }

    public boolean check_white_promotion(final char pawn_file, final char figure) {
        if (B.get_active_colour() != 'w') return false;
        if (B.get(pawn_file,'7') != 'P') return false;
        if (! Board.is_valid_white_figure(figure)) return false;
        if (figure == 'P' || figure == 'K') return false;
        if (! regular) return true;
        final Board test_board = new Board(B);
        test_board.white_promotion_0(pawn_file,figure);
        char file_king, rank_king = 0;
        for (file_king = 'a'; file_king <= 'h'; ++file_king)
          for (rank_king = '1'; rank_king <= '8'; ++rank_king)
            if (test_board.get(file_king,rank_king) == Board.white_king) break;
        final Moves test_move = new Moves(test_board);
        return test_move.black_not_attacking(file_king,rank_king);
    }
    public boolean check_black_promotion(final char pawn_file, final char figure) {
        if (B.get_active_colour() != 'b') return false;
        if (B.get(pawn_file,'2') != 'p') return false;
        if (! Board.is_valid_black_figure(figure)) return false;
        if (figure == 'p' || figure == 'k') return false;
        if (! regular) return true;
        final Board test_board = new Board(B);
        test_board.black_promotion_0(pawn_file,figure);
        char file_king, rank_king = 0;
        for (file_king = 'a'; file_king <= 'h'; ++file_king)
          for (rank_king = '1'; rank_king <= '8'; ++rank_king)
            if (test_board.get(file_king,rank_king) == Board.black_king) break;
        final Moves test_move = new Moves(test_board);
        return test_move.white_not_attacking(file_king,rank_king);
    }

    // checks whether black doesn't attack the field:
    public boolean black_not_attacking(final char file, final char rank) {
        // XXX
        return true;
    }
    public boolean free_white(final char file, final char rank) {
        // XXX
        return black_not_attacking(file,rank) && B.is_empty(file,rank);
    }
    // checks whether white doesn't attack the field:
    public boolean white_not_attacking(final char file, final char rank) {
        // XXX
        return true;
    }
    public boolean free_black(final char file, final char rank) {
        // XXX
        return white_not_attacking(file,rank) && B.is_empty(file,rank);
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
