package ru.dionisius;

public class Factorial {
	
	public int n;
	
	public Factorial(int n){
		this.n=n;
	}
	
	public int factorial(){
		if (n==0||n==1) return 1;
		int factorial = 1;
		for (int i=n; n>1; n--){
			factorial*=n;
		}
		return factorial;
	}

}