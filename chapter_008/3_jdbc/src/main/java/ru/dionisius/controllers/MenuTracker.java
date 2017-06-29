package ru.dionisius.controllers;

import ru.dionisius.controllers.action.ABaseAction;
import ru.dionisius.controllers.action.UserAction;
import ru.dionisius.controllers.input.Input;
import ru.dionisius.models.Comment;
import ru.dionisius.models.Item;

import java.util.Date;

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
		this.actions[0] = this.new AddItem("Добавить заявку.");
		this.actions[1] = this.new EditItem("Редактировать заявку.");
		this.actions[2] = this.new DeleteItem("Удалить заявку.");
		this.actions[3] = this.new AddComment("Добавить комментарий к заявке.");
		this.actions[4] = this.new PrintComments("Вывести все комментарии заявки.");
		this.actions[5] = this.new PrintItems("Вывести все заявки.");
		this.actions[6] = this.new FindById("Найти заявку по id.");
		this.actions[7] = this.new FindByName("Найти заявку по имени.");
		this.actions[8] = this.new FindByDesc("Найти заявку по описанию.");
	}

	/**
	 * Returns range of menu options.
	 * @return range of menu options.
	 */
	public int[] getRange() {
		int[] result = new int[this.actions.length];
		for (int index = 0; index < this.actions.length; index++) {
			result[index] = index;
		}
		return result;
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
	private class AddItem extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		AddItem(String name) {
			super(name);
		}
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
			if (tracker.findByName(name) != null && tracker.findByDesc(desc) != null) {
				Item item = new Item(name, desc, new Date());
				tracker.addNewItem(item);
				System.out.printf("Заявка добавлена.");
				System.out.println(item);
			} else {
				System.out.println("Неверное имя или описание заявки! Заявка не добавлена.");
			}
			System.out.println();
		}
	}
	/**
	 * Edits specified by id item in tracker.
	 */
	private class EditItem extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		EditItem(String name) {
			super(name);
		}
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
			if (tracker.findById(id) != null) {
				tracker.editItem(id, name, desc);
				Item item = tracker.findById(id);
				System.out.print("Заявка отредактирована. ");
				System.out.println(item);
			} else {
				System.out.println(String.format("Заявки с номером id %d не существует!", id));
			}
			System.out.println();
		}
	}
	/**
	 * Deletes specified by id item from tracker.
	 */
	private class DeleteItem extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		DeleteItem(String name) {
			super(name);
		}
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
				tracker.deleteItem(tracker.findById(id));
				System.out.println("Заявка удалена.");
			} else {
                System.out.println(String.format("Заявки с номером id %d не существует!", id));
            }
			System.out.println();
		}
	}
	/**
	 * Adds new comment to specified by id item in tracker.
	 */
	private class AddComment extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		AddComment(String name) {
			super(name);
		}
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
				tracker.addNewComment(id, comment);
				System.out.println("Комментарий добавлен.");
			} else {
				System.out.println(String.format("Заявки с номером id %d не существует! Комментарий не добавлен.", id));
			}
			System.out.println();
		}
	}
	/**
	 * Prints all comments of specified item by id in console.
	 */
	private class PrintComments extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		PrintComments(String name) {
			super(name);
		}
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
                for (Comment comment : tracker.getAllComments(id)) {
                    System.out.println(comment);
                }
            } else {
                System.out.println(String.format("Заявки с номером id %d не существует!", id));
            }
			System.out.println();
		}
	}
	/**
	 * Prints all models of specified tracker in console.
	 */
	private class PrintItems extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		PrintItems(String name) {
			super(name);
		}
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
			Item[] items = tracker.getAllItems();
			for (Item item: items) {
				System.out.println(item);
			}
			System.out.println();
		}
	}
	/**
	 * Finds item by item's id.
	 */
	private class FindById extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		FindById(String name) {
			super(name);
		}
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
				System.out.print("Заявка найдена: ");
				System.out.println(item);
				System.out.println();
			} else {
                System.out.println(String.format("Заявки с номером id %d не существует!", id));
            }
			System.out.println();
		}
	}
	/**
	 * Finds item by item's name.
	 */
	private class FindByName extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		FindByName(String name) {
			super(name);
		}
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
		 * Executes method finding item by item's name or part of name.
		 * @param input   type of input.
		 * @param tracker type of tracker.
		 */
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Введите имя заявки: ");
			if (tracker.findByName(name).length != 0) {
				for (Item current : tracker.findByName(name)) {
					System.out.println(current);
				}
			} else {
                System.out.println(String.format("Заявки с именем %s не существует!", name));
            }
			System.out.println();
		}
	}
	/**
	 * Finds item by item's description.
	 */
	private class FindByDesc extends ABaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		FindByDesc(String name) {
			super(name);
		}
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
		 * Executes method finding item by item's description or part of description.
		 * @param input type of input.
		 * @param tracker type of tracker.
		 */
		public void execute(Input input, Tracker tracker) {
			String desc = input.ask("Введите описание заявки: ");
            if (tracker.findByDesc(desc).length != 0) {
                for (Item current : tracker.findByDesc(desc)) {
                    System.out.println(current);
                }
            } else {
                System.out.println(String.format("Заявки с описанием %s не существует!", desc));
            }
            System.out.println();
        }
	}
}