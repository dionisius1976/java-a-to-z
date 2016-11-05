package ru.dionisius.start;
import ru.dionisius.models.*;
import java.util.*;

public class StartUI {
	public static void main(String[] args){
		Tracker tracker = new Tracker();
		tracker.add(new Task("First name", "First desc", new Date().getTime()));
		for(Item item: tracker.getAll()){
			System.out.println(item.getName());
		}
	}
}