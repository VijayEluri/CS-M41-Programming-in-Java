// Oliver Kullmann, 26.11.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class Storage {

  // Provides storage for the results of all (different) strategies against
  // each other (for a fixed deck), usable for the single results as well as
  // for the statistics.

  // Remark: One could call this class also "UpperTriangularMatrix".

  /*
    Via Strategy(5) for example storage for 5*4/2 = 10 integers is
    provided. Via set(i,j,x) then for the pairs 1 <= i < j <= 5
    the value x is stored, and retrieved with get(i,j).
  */

  public final int number_strategies;
  public final int number_results;

  Storage(final int num_strategies) {
    assert num_strategies >= 0;
    number_strategies = num_strategies;
    number_results = (number_strategies * (number_strategies-1)) / 2;
    results = new int[number_results];
  }

  int get(final int i, final int j) {
    return results[index(i,j)];
  }

  void set(final int i, final int j, final int x) {
    results[index(i,j)] = x;
  }

  private int[] results;

  // Using colexicographical order for linearisation:
  private int index(final int i, final int j) {
    assert i >= 1;
    assert i <= number_strategies;
    assert j >= 1;
    assert j <= number_strategies;
    assert i < j;
    return ((j-2)*(j-1))/2+i-1;
  }
}
