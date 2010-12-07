// Oliver Kullmann, 6.12.2010 (Swansea)

class Moves {

    private final Board B;
    public Moves(final Board b) { B = b; }

    public boolean check_normal_white_move(final char file0, final char rank0,
                                           final char file1, final char rank1) {
        // XXX
        return false;
    }
    public boolean check_normal_black_move(final char file0, final char rank0,
                                           final char file1, final char rank1) {
        // XXX
        return false;
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
        return false;
    }
    public boolean check_black_kingside_castling() {
        // only demonstration code:
        final char c = B.get_black_castling();
        if (c == '-' || c == 'q') return false;
        if (B.get_active_colour() == 'w') return false;
        // XXX
        return false;
    }
    public boolean check_black_queenside_castling() {
        // only demonstration code:
        final char c = B.get_black_castling();
        if (c == '-' || c == 'k') return false;
        if (B.get_active_colour() == 'w') return false;
        // XXX
        return false;
    }

    // checks whether black doesn't attack the field:
    public boolean black_not_attacking(final char file, final char rank) {
        // XXX
        return false;
    }
    public boolean free_white(final char file, final char rank) {
        // XXX
        return black_not_attacking(file,rank) && B.get(file,rank) == Board.empty;
    }
    // checks whether white doesn't attack the field:
    public boolean white_not_attacking(final char file, final char rank) {
        // XXX
        return false;
    }
    public boolean free_black(final char file, final char rank) {
        // XXX
        return white_not_attacking(file,rank) && B.get(file,rank) == Board.empty;
    }

}
