// Oliver Kullmann, 12.11.2009 (Swansea)

class Htree2 {

    private static int message_counter = 0;

    private static void draw_H(
                               final double x,
                               final double y,
                               final double size) {
        final double x0 = x - size/2;
        final double x1 = x + size/2;
        final double y0 = y - size/2;
        final double y1 = y + size/2;
        StdDraw.line(x0, y0, x0, y1);
        StdDraw.line(x1, y0, x1, y1);
        StdDraw.line(x0,  y, x1,  y);
    }

    private static void print(final String s, final int l) {
        // l is the indentation level
        ++message_counter;
        String indent = " ";
        for (int i = 0; i < l; ++i) indent += " ";
        StdOut.printf("%4d: ", message_counter);
        System.out.print(indent + s);
    }
    private static void println(final String s, final int l) {
        print(s,l);
        System.out.println();
    }

    // print message, and then wait:
    private static void wait(final String message, final int l) {
        println(message,l);
        print("Please enter a digit to continue:",l);
        StdIn.readInt();
    }

    private static void draw(
                             final int n,
                             final double x,
                             final double y,
                             final double size) {
        assert n >= 1;
        println("Enter \"draw\" at level " + n + ".", n);
        draw_H(x, y, size);
        if (n == 1) {
            println("After simple printing, leaving \"draw\" at level " + n + ".", n);
            return;
        }
        final double x0 = x - size/2;
        final double x1 = x + size/2;
        final double y0 = y - size/2;
        final double y1 = y + size/2;
        wait("Next drawing lower left H-tree at level " + (n-1) + ".", n);
        draw(n-1, x0, y0, size/2);
        wait("Next drawing upper left H-tree at level " + (n-1) + ".", n);
        draw(n-1, x0, y1, size/2);
        wait("Next drawing lower right H-tre at level " + (n-1) + ".", n);
        draw(n-1, x1, y0, size/2);
        wait("Next drawing upper right H-tree at level " + (n-1) + ".", n);
        draw(n-1, x1, y1, size/2);
        println("Finally leaving \"draw\" at level " + n + ".", n);
    }

    public static void main(String[] args) {
        final int N = Integer.parseInt(args[0]);
        if (N < 0) {
            System.err.println("ERROR[Htree2]: N < 0.");
            return;
        }
        if (N == 0) return;
        draw(N, .5, .5, .5);
        System.out.println("End: Now you need to press Ctrl-C.");
    }
}
