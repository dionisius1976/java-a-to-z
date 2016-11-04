package ru.dionisius.models;

public class Comment {
	private String text;
	
	public Comment(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
}
