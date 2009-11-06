// Oliver Kullmann, 6.11.2009 (Swansea)

class StringExample {

    static final int size_a = 4;
    static final int size_b = 3;

    static final String[] a = {"AB", "XYZ", "EFG", "1234"};
    static final String[] b = {"M1", "T3", "y6"};

    private static int check_a(final String x) {
        for (int i = 0; i < size_a; ++i)
            if (x.equals(a[i])) return i;
        return -1;
    }
    private static int check_b(final String x) {
        for (int i = 0; i < size_b; ++i)
            if (x.equals(b[i])) return i;
        return -1;
    }

    public static void main(String[] args) {
        while (! StdIn.isEmpty())
            System.out.println(check_a(StdIn.readString()) + " " + check_b(StdIn.readString()));
    }
}
