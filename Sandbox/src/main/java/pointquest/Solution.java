package pointquest;

public class Solution {

	public static void main(String [] args){

		Point p1 = new Point(5, 6);
		Point p2 = new Point(2, 4);

		System.out.println(p1.distance(p2));

		System.out.println(distance(p1, p2));

	}

	public static double distance(Point p1, Point p2){

		double b = Math.sqrt( Math.pow((p2.x - p1.x), 2)+Math.pow((p2.y - p1.y), 2));
		return b;
	}

}