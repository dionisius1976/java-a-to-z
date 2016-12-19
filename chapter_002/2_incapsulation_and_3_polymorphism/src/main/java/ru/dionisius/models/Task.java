package ru.dionisius.models;

import java.util.*;

public class Task extends Item {
	
	public Task(String name, String desc, Date create){
		this.name = name;
		this.desc = desc;
		this.create = create;
	}
	
public String calculatePrice(){
	return "100%";
	}
}
