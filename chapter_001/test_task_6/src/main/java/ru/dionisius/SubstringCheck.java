package ru.dionisius;

public class SubstringCheck {

	String origin;
	String sub;
	
	public SubstringCheck(String origin, String sub){
		this.origin=origin;
		this.sub=sub;
	}
	
	public boolean subStringCheck(){
		char[] originArray=this.origin.toCharArray();
		char[] subArray=this.sub.toCharArray();
		int originLastIndex=originArray.length-subArray.length+1;
		for(int j=0; j<=originLastIndex; j++){
			if(subArray[0]==originArray[j]&&isArraysEqual(subArray, 1, originArray, j+1)) return true;
		}
		return false;
	}
	
	public boolean isArraysEqual(char[] arr1, int arr1StartIndex, char[] arr2, int arr2StartIndex){
		int arr1LastIndex=arr1.length-1;
		for(int i=arr1StartIndex; i<=arr1LastIndex; i++){
			if(arr1[i]!=arr2[arr2StartIndex]) return false;
			arr2StartIndex++;
		}
		return true;
	}
	
}