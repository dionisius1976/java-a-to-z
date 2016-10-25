package ru.dionisius;

//import static java.lang.math.*;

public class Triangle {
	public Point a;
	public Point b;
	public Point c;

	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double area() {
		double firstSide=this.a.distanceTo(this.b);
		double secondSide=this.b.distanceTo(this.c);
		double thirdSide=this.c.distanceTo(this.a);
		
		if (firstSide>0&&secondSide>0&&thirdSide>0) {
			double perimeter=(firstSide+secondSide+thirdSide)/2;
			return Math.sqrt(perimeter*(perimeter-firstSide)*(perimeter-secondSide)*(perimeter-thirdSide));
			}
		else return -1;
	}
}