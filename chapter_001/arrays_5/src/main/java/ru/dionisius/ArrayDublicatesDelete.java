package ru.dionisius;

public class ArrayDublicatesDelete {
	String[] arr;
	
	public ArrayDublicatesDelete(String[] arr) {
		this.arr=arr;
	}
	
	public void arrayDublicatesDelete(){
		int lastIndex=arr.length-1;		
		for(int i=0; i<lastIndex; i++){
			if(arr[i]==null) continue;
			String uniqString=arr[i];
			for(int j=i; j<lastIndex; j++){
				if(arr[j+1]=="") continue;
				if(uniqString.equals(arr[j+1])) arr[j+1]="";
			}
		}
	}
}