package ru.dionisius;

public class Square {
	
	public float a;
	public float b;
	public float c;
	
	public Square(float a, float b, float c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public float calculate(float x){
		return this.a*x*x+this.b*x+this.c;
	}
	
	public void show(float start, float finish, float step){
		for (float i=start; i<=finish; i+=step){
			System.out.println("Значение выражения при х="+i+" равно: "+calculate(i));
		}
	} 
}