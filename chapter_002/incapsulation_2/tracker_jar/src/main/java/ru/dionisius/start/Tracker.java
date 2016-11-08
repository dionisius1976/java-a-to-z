package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

public class Tracker {
	
	private Item[] items = new Item[10];
	private static final Random RN = new Random();
	
	//add, update, delete, findById, get all, эти должны быть доступны и приватный метод сгенерить айди
	//остальные методы будут в StartUi
		
	private long generateId(){
		return Long.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
	
	public void add(Item item){
		item.setId(this.generateId());
		for( int index = 0; index < this.items.length; index++){
			if (this.items[index] == null) {
				this.items[index] = item;
				break;
			}
		}
	}
	
	public Item[] getAll(){
		int getAllIndex = 0;
		Item[] result = new Item[this.items.length];
		for( int index = 0; index < this.items.length; index++){
			if (this.items[index] == null) continue;
			result[getAllIndex] = this.items[index];
			getAllIndex++;
		}
		Item[] getAll = new Item [getAllIndex];
		System.arraycopy(result, 0, getAll, 0, getAllIndex);
		return getAll;
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
	
}