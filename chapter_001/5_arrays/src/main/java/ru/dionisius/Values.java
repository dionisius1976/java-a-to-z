package ru.dionisius;

public class Values {
	public int[] values;
	
	public Values(int[] arr) {
		this.values=arr;
	}
	
	public void bubbleSorting(){
		for(int i=this.values.length-1; i>0; i--){
			maxToEnd(i);
		}
	}
	
	public void maxToEnd(int lastIndex){
		int temp=0;
		for (int i=0; i<lastIndex; i++){
			if(this.values[i]>this.values[i+1]){
				temp=this.values[i+1];
				this.values[i+1]=this.values[i];
				this.values[i]=temp;
			}
		}
	}
}