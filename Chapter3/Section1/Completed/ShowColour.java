import java.awt.Color;
class ShowColour {
  static final int d = 256;
  public static void show1(final int r, final int g, final int b) {
    StdDraw.setCanvasSize(d,d);
    StdDraw.clear(new Color(r,g,b));
  }
  public static void show2(final int r, final int g, final int b) {
    final Picture p = new Picture(d,d);
    final Color c = new Color(r,g,b);
    for (int i = 0; i < d; ++i)
      for (int j = 0; j < d; ++j)
        p.set(i,j,c);
    p.show();
  }
  // using two methods (possibly the picture-windows sit on top of each other):
  public static void main(final String[] args) {
    final int r = Integer.parseInt(args[0]);
    final int g = Integer.parseInt(args[1]);
    final int b = Integer.parseInt(args[2]);
    show1(r,g,b);
    show2(r,g,b);
  }
}
