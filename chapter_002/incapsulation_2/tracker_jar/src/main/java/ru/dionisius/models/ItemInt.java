package ru.dionisius.models;

interface ItemInt {
	
	public String getName();
	
	public String getDesc();
	
	public long getCreate();
	
	public String getId();
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setDesc(String desc);
	
	public void setCreate(long create);
	
	public Comment addComment(Comment comment);
	
	public String[] getComments();
	
}