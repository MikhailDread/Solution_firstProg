package pointquest;


import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint {

    @Test
    public void testPoint(){
        Point point1 = new Point(5, 6);
        Point point2 = new Point(2, 4);

        Assert.assertEquals(point1.distance(point2), 5, 6);
    }

    public void testSolution(){
        Point point1 = new Point(5, 6);
        Point point2 = new Point(2, 4);

        Assert.assertEquals(Solution.distance(point1, point2), 5, 6);
    }
}
