// Oliver Kullmann, 4.12.2009 (Swansea)
/* Copyright 2009 Oliver Kullmann */

class EvaluationResult {

  public EvaluationResult(final EvaluatedOutcome[] E) {
    assert E != null;
    results = E;
    length = results.length;
  }

  public EvaluatedOutcome get(final int index) {
    assert index >= 1;
    assert index <= length;
    return results[index-1];
  }

  public String toString() {
    String res = "";
    for (int i = 0; i < length; ++i)
      res += i + ":\n" + results[i];
    return res;
  }

  private final EvaluatedOutcome[] results;
  public final int length;

}

