package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class Tracker {
	
	private Item[] items = new Item[10];
	private static final Random RN = new Random();
			
	private long generateId(){
		return Long.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
	
	public void add(Item item){
		if(!isItemsFree()){
			Item[] newItems = new Item[this.items.length*2];
				System.arraycopy(items, 0, newItems, 0, this.items.length);
				this.items = newItems;
		}
		item.setId(this.generateId());
		for( int index = 0; index < this.items.length; index++){
			if (this.items[index] == null) {
				this.items[index] = item;
				break;
			} 
		}
	}
	
	public Item[] getAll(){
		return this.items;
	}
	
	public void delete(Item item){
		for( int index = 0; index < this.items.length; index++){
			if (this.items[index] == null) continue;
			if (this.items[index].equals(item)) this.items[index] = null;
		}
	}
	
	public Item findById(long id){
		Item result = null;
		for( int index = 0; index < this.items.length; index++){
			if (this.items[index] == null) continue;
			if(this.items[index].getId() == id) result = this.items[index];
		}
		return result;
	}
		
	public void update(long id, String newName, String newDesc){
		Item oldItem = this.findById(id);
		Date oldDate = oldItem.getCreate();
		Comment[] oldComments = oldItem.getComments();
		this.delete(oldItem);
		Item newItem = new Item(newName, newDesc);
		this.add(newItem);
		newItem.setDate(oldDate);
		newItem.setComments(oldComments);
		newItem.setId(id);
	}
	
	private boolean isItemsFree(){
		boolean result = false;
		for( int index = 0; index < this.items.length; index++){
			if (this.items[index] == null) {
				result = true;
				break;
			}
		}
		return result;
	}
	
}