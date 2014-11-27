import java.awt.Color;
class ExtractColours {
  public static void main(final String[] args) {
    final Picture pic = new Picture(args[0]);
    pic.show();
    final int w = pic.width(), h = pic.height();
    final Picture pic_r = new Picture(w,h), pic_g = new Picture(w,h),
      pic_b = new Picture(w,h);
    for (int i = 0; i < w; ++i)
      for (int j = 0; j < h; ++j) {
        final Color c = pic.get(i,j);
        pic_r.set(i,j,new Color(c.getRed(),0,0));
        pic_g.set(i,j,new Color(0,c.getGreen(),0));
        pic_b.set(i,j,new Color(0,0,c.getBlue()));
      }
    pic_r.show(); pic_g.show(); pic_b.show();
  }
}
