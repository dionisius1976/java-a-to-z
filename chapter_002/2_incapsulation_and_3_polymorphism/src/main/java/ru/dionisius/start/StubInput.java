package ru.dionisius.start;

//import java.util.*;

public class StubInput implements Input {
	private String[] answers;
	private int position = 0;
	
	public StubInput(String[] answers){
		this.answers = answers;
	}

	public String ask(String quastion){
		return this.answers[position++];
	}
	
}