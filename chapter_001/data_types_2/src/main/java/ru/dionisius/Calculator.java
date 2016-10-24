package ru.dionisius;

public class Calculator {
	double result;
	public void add(double first, double second) {
		this.result=first+second;
	}
	public void substruct(double first, double second) {
		this.result=first-second;
	}
	public void div(double first, double second) {
		this.result=first/second;
	}
	public void multiple(double first, double second) {
		this.result=first*second;
	}
}

