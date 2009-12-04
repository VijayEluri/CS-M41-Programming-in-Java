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

  public Storage(final int num_strategies) {
    assert num_strategies >= 0;
    number_strategies = num_strategies;
    number_results = (number_strategies * (number_strategies-1)) / 2;
    results = new int[number_results];
  }

  public int get(final int i, final int j) {
    return results[index(i,j)];
  }

  public void set(final int i, final int j, final int x) {
    results[index(i,j)] = x;
  }

  public String toString() {
    String result = "";
    for (int i = 1; i < number_strategies; ++i) {
      for (int j = i+1; j <= number_strategies; ++j)
        result += get(i,j) + " ";
      result += "\n";
    }
    return result;
  }

  public boolean equals(final Storage S) {
    if (S.number_strategies != number_strategies)
      return false;
    for (int i = 1; i < number_strategies; ++i)
      for (int j = i+1; j <= number_strategies; ++j)
        if (S.get(i,j) != get(i,j))
          return false;
    return true;
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


  // Tests:
  public static void main(String[] args) {
    final Storage S = new Storage(5);
    for (int i = 1; i < 5; ++i)
      for (int j = i+1; j <= 5; ++j)
        S.set(i,j,i*j);
    for (int i = 1; i < 5; ++i)
      for (int j = i+1; j <= 5; ++j)
        assert(S.get(i,j) == i*j);
  }
}
