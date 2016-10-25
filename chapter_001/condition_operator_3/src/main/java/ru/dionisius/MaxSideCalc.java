package ru.dionisius;

public class MaxSideCalc {
	public Triangle triangle;
	
	public MaxSideCalc(Triangle triangle){
		this.triangle=triangle;
	}
	
	public double maxSide(){
		double firstSide=this.triangle.a.distanceTo(this.triangle.b);
		double secondSide=this.triangle.b.distanceTo(this.triangle.c);
		double thirdSide=this.triangle.c.distanceTo(this.triangle.a);
		if(firstSide<0||secondSide<0||thirdSide<0||firstSide==secondSide||secondSide==thirdSide||firstSide==thirdSide)return -1;
		if(firstSide>secondSide&&firstSide>thirdSide) return firstSide;
		if(secondSide>firstSide&&secondSide>thirdSide) return secondSide;
		else return thirdSide;
	}
}