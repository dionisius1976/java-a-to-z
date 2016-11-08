package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class StartUITest {
		
	public static void main(String[] args){
		Input input = new StubInput(new String[] {"1", "First", "First desc", 
												  "1", "Second", "Second desc", 
												  "3", "First", "First comment1", 
												  "3", "First", "First comment2",
												  "3", "Second", "Second comment", 
												  "7", 
												  "4", "First",
												  "2", "First",
												  "7",
												  "5", "Second", "Third",
												  "7",
												  "6", "Third", "Third desc",
												  "4", "Third",
												  "7",
												  "8"});
		//new StartUI(input).init();
	}
} 