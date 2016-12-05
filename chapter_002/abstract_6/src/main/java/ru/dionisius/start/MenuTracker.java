package ru.dionisius.start;

import ru.dionisius.models.*;

public class MenuTracker {

	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[9];
	private int position = 0;
		
	public MenuTracker(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}
		
	public void fillActions(){
		this.actions[position++] = this.new AddItem("Добавить заявку.");
		this.actions[position++] = this.new EditItem("Редактировать заявку.");
		this.actions[position++] = this.new DeleteItem("Удалить заявку.");
		this.actions[position++] = this.new AddComment("Добавить комментарий к заявке.");
		this.actions[position++] = this.new PrintComments("Вывести все комментарии заявки.");
		this.actions[position++] = this.new PrintItems("Вывести все заявки.");
		this.actions[position++] = this.new FindById("Найти заявку по id.");
		this.actions[position++] = this.new FindByName("Найти заявку по имени.");
		this.actions[position++] = this.new FindByDesc("Найти заявку по описанию.");
	}
		
	public void addAction (UserAction action){
		this.actions[position++] = action;
	}
		
	public int[] getRange() {
		int[] result = new int[this.actions.length];
		for(int index = 0; index < this.actions.length; index++){
			result[index] = index;
		}
		return result;
	}
		
	public void select (int key) {
		this.actions[key].execute(this.input, this.tracker);
	}
		
	public void show() {
		for(UserAction action: this.actions){
			if(action != null){
				System.out.println(action.info());
			}
		}
	}
		
	private boolean subStringCheck(String originString, String subString){
		char[] originArray = originString.toCharArray();
		char[] subArray = subString.toCharArray();
		int originLastIndex=originArray.length-subArray.length+1;
		boolean isSubstring = false;
		for(int j=0; j<=originLastIndex; j++){
			if(subArray[0] == originArray[j] && isArraysEqual(subArray, 1, originArray, j+1)) {
				isSubstring = true;
				break;
			}
		}
		return isSubstring;
	}
	
	private boolean isArraysEqual(char[] arr1, int arr1StartIndex, char[] arr2, int arr2StartIndex){
		int arr1LastIndex=arr1.length-1;
		boolean isArraysEqual=true;
		for(int i=arr1StartIndex; i<=arr1LastIndex; i++){
			if(arr1[i]!=arr2[arr2StartIndex]) {
				isArraysEqual=false;
				break;
			}
			arr2StartIndex++;
		}
		return isArraysEqual;
	}
		
	private class AddItem extends BaseAction implements UserAction {
			
		public AddItem (String name) {
			super(name);
		}
			
		public int key(){
			return 0;
		}
	
		public void execute(Input input, Tracker tracker){
			String name = input.ask("Введите имя заявки: ");
			String desc = input.ask("Введите описание заявки: ");
			if (!name.equals("") && !desc.equals("")){
				Item item = new Item(name, desc);
				tracker.add(item);
				System.out.printf("Заявка добавлена. ");
				System.out.println(item);
			} else System.out.println("Неверное имя или описание заявки! Заявка не добавлена.");
			System.out.println();
		}
	}
		
	private class EditItem extends BaseAction implements UserAction {
			
		public EditItem (String name) {
			super(name);
		}
			
		public int key(){
			return 1;
		}
	
		public void execute(Input input, Tracker tracker){
			try{
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				String name = input.ask("Введите новое имя заявки: ");
				String desc = input.ask("Введите новое описание заявки: ");
				if (!name.equals("") && !desc.equals("") &&tracker.findById(id) != null){
					tracker.update(id, name, desc);
					Item item = tracker.findById(id);
					System.out.print("Заявка отредактирована. ");
					System.out.println(item);
				} else System.out.println("Неверное новое имя заявки, новое описание либо номер id! Заявка не отредактирована.");
				System.out.println();
			}catch(NumberFormatException nfe){
				System.out.println("Введите корректные данные.");
			}
		}
	}
		
	private class DeleteItem extends BaseAction implements UserAction {
			
		public DeleteItem(String name) {
			super(name);
		}
			
		public int key(){
			return 2;
		}
	
		public void execute(Input input, Tracker tracker){
			try{
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null){
					tracker.delete(tracker.findById(id));
					System.out.println("Заявка удалена.");
				} else System.out.println("Такой номер id не существует! Заявка не удалена.");
				System.out.println();
			}catch(NumberFormatException nfe){
				System.out.println("Введите корректные данные.");
			}
		}
	}
		
	private class AddComment extends BaseAction implements UserAction {
			
		public AddComment (String name) {
			super(name);
		}
			
		public int key(){
			return 3;
		}
	
		public void execute(Input input, Tracker tracker){
			try{
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				String comment = input.ask("Введите комментарий: ");
				if (tracker.findById(id) != null){
					tracker.findById(id).addComment(new Comment(comment));
					System.out.println("Комментарий добавлен.");
				} else System.out.println ("Такой номер id не существует! Комментарий не добавлен.");
				System.out.println();
			}catch(NumberFormatException nfe){
				System.out.println("Введите корректные данные.");
			}
		}
	}
		
	private class PrintComments extends BaseAction implements UserAction {
			
		public PrintComments (String name) {
			super(name);
		}
			
		public int key(){
			return 4;
		}
	
		public void execute(Input input, Tracker tracker){
			try{
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null){
					Comment[] comments = tracker.findById(id).getComments();
					for(int i = 0; i < comments.length; i++){
						if(comments[i] == null) break;
						System.out.println(comments[i].getText());
					}
				} else System.out.println ("Такой номер id не существует!");
				System.out.println();
			}catch(NumberFormatException nfe){
				System.out.println("Введите корректные данные.");
			}
		}
	}
		
	private class PrintItems extends BaseAction implements UserAction {
		
		public PrintItems (String name) {
			super(name);
		}
			
		public int key(){
			return 5;
		}
	
		public void execute(Input input, Tracker tracker){
			Item[] items = tracker.getAll();
			for(int i = 0; i < items.length; i++){
				if(items[i] == null) continue;
				System.out.println(items[i]);
			}
			System.out.println();
		}
	}
		
	private class FindById extends BaseAction implements UserAction {
			
		public FindById(String name) {
			super(name);
		}
			
		public int key(){
			return 6;
		}
	
		public void execute(Input input, Tracker tracker){
			try{
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null){
					Item item = tracker.findById(id);
					System.out.print("Заявка найдена. ");
					System.out.println(item);
					System.out.println();
				} else System.out.println ("Такоq номер id не существует!");
				System.out.println();
			}catch(NumberFormatException nfe){
				System.out.println("Введите корректные данные.");
			}
		}
	}
		
	private class FindByName extends BaseAction implements UserAction {
			
		public FindByName(String name) {
			super(name);
		}
			
		public int key(){
			return 7;
		}
	
		public void execute(Input input, Tracker tracker){
			String name = input.ask("Введите имя заявки: ");
			if (!name.equals("")){
				boolean itemExist = false;
				Item[] items = tracker.getAll();
				Item[] result = new Item[items.length];
				for(int index=0; index < items.length; index++){
					if(items[index] == null) continue;
					if (subStringCheck(items[index].getName(), name)){
						System.out.println(items[index]);
						itemExist = true;
					}
				} if(!itemExist) System.out.println("Заявка с таким именем не найдена.");
			} else System.out.println("Неверное имя заявки!");
			System.out.println();
		}
	}
		
	private class FindByDesc extends BaseAction implements UserAction {
		
		public FindByDesc(String name) {
			super(name);
		}
			
		public int key(){
			return 8;
		}
	
		public void execute(Input input, Tracker tracker){
			String desc = input.ask("Введите описание заявки: ");
			if (!desc.equals("")){
				boolean itemExist = false;
				Item[] items = tracker.getAll();
				for(int index=0; index < items.length; index++){
					if(items[index] == null) continue;
					if (subStringCheck(items[index].getDesc(), desc)){
						System.out.println(items[index]);
						itemExist = true;
					}
				}
				if(!itemExist) System.out.println("Заявка с таким описанием не найдена.");
			} else System.out.println("Неверное имя описания!");
			System.out.println();
		}
	}
	
}