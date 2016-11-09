package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class StartUITest {
		
	public static void main(String[] args){
		Input input = new StubInput(new String[] {"1", "First", "First desc", 
												  "1", "Second", "Second desc", 
												  "6",  
												  "8", "First",
												  "8", "Third", 
												  "9", "First desc",
												  "9", "Desc",
												  "10"});
		new StartUI(input).init();
	}
} 