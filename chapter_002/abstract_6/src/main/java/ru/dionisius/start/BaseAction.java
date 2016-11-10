package ru.dionisius.start;

public abstract class BaseAction implements UserAction {
	
	private String name;
	
	public BaseAction (String name){
		this.name = name;
	}

	abstract public int key();
	
	abstract public void execute(Input input, Tracker tracker);
	
	public String info() {
				return String.format("%s. %s", this.key(), this.name);
			}

}