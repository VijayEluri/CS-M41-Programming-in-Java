public class Plateau2 {
  public static void main(final String[] args) {
    final int N = args.length;
    if (N == 0) { System.out.println(0); return; }
    int max_plateau = 0;
    int curr_plateau = 1;
    int old_val = Integer.parseInt(args[0]), curr_val;
    for (int i = 1; i < N; ++i, old_val = curr_val) {
      curr_val = Integer.parseInt(args[i]);
      if (curr_val < old_val) {
        if (curr_plateau > max_plateau) max_plateau = curr_plateau;
        curr_plateau = -1;
      }
      else if (curr_val == old_val && curr_plateau != -1) ++curr_plateau;
      else curr_plateau = 1;
    }
    if (curr_plateau > max_plateau) max_plateau = curr_plateau;
    System.out.println(max_plateau);
  }
}
