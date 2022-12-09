package be.ecam.ms_studenthelp.Interfaces;

import be.ecam.ms_studenthelp.Object.Category;
import be.ecam.ms_studenthelp.Object.Tag;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Interface to implement threads.
 */
public interface IForumThread {
    /**
     * Getter for the thread ID.
     * @return ID of the thread.
     */
    @NonNull
    String getId();

    /**
     * Getter for the thread title.
     * @return Thread title.
     */
    @NonNull
    String getTitle();

    /**
     * Getter for the flag of the thread to know if it's answered.
     * @return Is the thread answered.
     */
    boolean isAnswered();

    /**
     * Getter for the category of the thread.
     * @return Thread category.
     */
    @NonNull
    Category getCategory();

    /**
     * Getter for the posted date of the thread.
     * @return Posted date of the thread.
     */
    @NonNull
    LocalDateTime getDatePosted();

    /**
     * Getter for the date of the last modification.
     * @return Date of the last modification.
     */
    @NonNull
    LocalDateTime getDateModified();

    /**
     * Getter for the first post.
     * @return First post of the thread.
     */
    IPost getFirstPost();

    /**
     * Getter for the tags linked to the thread.
     * @return Tags.
     */
    @NonNull
    Set<Tag> getTags();

    /**
     * Setter for the thread Title.
     * @param title Thread title.
     */
    void setTitle(@NonNull String title);

    /**
     * Set if the thread has been answered.
     * @param answered Answer flag of the thread.
     */
    void setAnswered(boolean answered);

    /**
     * Setter for the category of the thread.
     * @param category Thread category.
     */
    void setCategory(@NonNull Category category);

    /**
     * Set the last modification date.
     * @param dateModified Last modification date.
     */
    void setDateModified(@NonNull LocalDateTime dateModified);

    /**
     * Setter first post for the thread.
     * @param firstPost First post.
     */
    void setFirstPost(IPost firstPost);

    /**
     * Set the tags for the thread.
     * @param tags Tags for the thread.
     */
    void setTags(@NonNull Set<Tag> tags);

    /**
     * Get a string representation of the thread.
     * @return Representation of the thread.
     */
    @Override
    String toString();
}
