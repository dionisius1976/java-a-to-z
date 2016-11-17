package ru.dionisius.start;

import java.util.*;

public class ConsoleInput implements Input {
	
	private Scanner scanner = new Scanner(System.in);
	
	public String ask(String quastion){
		System.out.print(quastion);
		return scanner.nextLine();
	}
	
	public int ask(String quastion, int[] range){
		int key = Integer.valueOf(this.ask(quastion));
		boolean exist = false;
		for (int value: range){
			if(value == key){
				exist = true;
				break;
			}
		}
		if(exist){
			return key;
		} else {
			throw new MenuOutException("Выход из диапазона возможных значений!");
		}
		
	}
	
}