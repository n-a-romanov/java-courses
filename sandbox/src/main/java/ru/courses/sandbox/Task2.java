package ru.courses.sandbox;

public class Task2 {


    public static void main (String[] args) {
        Point p1 = new Point(1,1);
        Point p2 = new Point(4,5);

        System.out.println("Расстояние между точкой p1("+p1.x+","+p1.y+") и точкой p2("+p2.x+","+p2.y+") равно "+p1.distance(p2));
        }

}

