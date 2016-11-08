package ru.dionisius.start;
import java.util.*;

public class ConsoleInput implements Input {
	
	private Scanner scanner = new Scanner(System.in);
	
	public String ask(String quastion){
		System.out.println(quastion);
		return scanner.nextLine();
	}
	
}