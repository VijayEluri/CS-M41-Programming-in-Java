/*************************************************************************
 *  Compilation:  javac Grayscale.java
 *  Execution:    java Grayscale filename
 *
 *  Reads in an image from a file, converts it to grayscale, and
 *  displays it on the screen.
 *
 *  % java Grayscale image.jpg
 *
 *************************************************************************/

import java.awt.Color;

public class Grayscale {

    public static void main(String[] args) {
        Picture pic = new Picture(args[0]);
        int width  = pic.width();
        int height = pic.height();

        // convert to grayscale
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = pic.get(i, j);
                Color gray = Luminance.toGray(color);
                pic.set(i, j, gray);
            }
        }
        pic.show();
    }

}
