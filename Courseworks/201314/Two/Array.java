 class Array {
 
   private int[] a;
   private int n;
   
   public Array() { n = 0; create(); }
   public Array(final int[] a_) {
     if (a_ == null) { n = 0; create(); }
     else {
       n = a_.length; create();
       for (int i = 0; i < n; ++i) a[i] = a_[i];
     }
   }
   
   public int size() { return n; }
   public int get(final int i) {
     if (i < 0) return get(0);
     else if (i >= n) { n = i+1; enlarge(); return 0; }
     return a[i];
   }
   public void set(final int i, final int x) {
     if (i < 0) return;
     if (i >= n) { n = i+1; enlarge();}
     a[i] = x;
   }
   
   public boolean equals(final Array rhs) {
     if (rhs.n != n) return false;
     for (int i = 0; i < n; ++i) if (a[i] != rhs.a[i]) return false;
     return true;
   }
   public String toString() {
     String res = "";
     res += n + ":";
     for (int i = 0; i < n; ++i) res += " " + a[i];
     return res;
   }
   
   private void create() { a = new int[n]; }
   private void multiply() { n = (int) 1.4 * n; }
   private void enlarge() {
     final int old_n = n;
     multiply();
     final int[] old = a;
     create();
     for (int i = 0; i < old_n; ++i) a[i] = old[i];
   }
 }
 