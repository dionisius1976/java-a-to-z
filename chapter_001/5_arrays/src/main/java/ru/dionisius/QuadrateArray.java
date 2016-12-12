package ru.dionisius;

public class QuadrateArray {
	int[][] quadrateArray;
	
	public QuadrateArray(int[][] quadrateArray){
	this.quadrateArray=quadrateArray;
	}
	
	public void rotate(){
		int[][] temp = new int[this.quadrateArray.length][this.quadrateArray.length];
		for (int i=0; i<this.quadrateArray.length; i++){
			for (int j=0; j<this.quadrateArray.length; j++){
				temp[j][i]=this.quadrateArray[i][j];
			}
		}
		this.quadrateArray=temp;
	}
}
