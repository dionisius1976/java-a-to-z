package ru.dionisius.models;

public class Task extends Item {
	
	public Task(String name, String desc, long create){
		this.name = name;
		this.desc = desc;
		this.create = create;
	}
	
public String calculatePrice(){
	return "100%";
	}
}
