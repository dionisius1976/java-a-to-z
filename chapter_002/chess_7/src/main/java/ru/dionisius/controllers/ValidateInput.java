package ru.dionisius.models;

import java.util.*;

public class ValidateInput extends ConsoleInput implements Input{
		
	public int ask(String quastion, int[] range){
		boolean invalid = true;
		int coordinate = -1;
		do{
			try{
				class = super.ask(quastion, range);
				invalid = false;
			} catch(BoardSizeOutException moe){
				System.out.println("Введите координату из заданного диапазона.");
			} catch(NumberFormatException nfe){
				System.out.println("Введите число.");
			}
		} while(invalid);
		return coordinate;
	} 
}