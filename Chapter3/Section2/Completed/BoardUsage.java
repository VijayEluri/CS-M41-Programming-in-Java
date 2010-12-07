class BoardUsage {
    public static void main(final String[] args) {
        final Board B = new Board();
        // set directly a few fields, and print out board
        B.set('e','1','K');
        B.set('e','8','k');
        System.out.println(B);
        // reset some of the fields, and print out board
        B.reset('a','1');
        System.out.println(B);
        // now do white and black king-side castlings, and print out boards
        B.set('h','1','R');
        B.active_colour = 'w';
        B.white_castling = 'k';
        B.halfmoves = 0;
        B.fullmoves = 1;
        System.out.println(B);
        B.do_white_kingside_castling();
        System.out.println(B);
    }
}
