package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class StartUI {
	
	private Tracker tracker = new Tracker();
	private Input input;
	  
	public StartUI(Input input){
		this.input = input;
	}
	
	public void init(){
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Выберете действие: "));
			menu.select(key);
		} while(!"y".equals(this.input.ask("Выход? (y/n)")));
	}
	
	public static void main(String[] args){
		new StartUI(new ConsoleInput()).init();
	}
}