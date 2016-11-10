package ru.dionisius.start;

import ru.dionisius.models.*;

public class MenuTracker {

		private Input input;
		private Tracker tracker;
		private UserAction[] actions = new UserAction[9];
		
		public MenuTracker(Input input, Tracker tracker){
			this.input = input;
			this.tracker = tracker;
		}
		
		public void fillActions(){
			this.actions[0] = this.new AddItem();
			this.actions[1] = this.new EditItem();
			this.actions[2] = this.new DeleteItem();
			this.actions[3] = this.new AddComment();
			this.actions[4] = this.new PrintComments();
			this.actions[5] = this.new PrintItems();
			this.actions[6] = this.new FindById();
			this.actions[7] = this.new FindByName();
			this.actions[8] = this.new FindByDesc();
		}
		
		public int[] getRange(){
			int[] result = new int[this.actions.length];
			for(int index = 0; index < this.actions.length; index++){
				result[index] = index;
			}
			return result;
		}
		
		public void select (int key){
			this.actions[key].execute(this.input, this.tracker);
		}
		
		public void show(){
			for(UserAction action: this.actions){
				if(action != null){
					System.out.println(action.info());
				}
			}
		}
		
		private class AddItem implements UserAction {
			
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
	
			public String info() {
				return String.format("%s. %s", this.key(), "Добавить заявку.");
			}
		}
		
		private class EditItem implements UserAction {
			
			public int key(){
				return 1;
			}
	
			public void execute(Input input, Tracker tracker){
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
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Редактировать заявку.");
			}
		}
		
		private class DeleteItem implements UserAction {
			
			public int key(){
				return 2;
			}
	
			public void execute(Input input, Tracker tracker){
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null){
					tracker.delete(tracker.findById(id));
					System.out.println("Заявка удалена.");
				} else System.out.println("Такой номер id не существует! Заявка не удалена.");
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Удалить заявку.");
			}
		}
		
		private class AddComment implements UserAction {
			
			public int key(){
				return 3;
			}
	
			public void execute(Input input, Tracker tracker){
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				String comment = input.ask("Введите комментарий: ");
				if (tracker.findById(id) != null){
					tracker.findById(id).addComment(new Comment(comment));
					System.out.println("Комментарий добавлен.");
				} else System.out.println ("Такой номер id не существует! Комментарий не добавлен.");
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Добавить комментарий к заявке.");
			}
		}
		
		private class PrintComments implements UserAction {
			
			public int key(){
				return 4;
			}
	
			public void execute(Input input, Tracker tracker){
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null){
					Comment[] comments = tracker.findById(id).getComments();
					for(Comment comment: comments){
						System.out.printf(comment.getText());
					}
				} else System.out.println ("Такой номер id не существует!");
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Вывести все комментарии заявки.");
			}
		}
		
		private class PrintItems implements UserAction {
			
			public int key(){
				return 5;
			}
	
			public void execute(Input input, Tracker tracker){
				Item[] items = tracker.getAll();
				for(Item item: items){
					System.out.println(item);
				}
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Вывести все заявки.");
			}
		}
		
		private class FindById implements UserAction {
			
			public int key(){
				return 6;
			}
	
			public void execute(Input input, Tracker tracker){
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null){
					Item item = tracker.findById(id);
					System.out.print("Заявка найдена. ");
					System.out.println(item);
					System.out.println();
				} else System.out.println ("Такоq номер id не существует!");
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Найти заявку по id.");
			}
		}
		
		private class FindByName implements UserAction {
			
			public int key(){
				return 7;
			}
	
			public void execute(Input input, Tracker tracker){
				String name = input.ask("Введите имя заявки: ");
				if (!name.equals("")){
					boolean itemExist = false;
					Item[] items = tracker.getAll();
					for(int index=0; index < items.length; index++){
						if (name.equals(items[index].getName())) {
							System.out.print("Заявка найдена. ");
							System.out.println(items[index]);
							itemExist = true;
							break;
						}
					} if(!itemExist) System.out.println("Заявка с таким именем не найдена.");
				} else System.out.println("Неверное имя заявки!");
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Найти заявку по имени.");
			}
		}
		
		private class FindByDesc implements UserAction {
			
			public int key(){
				return 8;
			}
	
			public void execute(Input input, Tracker tracker){
				String desc = input.ask("Введите описание заявки: ");
				if (!desc.equals("")){
					boolean itemExist = false;
					Item[] items = tracker.getAll();
					for(int index=0; index < items.length; index++){
						if (desc.equals(items[index].getDesc())){ 
							System.out.print("Заявка найдена. ");
							System.out.println(items[index]);
							itemExist = true;
							break;
						}
					} if(!itemExist) System.out.println("Заявка с таким описанием не найдена.");
				} else System.out.println("Неверное имя описания!");
				System.out.println();
			}
	
			public String info() {
				return String.format("%s. %s", this.key(), "Найти заявку по описанию.");
			}
		}
		
		

}