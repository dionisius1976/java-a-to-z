package ru.dionisius;

//import static java.lang.math.*;

public class Point {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(Point point) {
		double distance=Math.sqrt((point.x - this.x)*(point.x - this.x)+(point.y - this.y)*(point.y - this.y));
		if (distance>0) return distance;
		else return -1;
		}
	}




