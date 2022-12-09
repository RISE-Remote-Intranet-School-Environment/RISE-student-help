package be.ecam.ms_studenthelp.Interfaces;

import be.ecam.ms_studenthelp.Object.Author;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

/**
 * Interface for the Posts.
 */
public interface IPost {
    /**
     * Getter for the post ID.
     * @return Post ID.
     */
    @NonNull String getId();

    /**
     * Get author of the Post.
     * @return Author of the post.
     */
    @NonNull Author getAuthor();

    /**
     * Getter for the up votes.
     * @return Up votes.
     */
    int getUpVotes();

    /**
     * Getter for the down votes.
     * @return Down votes.
     */
    int getDownVotes();

    /**
     * Getter for the posted date.
     * @return Posted date.
     */
    @NonNull LocalDateTime getDatePosted();

    /**
     * Getter for the last modification date.
     * @return Last modification date.
     */
    @NonNull LocalDateTime getDateModified();

    /**
     * Getter for the post content.
     * @return Post content.
     */
    @NonNull String getContent();

    /**
     * Getter for the parent of the post.
     * @return Parent of the post.
     */
    IPost getParent();

    /**
     * Set the post content.
     * @param content Post content.
     */
    void setContent(@NonNull String content);

    /**
     * Set up votes value.
     * @param upVotes Up votes.
     */
    void setUpVotes(int upVotes);

    /**
     * Set the down votes value.
     * @param downVotes Down votes.
     */
    void setDownVotes(int downVotes);

    /**
     * Set the last modification date.
     * @param dateModified Last modification date.
     */
    void setDateModified(@NonNull LocalDateTime dateModified);

    /**
     * Set the parent of the post.
     * @param parent Parent of the post.
     */
    void setParent(IPost parent);

    /**
     * Increment the up votes value.
     * @param upVotes Value to increment the up votes.
     */
    void incrementUpVotes(int upVotes);

    /**
     * Increment the down votes value.
     * @param downVotes Value to increment the down votes.
     */
    void incrementDownVotes(int downVotes);

    /**
     * Decrement the up votes value.
     * @param upVotes Value to decrement the up votes.
     */
    void decrementUpVotes(int upVotes);

    /**
     * Decrement the down votes value.
     * @param downVotes Value to decrement the down votes.
     */
    void decrementDownVotes(int downVotes);
}
