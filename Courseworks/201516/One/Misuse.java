class Misuse {
  public static void main(final String[] args) {
    final int min = -2147483648, max = 2147483647;

    // Three arguments, not using long for summation:
    System.out.println(max + 2 + max);
    System.out.println(min + (min+1) -1);

    // Five (or more) arguments, wrong handling of inputs:
    System.out.println(-min);
    // More than five arguments, using double for summation:
   {
     long sum_l = 0;
     double sum_d = 0;
     int i = 0;
     while (sum_l == sum_d) { sum_l += max; sum_d += max; ++i; }
     System.out.println(i + ": " + sum_l + " " + sum_d);
    }
  }
}
