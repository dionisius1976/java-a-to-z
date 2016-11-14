package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class StartUITest {
		
	public static void main(String[] args){
		Input input = new StubInput(new String[] {"0", "First", "First desc",
												  "n",
												  "0", "Second", "Second desc", 
												  "n",
												  "5",  
												  "n",
												  "7", "First",
												  "n",
												  "7", "Third", 
												  "n",
												  "8", "First desc",
												  "n",
												  "8", "Desc",
												  "y"}, new int[9]);
		new StartUI(input).init();
	}
} 