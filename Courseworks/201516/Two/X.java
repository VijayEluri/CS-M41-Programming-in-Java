class X {
  private final String s;
  private boolean b;
  private int i;
  private double d;

  public X(final String s_) {
    s = s_;
    b = false; i = 0; d = 0;
  }

  public boolean to_boolean() {
    if (s.equals("true")) {b=true; return true;}
    else if (s.equals("false")) {b=false; return true;}
    else return false;
  }
  public boolean to_int() {
    try {i = Integer.parseInt(s);}
    catch (final Exception e) {return false;}
    return true;
  }
  public boolean to_double() {
    try {d = Double.parseDouble(s);}
    catch (final Exception e) {return false;}
    return true;
  }

  public boolean b() {return b;}
  public int i() {return i;}
  public double d() {return d;}

}
