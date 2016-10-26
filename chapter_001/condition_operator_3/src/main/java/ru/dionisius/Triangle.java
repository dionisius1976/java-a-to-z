package ru.dionisius;

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
		double ab=this.a.distanceTo(this.b);
		double bc=this.b.distanceTo(this.c);
		double ca=this.c.distanceTo(this.a);
		
		if (ab>0&&bc>0&&ca>0) {
			double perimeter=(ab+bc+ca)/2;
			return Math.sqrt(perimeter*(perimeter-ab)*(perimeter-bc)*(perimeter-ca));
			}
		else return -1;
	}
	
	public static double maxSide(double ... sides){
		double maxSide=0;
		for(double currentSide: sides){
			maxSide = maxSide<currentSide?currentSide:maxSide;
		}
		return maxSide;
		
	}
	
	public static void main(String ... args){
		if(args.length>6) System.out.println("Вы ввели более шести координат!");
		
		Point a = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		Point b = new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		Point c = new Point(Integer.parseInt(args[4]), Integer.parseInt(args[5]));
		Triangle triangle=new Triangle(a, b, c);
		
		double ab=a.distanceTo(b);
		double bc=b.distanceTo(c);
		double ca=c.distanceTo(a);
		
		System.out.println("Максимальная длина стороны равна: "+Triangle.maxSide(ab, bc, ca));
		
		System.out.println("Площадь данного треугольника равна: "+triangle.area());
		
		if(ab<0||bc<0||ca<0) System.out.println("Данная фигура не является тругольником! Длина одной из сторон равна нулю.");
		
	}

}