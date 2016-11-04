package ru.dionisius.start;
import ru.dionisius.models.*;
import java.util.*;

public class Tracker {
	
	private Item[] items = new Item[10];
	private int position = 0;
	private static final Random RN = new Random();
		
	public Item add(Item item){
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}
	
	public Item[] getAll(){
		Item[] result = new Item[this.position];
		for( int index = 0; index < this.position; index++){
			result[index] = this.items[index];
		}
		return result;
	}
	
	public Item delete(Item item){
		Item deletedItem = null;
		for( int index = 0; index<=this.position; index++){
			if (this.items[index] == null) continue;
			if(this.items[index].equals(item)){
				deletedItem = this.items[index];
				this.items[index] = null;
			}
		}
		return deletedItem;
	}
	
	public Comment addComment(Item item, Comment comment){
		Comment returningComment = comment;
		if (comment != null) item.addComment(comment);
		return comment;
	}
		
	protected Item findById(String id){
		Item result = null;
		for (Item item: items){
			if(item != null && item.getId().equals(id)){
				result = item;
				break;
			}
		}
		return result;
	}
	
	protected Item findByName(String name){
		Item result = null;
		for (Item item: items){
			if(item != null && item.getName().equals(name)){
				result = item;
				break;
			}
		}
		return result;
	}
	
	protected Item findByDesc(String desc){
		Item result = null;
		for (Item item: items){
			if(item != null && item.getDesc().equals(desc)){
				result = item;
				break;
			}
		}
		return result;
	}
	
	protected Item findByCreate(long create){
		Item result = null;
		for (Item item: items){
			if(item != null && item.getCreate() == create){
				result = item;
				break;
			}
		}
		return result;
	}
	
	protected void editName(Item item, String name){
		item.setName(name);
	}
	
	protected void editDesc(Item item, String desc){
		item.setDesc(desc);
	}
	
	String generateId(){
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
	
}