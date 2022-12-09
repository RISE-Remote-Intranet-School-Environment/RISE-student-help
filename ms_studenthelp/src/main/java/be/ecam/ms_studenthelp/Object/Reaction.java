package be.ecam.ms_studenthelp.Object;

import be.ecam.ms_studenthelp.Interfaces.IPost;
import be.ecam.ms_studenthelp.Interfaces.IReaction;

/**
 * Container for the reaction information.
 */
public class Reaction implements IReaction {
	private final IPost post;
	private final Author author;
	private int value;

	/**
	 * Constructor.
	 * @param post Post linked for the reactions.
	 * @param author Author of the reactions.
	 * @param value Value of the reactions.
	 */
	public Reaction(IPost post, Author author, int value) {
		this.post = post;
		this.author = author;
		setValue(value);
	}

	/**
	 * Getter for the post linked to the reaction.
	 * @return Post.
	 */
	public IPost getPost() { return post; }

	/**
	 * Getter for the author linked to the reaction.
	 * @return Author.
	 */
	public Author getAuthor() { return author; }

	/**
	 * Getter for the reaction value.
	 * @return Value.
	 */
	public int getValue() { return value; }

	/**
	 * Set value for the reaction.
	 * @param value Reaction value.
	 */
	public void setValue(int value) {
		if (value > 0) this.value = 1;
		if (value < 0) this.value = -1;
	}

	/**
	 * Get reaction value.
	 * @return Reaction value.
	 */
	public int toInt() {
		return value;
	}
}

