package ru.dionisius.models;

import java.util.*;

public class Item {
	
	public String name;
	public String desc;
	public Date create;
	public long id;
	public Comment[] comments = new Comment[10];
		
	public Item(){}

	public Item(String name, String desc){
		this.name = name;
		this.desc = desc;
		this.create = new Date();
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public Date getCreate(){
		return this.create;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setDate(Date date){
		this.create = date;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setComments(Comment[] comments){
		this.comments = comments;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public void addComment(Comment comment){
		for (int index = 0; index < this.comments.length; index++){
			if (comments[index] == null) this.comments[index] = comment;
		}
	}
	
	public Comment[] getComments(){
		Comment[] result = new Comment[this.comments.length];
		for (int index = 0; index < this.comments.length; index++){
			if (comments[index] == null) break;
			result[index] = this.comments[index];
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(255);
		return  hash * 255 + (int)id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		Item other = (Item) obj;
		return (this.getId() == other.getId());
		
	}
	
}