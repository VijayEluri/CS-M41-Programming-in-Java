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

    public static void main(final String[] args) {
        final Picture pic = XXX create a new picture using args[0] as filename XXX;
        final int width  = XXX width of pic XXX;
        final int height = XXX height of pic XXX;

        // convert to grayscale
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                final Color color = XXX colour of pixel at (i,j) in pic XXX;
                final Color gray = XXX use conversion to Gray-scale in Luminance XXX;
                XXX reset the pixel to the new colour XXX;
            }
        }
        pic.show();
    }

}
