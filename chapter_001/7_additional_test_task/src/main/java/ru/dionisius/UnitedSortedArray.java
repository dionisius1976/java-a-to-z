package ru.dionisius;

public class UnitedSortedArray {

	public int[] sortedArray(int[] sortedArr1, int[] sortedArr2){
		
		int[] unitedArray;
		int index1=sortedArr1[sortedArr1.length-1];
		int index2=sortedArr2[sortedArr2.length-1];
		
		if(index1>=index2) {
			unitedArray=getUnitedArray(sortedArr1,sortedArr2);
		}else unitedArray=getUnitedArray(sortedArr2,sortedArr1);
		
		return unitedArray;
	}
	
	private int[] getUnitedArray(int[] arr1, int[] arr2){
		
		int[] arr3=new int[(arr1.length+arr2.length)];
		int arr1Index=0;
		int arr2Index=0;
		int arr3Index=0;
				
		for (arr1Index=0; arr1Index<arr1.length; arr1Index++){
			for (arr2Index=arr2Index; arr2Index<arr2.length; arr2Index++){
				if (arr1[arr1Index]==arr2[arr2Index]) {
					arr3[arr3Index]=arr2[arr2Index];
					arr3Index++;
					arr2Index++;
					if (arr2Index==arr2.length-1&&arr1Index==arr1.length-1) {
						arr3=concatinateArrays(arr3, arr3Index, arr1, arr1Index);
						break;
					}
					if (arr2Index==arr2.length-1) {
						arr3=concatinateArrays(arr3, arr3Index+1, arr1, arr1Index+1);
						break;
					}
					break;
				}
				if (arr1[arr1Index]<arr2[arr2Index]) {
					arr3[arr3Index]=arr1[arr1Index];
					arr3Index++;
					break;
				}
				if (arr1[arr1Index]>arr2[arr2Index]) {
					arr3[arr3Index]=arr2[arr2Index];
					arr3Index++;
				}
			}
			if (arr2Index==arr2.length-1) break;
		}
		return arr3;
	}
	
	private int[] concatinateArrays(int[] arr1, int arr1Index, int[] arr2, int arr2Index){
		int[] concatinatedArray=new int[(arr1Index-1+arr2.length-arr2Index)];
		for (int i=0; i<arr1Index-1; i++){
			concatinatedArray[i]=arr1[i];
		}
		for (int i=arr1Index-1; i<concatinatedArray.length; i++){
			concatinatedArray[i]=arr2[arr2Index];
			arr2Index++;
		}
		return concatinatedArray;
	}
	
}