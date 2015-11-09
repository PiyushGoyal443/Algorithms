import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
public class Point implements Comparable<Point> {

public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

private final int x;
private final int y;

public Point(int x, int y)
{
this.x = x;
this.y = y;
}
public void draw()
{
StdDraw.point(x, y);
}
public void drawTo(Point that)
{
StdDraw.line(this.x, this.y, that.x, that.y);
}
public double slopeTo(Point that) {
double ydiff = this.y - that.y;
double xdiff = this.x - that.x;
if (xdiff == 0 && ydiff == 0)
{
return Double.NEGATIVE_INFINITY;
}
if (ydiff == 0)
{
return 0;
}
if (xdiff == 0)
{
return Double.POSITIVE_INFINITY;
}
return ydiff / xdiff;
}
public int compareTo(Point that)
{
if (this.y < that.y)
{
return -1;
}
if (this.y > that.y)
{
return 1;
}
if (this.x < that.x)
{
return -1;
}
if (this.x > that.x)
{
return 1;
}
return 0;
}
public String toString()
{
return "(" + x + ", " + y + ")";
}
private class SlopeOrder implements Comparator<Point>
{
public int compare(Point o1, Point o2)
{
double slope1 = slopeTo(o1);
double slope2 = slopeTo(o2);
if (slope1 == slope2)
{
return 0;
}
if (slope1 > slope2)
{
return 1;
}
return -1;
}
}
}
