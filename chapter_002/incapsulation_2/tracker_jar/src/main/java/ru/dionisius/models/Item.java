package ru.dionisius.models;

public class Item {
	public String name;
	public String desc;
	public long create;
	private String id;
	public Comment[] comments = new Comment[10];
	private int commentPosition = 0;
	
	
	public Item(){}

	public Item(String name, String desc, long create){
		this.name = name;
		this.desc = desc;
		this.create = create;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public long getCreate(){
		return this.create;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public Comment addComment(Comment comment){
		this.comments[commentPosition++] = comment;
		return comment;
	}
	
	public Comment[] getComments(){
		Comment[] result = new Comment[commentPosition];
		for (int index = 0; index < this.commentPosition; index++){
			result[index] = this.comments[index];
		}
		return result;
	}
	
}