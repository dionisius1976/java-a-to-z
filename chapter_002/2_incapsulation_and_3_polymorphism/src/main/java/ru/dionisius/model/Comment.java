package ru.dionisius.model;

import java.util.Random;
/**
 * Class describes comments to model.
 */
public class Comment {
	/**
	 * Text of this comment.
	 */
	private String text;
	/**
	 * Default constructor.
	 * @param text text of this comment.
	 */
	public Comment(String text) {
		this.text = text;
	}
	/**
	 * Getter of text of this comment.
	 * @return text of this comment.
	 */
	public String getText() {
		return this.text;
	}
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(255);
		return  hash * 37;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Comment other = (Comment) obj;
		return (this.getText().equals(other.getText()));
	}
}
