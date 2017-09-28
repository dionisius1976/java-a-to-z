package ru.dionisius.controllers;

import ru.dionisius.model.Comment;
import ru.dionisius.model.Item;
/**
 * Class starts the program.
 */
public class StartUI {
	/**
	 * Traxker instance.
	 */
	private Tracker tracker = new Tracker();
	/**
	 * Type of input.
	 */
	private Input input;
	/**
	 * Menu text.
	 */
	private String menu =  "\nВыберете действие: "
	  + "\n1)Добавить заявку"
	  + "\n2)Редактировать заявку"
	  + "\n3)Удалить заявку"
	  + "\n4)Добавить комментарий"
	  + "\n5)Вывести комментарии"
	  + "\n6)Вывести все заявки"
	  + "\n7)Найти заявку по id"
	  + "\n8)Найти заявку по имени"
	  + "\n9)Найти заявку по описанию"
	  + "\n10)Выход\n\n";
	/**
	 * Default constructor.
	 * @param input Type of input.
	 */
	  public StartUI(Input input) {
		this.input = input;
	}
	/**
	 * Initiate controllers of the program.
	 */
	public void init() {
		boolean exit = false;
	    while (!exit) {
			int choice = Integer.parseInt(this.input.ask(this.menu));
			if (choice == 1) {
				add();
				continue;
			}
			if (choice == 2) {
				update();
				continue;
			}
			if (choice == 3) {
				delete();
				continue;
			}
			if (choice == 4) {
				addComment();
				continue;
			}
			if (choice == 5) {
				printComments();
				continue;
			}
			if (choice == 6) {
				printItems();
				continue;
			}
			if (choice == 7) {
				findById();
				continue;
			}
			if (choice == 8) {
				findByName();
				continue;
			}
			if (choice == 9) {
				findByDesc();
				continue;
			}
			if (choice == 10) {
				exit = true;
			} else {
				System.out.println("Вы ввели неверный символ.");
			}
		}
	}

	/**
	 *
	 */
	private void add() {
		String name = this.input.ask("Введите имя заявки: ");
		String desc = this.input.ask("Введите описание заявки: ");
		if (!name.equals("") && !desc.equals("")) {
			Item item = new Item(name, desc);
			tracker.add(item);
			System.out.printf("Заявка добавлена. ");
			System.out.println(item);
		} else {
			System.out.println("Неверное имя или описание заявки! Заявка не добавлена.");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void update() {
		long id = Long.valueOf(this.input.ask("Введите id заявки: "));
		String name = this.input.ask("Введите новое имя заявки: ");
		String desc = this.input.ask("Введите новое описание заявки: ");
		if (!name.equals("") && !desc.equals("") && tracker.findById(id) != null) {
			tracker.update(id, name, desc);
			Item item = tracker.findById(id);
			System.out.print("Заявка отредактирована. ");
			System.out.println(item);
		} else {
			System.out.println("Неверное новое имя заявки, новое описание либо номер id! Заявка не отредактирована.");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void delete() {
		long id = Long.valueOf(this.input.ask("Введите id заявки: "));
		if (tracker.findById(id) != null) {
			tracker.delete(tracker.findById(id));
			System.out.println("Заявка удалена.");
		} else {
			System.out.println("Такого номера id не существует! Заявка не удалена.");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void addComment() {
		long id = Long.valueOf(this.input.ask("Введите id заявки: "));
		String comment = this.input.ask("Введите комментарий: ");
		if (tracker.findById(id) != null) {
			tracker.findById(id).addComment(new Comment(comment));
			System.out.println("Комментарий добавлен.");
		} else {
			System.out.println("Такого номера id не существует! Комментарий не добавлен.");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void printComments() {
		long id = Long.valueOf(this.input.ask("Введите id заявки: "));
		if (tracker.findById(id) != null) {
			Comment[] comments = tracker.findById(id).getComments();
			for (Comment comment: comments) {
				System.out.printf(comment.getText());
			}
		} else {
			System.out.println("Такого номера id не существует!");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void printItems() {
		Item[] items = tracker.getAll();
		for (Item item: items) {
			System.out.println(item);
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void findById() {
		long id = Long.valueOf(this.input.ask("Введите id заявки: "));
		if (tracker.findById(id) != null) {
			Item item = tracker.findById(id);
			System.out.print("Заявка найдена. ");
			System.out.println(item);
			System.out.println();
		} else {
			System.out.println("Такого номера id не существует!");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void findByName() {
		String name = this.input.ask("Введите имя заявки: ");
		if (!name.equals("")) {
			boolean itemExist = false;
			Item[] items = tracker.getAll();
			for (int index = 0; index < items.length; index++) {
				if (name.equals(items[index].getName())) {
					System.out.print("Заявка найдена. ");
					System.out.println(items[index]);
					itemExist = true;
					break;
				}
			} if (!itemExist) {
				System.out.println("Заявка с таким именем не найдена.");
			}
		} else {
			System.out.println("Неверное имя заявки!");
		}
		System.out.println();
	}

	/**
	 *
	 */
	private void findByDesc() {
		String desc = this.input.ask("Введите описание заявки: ");
		if (!desc.equals("")) {
			boolean itemExist = false;
			Item[] items = tracker.getAll();
			for (int index = 0; index < items.length; index++) {
				if (desc.equals(items[index].getDesc())) {
					System.out.print("Заявка найдена. ");
					System.out.println(items[index]);
					itemExist = true;
					break;
				}
			}
			if (!itemExist) {
				System.out.println("Заявка с таким описанием не найдена.");
			}
		} else {
			System.out.println("Неверное имя описания!");
		}
		System.out.println();
	}
	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		new StartUI(new ConsoleInput()).init();
	}
}