class X {
  private final int l;
  private final long[] count;
  
  public X(final int length) {
    assert(length > 0);
    l = length;
    count = new long[l];
  }
  
  public int length() { return l; }
  public void inc(int i) {
    assert(i >= 0);
    assert(i < l);
    ++count[i];
  }
  public long read(int i) {
    assert(i >= 0);
    assert(i < l);
    return count[i];
  }
  public long sum() {
    long s = 0;
    for (int i = 0; i < l; ++i) s += count[i];
    return s;
  }
  
  public boolean equals(final X other) {
    final boolean comp = l <= other.l;
    final long[] a = (comp) ? count : other.count;
    final long[] b = (comp) ? other.count : count;
    final int la = (comp) ? l : other.l;
    final int lb = (comp) ? other.l : l;
    for (int i = 0; i < la; ++i) if (a[i] != b[i]) return false;
    for (int i = la; i < lb; ++i) if (b[i] != 0) return false;
    return true;
  }
  public String toString() {
    assert(l >= 1);
    String res = "";
    for (int i = 0; i < l-1; ++i) res += count[i] + " ";
    res += count[l-1];
    return res;
  }
}
