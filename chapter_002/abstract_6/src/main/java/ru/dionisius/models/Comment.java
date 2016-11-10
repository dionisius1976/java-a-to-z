package ru.dionisius.models;

import java.util.*;

public class Comment {
	
	private String text;

	public Comment(String text){
		this.text = String.format("%s : %s", new Date(), text);
	}
	
	public String getText(){
		return this.text;
	}
	
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(255);
		return  hash * 37;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return (this.getText().equals(other.getText()));
	}
}
