package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class StartUI {
	private int[] range;
	private Tracker tracker = new Tracker();
	private Input input;
	  
	public StartUI(Input input){
		this.input = input;
	}
	
	public void init() {
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		this.range = menu.getRange();
		menu.fillActions();
		do {
			menu.show();
			menu.select(input.ask("Выберете действие: ", range));
		} while(!"y".equals(this.input.ask("Выход? (y)")));
	}
	
	public static void main(String[] args){
		new StartUI(new ValidateInput()).init();
	}
}