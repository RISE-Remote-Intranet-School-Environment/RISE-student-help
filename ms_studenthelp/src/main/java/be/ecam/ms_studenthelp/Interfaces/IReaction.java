package be.ecam.ms_studenthelp.Interfaces;

import be.ecam.ms_studenthelp.Object.Author;

public interface IReaction {
	/**
	 * Getter for the post linked to the reaction.
	 * @return Post.
	 */
	public IPost getPost();

	/**
	 * Getter for the author linked to the reaction.
	 * @return Author.
	 */
	public Author getAuthor();

	/**
	 * Getter for the reaction value.
	 * @return Value.
	 */
	public int getValue();

	/**
	 * Set value for the reaction.
	 * @param value Reaction value.
	 */
	public void setValue(int value);

	/**
	 * Get reaction value.
	 * @return Reaction value.
	 */
	public int toInt();
}


