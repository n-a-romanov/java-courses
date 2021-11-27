package ru.courses.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 {

    @Test
    public void testValid5Distance() {
        Point p1=new Point(1,1);
        Point p2=new Point(4,5);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testValid0Distance() {
        Point p1=new Point(0,0);
        Point p2=new Point(0,0);
        Assert.assertEquals(p1.distance(p2), 0);
    }

    @Test
    public void testValidNegativeXDistance() {
        Point p1=new Point(0,0);
        Point p2=new Point(-20,0);
        Assert.assertEquals(p1.distance(p2), 20);
    }

    @Test
    public void testValidNegativeYDistance() {
        Point p1=new Point(0,-20);
        Point p2=new Point(-20,0);
        Assert.assertEquals(p1.distance(p2), 28.284271247461902);
    }
}
