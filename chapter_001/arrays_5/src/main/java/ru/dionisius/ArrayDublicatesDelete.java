package ru.dionisius;

public class ArrayDublicatesDelete {
	String[] arr;
	
	public ArrayDublicatesDelete(String[] arr) {
		this.arr=arr;
	}
	
	public void arrayDublicatesDelete(){
		int lastIndex=arr.length-1;		
		for(int i=0; i<lastIndex; i++){
			if("".equals(arr[i])) continue;
			String uniqString=arr[i];
			for(int j=i+1; j<=lastIndex; j++){
				if("".equals(arr[j])) continue;
				if(uniqString.equals(arr[j])) arr[j]="";
			}
		}
		moveNullsToTheEndOfArray();
	}
	
	public void moveNullsToTheEndOfArray(){
		int lastIndex=arr.length-1;
		String temp="";
		for(int i=0; i<lastIndex; i++){
			if("".equals(arr[i])){
				arr[i]=arr[lastIndex];
				arr[lastIndex]="";
				lastIndex--;
				i--;
			}
		}
	}
}