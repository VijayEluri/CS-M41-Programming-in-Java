
/*************************************************************************
 *  Compilation:  javac PlotFilter.java
 *  Execution:    java PlotFilter < input.txt
 *  Dependencies: StdDraw.java StdIn.java
 *  
 *  % java PlotFilter < USA.txt
 *
 *  Datafiles:    http://www.cs.princeton.edu/IntroProgramming/15inout/USA.txt
 *
 *************************************************************************/

public class PlotFilter { 

    public static void main(final String[] args) {

        // read in bounding box and rescale
        final double xmin = StdIn.readDouble();
        final double ymin = StdIn.readDouble();
        final double xmax = StdIn.readDouble();
        final double ymax = StdIn.readDouble();
        StdDraw.setXscale(xmin, xmax);
        StdDraw.setYscale(ymin, ymax);

        // turn on animation mode to defer displaying all of the points
        // StdDraw.show(0);

        // plot points, one at a time
        while (!StdIn.isEmpty()) {
            final double x = StdIn.readDouble();
            final double y = StdIn.readDouble();
            StdDraw.point(x, y);
        }

        // display all of the points now
        // StdDraw.show(0);

    }
}
