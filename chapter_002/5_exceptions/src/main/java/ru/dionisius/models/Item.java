package ru.dionisius.models;

import java.util.Date;
import java.util.Random;

/**
 * Describes item.
 */
public class Item {
	/**
	 * Random number for generating hash code.
	 */
	static final int RANDOM_NUMBER_255 = 255;
	/**
	 * Available number of comments for this item.
	 */
	static final int NUMBER_OF_COMMENTS = 10;
	/**
	 * Specified name of this item.
	 */
	private String name;
	/**
	 * Specified description of this item.
	 */
	private String desc;
	/**
	 * Specified date of creation of this item.
	 */
	private Date create;
	/**
	 * Specified id of this item.
	 */
	private long id;
	/**
	 * Array of comment of this item.
	 */
	private Comment[] comments = new Comment[NUMBER_OF_COMMENTS];
	/**
	 * Constructor without parameters.
	 */
	public Item() {
	}
	/**
	 * Constructor.
	 * @param name Specified name of this item.
	 * @param desc Specified description of this item.
	 */
	public Item(String name, String desc) {
		this.name = name;
		this.desc = desc;
		this.create = new Date();
	}
	/**
	 * Getter for the name of this item.
	 * @return the name of this item.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Getter of description of this item.
	 * @return description of this item.
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * Getter of creation date of this item.
	 * @return creation date of this item.
	 */
	public Date getCreate() {
		return this.create;
	}
	/**
	 * Getter for id of this item.
	 * @return id of this item.
	 */
	public long getId() {
		return this.id;
	}
	/** Sets the new id for this item.
	 * @param id new id for this item.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/** Sets the new creation date for this item.
	 * @param date the new creation date for this item.
	 */
	public void setDate(Date date) {
		this.create = date;
	}
	/**
	 * Sets the new name for this item.
	 * @param name specified new name for this item.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Setter for new array of comments to this item.
	 * @param comments specified new array of comments to this item.
	 */
	public void setComments(Comment[] comments) {
		this.comments = comments;
	}
	/**
	 * Adds description to this item.
	 * @param desc specified descriptor that adds to this item.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * Adds a new comment to this item.
	 * @param comment the adding comment.
	 */
	public void addComment(Comment comment) {
		if (!this.isCommentsFree()) {
			Comment[] newComments = new Comment[this.comments.length * 2];
				System.arraycopy(comments, 0, newComments, 0, this.comments.length);
				this.comments = newComments;
		}
		for (int index = 0; index < this.comments.length; index++) {
			if (comments[index] == null) {
				this.comments[index] = comment;
				break;
			}
		}
	}
	/**
	 * Returns array of comments of this item.
	 * @return array of comments of this item.
	 */
	public Comment[] getComments() {
		Comment[] result = new Comment[this.comments.length];
		int index = 0;
		for (int i = 0; i < this.comments.length; i++) {
			if (comments[i] == null) {
				break;
			}
			result[i] = this.comments[i];
			index++;
		}
		if (index < result.length) {
			Comment[] temp = new Comment[index];
			System.arraycopy(result, 0, temp, 0, index);
			result = temp;
		}
		return result;
	}
	/**
	 * Verifies if there is no comments of this item.
	 * @return true if there is no comments and false if not.
	 */
	private boolean isCommentsFree() {
		boolean result = false;
		for (int index = 0; index < this.comments.length; index++) {
			if (this.comments[index] == null) {
				result = true;
				break;
			}
		}
		return result;
	}
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(RANDOM_NUMBER_255);
		return  hash * RANDOM_NUMBER_255 + (int) id;
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
		Item other = (Item) obj;
		return (this.getId() == other.getId());
	}

	@Override
	public String toString() {
		return  String.format("Id: %d, имя: %s, описание: %s", this.getId(), this.getName(), this.getDesc());
	}
}