package ru.dionisius.models;

import java.util.Random;

/**
 *
 */
public class Comment {
	/**
	 *
	 */
	private String text;

	/**
	 * @param text
	 */
	public Comment(String text){
		this.text = text;
	}

	/**
	 * @return
	 */
	public String getText(){
		return this.text;
	}

	/**
	 * @return
	 */
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(255);
		return  hash * 37;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return (this.getText().equals(other.getText()));
	}
}
