package ru.dionisius.controllers;

import ru.dionisius.models.Comment;
import ru.dionisius.models.Item;
/**
 * Selects specified action depending of chosen by user key and starts this action.
 */
public class MenuTracker {
	/**
	 * Specified type of input.
	 */
		private final Input input;
	/**
	 * Specified type of tracker.
	 */
		private final Tracker tracker;
	/**
	 * Number of available actions.
	 */
		static final int NUMBER_OF_ACTIONS = 9;
	/**
	 * Menu option 0.
	 */
		static final int MENU_OPTION_0 = 0;
	/**
	 * Menu option 1.
	 */
		static final int MENU_OPTION_1 = 1;
	/**
	 * Menu option 2.
	 */
		static final int MENU_OPTION_2 = 2;
	/**
	 * Menu option 3.
	 */
		static final int MENU_OPTION_3 = 3;
	/**
	 * Menu option 4.
	 */
		static final int MENU_OPTION_4 = 4;
	/**
	 * Menu option 5.
	 */
		static final int MENU_OPTION_5 = 5;
	/**
	 * Menu option 6.
	 */
		static final int MENU_OPTION_6 = 6;
	/**
	 * Menu option 7.
	 */
		static final int MENU_OPTION_7 = 7;
	/**
	 * Menu option 8.
	 */
		static final int MENU_OPTION_8 = 8;
	/**
	 * Array of available actions.
	 */
		private final UserAction[] actions = new UserAction[NUMBER_OF_ACTIONS];
	/**
	 * Default constructor.
	 * @param input type of input.
	 * @param tracker type of tracker.
	 */
		public MenuTracker(final Input input, final Tracker tracker) {
			this.input = input;
			this.tracker = tracker;
		}
	/**
	 * Fills action array by available actions.
	 */
		public void fillActions() {
			this.actions[MENU_OPTION_0] = this.new AddItem();
			this.actions[MENU_OPTION_1] = this.new EditItem();
			this.actions[MENU_OPTION_2] = this.new DeleteItem();
			this.actions[MENU_OPTION_3] = this.new AddComment();
			this.actions[MENU_OPTION_4] = this.new PrintComments();
			this.actions[MENU_OPTION_5] = this.new PrintItems();
			this.actions[MENU_OPTION_6] = this.new FindById();
			this.actions[MENU_OPTION_7] = this.new FindByName();
			this.actions[MENU_OPTION_8] = this.new FindByDesc();
		}

	/**
	 * Selects specified action depending of chosen by user key and starts this action.
	 * @param key chosen by user key.
	 */
		public void select(int key) {
			this.actions[key].execute(this.input, this.tracker);
		}

	/**
	 * Shows menu with available actions.
	 */
		public void show() {
			for (UserAction action: this.actions) {
				if (action != null) {
					System.out.println(action.info());
				}
			}
		}

	/**
	 * Adds the new item in tracker.
	 */
		private class AddItem implements UserAction {
		/**
		 * Specified key for this action.
		 */
		static final int KEY = 0;
		/**
		 * Returns specified key for this class instance that using in menu of available actions.
		 * @return specified key for this class instance.
		 */
			public int key() {
				return KEY;
			}
		/**
		 * Executes method adding a new item in specified tracker.
		 * @param input type of input.
		 * @param tracker type of tracker.
		 */
			public void execute(Input input, Tracker tracker) {
				String name = input.ask("Введите имя заявки: ");
				String desc = input.ask("Введите описание заявки: ");
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
		 * Returns info about this class instance that using in menu of available actions.
		 * @return info about this class instance.
		 */
			public String info() {
				return String.format("%s. %s", this.key(), "Добавить заявку.");
			}
		}

	/**
	 * Edits specified by id item in tracker.
	 */
		private class EditItem implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 1;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method editing specified by id item.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				String name = input.ask("Введите новое имя заявки: ");
				String desc = input.ask("Введите новое описание заявки: ");
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
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Редактировать заявку.");
			}
		}

	/**
	 * Deletes specified by id item from tracker.
	 */
		private class DeleteItem implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 2;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method deleting specified by id item.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null) {
					tracker.delete(tracker.findById(id));
					System.out.println("Заявка удалена.");
				} else {
					System.out.println("Такой номер id не существует! Заявка не удалена.");
				}
				System.out.println();
			}
			/**
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Удалить заявку.");
			}
		}

	/**
	 * Adds new comment to specified by id item in tracker.
	 */
		private class AddComment implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 3;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method adding a new comment to specified by id item.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				String comment = input.ask("Введите комментарий: ");
				if (tracker.findById(id) != null) {
					tracker.findById(id).addComment(new Comment(comment));
					System.out.println("Комментарий добавлен.");
				} else {
					System.out.println("Такой номер id не существует! Комментарий не добавлен.");
				}
				System.out.println();
			}
			/**
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Добавить комментарий к заявке.");
			}
		}

	/**
	 * Prints all comments of specified item by id in console.
	 */
		private class PrintComments implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 4;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method printing all comments of specified item by id in console.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null) {
					Comment[] comments = tracker.findById(id).getComments();
					for (Comment comment: comments) {
						System.out.printf(comment.getText());
					}
				} else {
					System.out.println("Такой номер id не существует!");
				}
				System.out.println();
			}
			/**
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Вывести все комментарии заявки.");
			}
		}

	/**
	 * Prints all models of specified tracker in console.
	 */
		private class PrintItems implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 5;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method printing all models in console.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				Item[] items = tracker.getAll();
				for (Item item: items) {
					System.out.println(item);
				}
				System.out.println();
			}
			/**
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Вывести все заявки.");
			}
		}

	/**
	 * Finds item by item's id.
	 */
		private class FindById implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 6;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method finding item by item's id.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				long id = Long.valueOf(input.ask("Введите id заявки: "));
				if (tracker.findById(id) != null) {
					Item item = tracker.findById(id);
					System.out.print("Заявка найдена. ");
					System.out.println(item);
					System.out.println();
				} else {
					System.out.println("Такой номер id не существует!");
				}
				System.out.println();
			}
			/**
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Найти заявку по id.");
			}
		}

	/**
	 * item by item's name.
	 */
		private class FindByName implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 7;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method finding item by item's name.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				String name = input.ask("Введите имя заявки: ");
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
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Найти заявку по имени.");
			}
		}

	/**
	 * item by item's description.
	 */
		private class FindByDesc implements UserAction {
			/**
			 * Specified key for this action.
			 */
			static final int KEY = 8;
			/**
			 * Returns specified key for this class instance that using in menu of available actions.
			 * @return specified key for this class instance.
			 */
			public int key() {
				return KEY;
			}
			/**
			 * Executes method finding item by item's description.
			 * @param input type of input.
			 * @param tracker type of tracker.
			 */
			public void execute(Input input, Tracker tracker) {
				String desc = input.ask("Введите описание заявки: ");
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
					} if (!itemExist) {
						System.out.println("Заявка с таким описанием не найдена.");
					}
				} else {
					System.out.println("Неверное имя описания!");
				}
				System.out.println();
			}

			/**
			 * Returns info about this class instance that using in menu of available actions.
			 * @return info about this class instance.
			 */
			public String info() {
				return String.format("%s. %s", this.key(), "Найти заявку по описанию.");
			}
		}
}