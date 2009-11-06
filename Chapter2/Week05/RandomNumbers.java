// Oliver Kullmann, 6.11.2009 (Swansea)

class RandomNumbers {
    private static int random(final int M) {
        return 1 + (int) (Math.random() * M);
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("ERROR[RandomNumbers]: Two arguments M, N are needed.");
            return;
        }
        final int M = Integer.parseInt(args[0]);
        if (M < 1) {
            System.err.println("ERROR[RandomNumbers]: M < 1.");
            return;
        }
        final int N = Integer.parseInt(args[1]);
        if (N <= 0) return;
        int[] counts = new int[M+1];
        for (int i = 0; i < N; ++i)
            ++counts[random(M)];
        for (int i = 1; i <= M; ++i)
            System.out.println(i + ": " + ((double) counts[i] / N * 100) + "%");
    }
}

