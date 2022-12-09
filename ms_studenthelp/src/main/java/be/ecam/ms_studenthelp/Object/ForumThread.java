package be.ecam.ms_studenthelp.Object;


import java.util.*;
import java.time.LocalDateTime;

import be.ecam.ms_studenthelp.Interfaces.IForumThread;
import be.ecam.ms_studenthelp.Interfaces.IPost;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

/**
 * Container for the thread informations.
 */
public class ForumThread implements IForumThread {
    @NonNull private final String id;
    @NonNull private String title;
    private boolean answered;
    @NonNull private Category category;
    @NonNull private final LocalDateTime datePosted;
    @NonNull private LocalDateTime dateModified;
    @NonNull private IPost firstPost;
    private Set<Tag> tags;

    /**
     * Constructor from its parameters. Usually used when the thread is loaded from the database.
     * @param id ID of the thread.
     * @param title Thread title.
     * @param answered Is answered.
     * @param category Category of the thread.
     * @param datePosted Date when the thread was posted.
     * @param dateModified Date of the last modification.
     * @param firstPost First post of the thread.
     * @param tags List of the tags linked to the thread.
     */
    public ForumThread(@NonNull String id,
                       @NonNull String title,
                       boolean answered,
                       @NonNull Category category,
                       @NonNull LocalDateTime datePosted,
                       @NonNull LocalDateTime dateModified,
                       @NonNull IPost firstPost,
                       Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.answered = answered;
        this.category = category;
        this.datePosted = datePosted;
        this.dateModified = dateModified;
        this.firstPost = firstPost;
        this.tags = tags;
    }

    /**
     * Constructor for a new thread that is no stored in the database.
     * @param title Thread title.
     * @param category Category of the thread.
     * @param firstPost First post of the thread.
     */
    public ForumThread(@NonNull String title,
                       @NonNull Category category,
                       @NonNull IPost firstPost) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.answered = false;
        this.category = category;
        this.datePosted = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.firstPost = firstPost;
        this.tags = new HashSet<>();
    }

    /**
     * Getter for the thread ID.
     * @return ID of the thread.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Getter for the thread title.
     * @return Thread title.
     */
    @Override
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the flag of the thread to know if it's answered.
     * @return Is the thread answered.
     */
    @Override
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Getter for the category of the thread.
     * @return Thread category.
     */
    @Override
    @NonNull
    public Category getCategory() {
        return category;
    }

    /**
     * Getter for the posted date of the thread.
     * @return Posted date of the thread.
     */
    @Override
    @NonNull
    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    /**
     * Getter for the date of the last modification.
     * @return Date of the last modification.
     */
    @Override
    @NonNull
    public LocalDateTime getDateModified() {
        return dateModified;
    }

    /**
     * Getter for the first post.
     * @return First post of the thread.
     */
    @Override
    @NotNull
    public IPost getFirstPost() {
        return firstPost;
    }

    /**
     * Getter for the tags linked to the thread.
     * @return Tags.
     */
    @Override
    public @NotNull Set<Tag> getTags() {
        return tags;
    }

    /**
     * Setter for the thread Title.
     * @param title Thread title.
     */
    @Override
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     * Set if the thread has been answered.
     * @param answered Answer flag of the thread.
     */
    @Override
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Setter for the category of the thread.
     * @param category Thread category.
     */
    @Override
    public void setCategory(@NonNull Category category) {
        this.category = category;
    }

    /**
     * Set the last modification date.
     * @param dateModified Last modification date.
     */
    @Override
    public void setDateModified(@NonNull LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * Setter first post for the thread.
     * @param firstPost First post.
     */
    @Override
    public void setFirstPost(@NotNull IPost firstPost) {
        this.firstPost = firstPost;
    }

    /**
     * Set the tags for the thread.
     * @param tags Tags for the thread.
     */
    @Override
    public void setTags(@NotNull Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Get a string representation of the thread.
     * @return Representation of the thread.
     */
    @Override
    public String toString() {
        return "Thread(" +
                "ID: " + id + "\n" +
                "Title: " + title + "\n" +
                "Answered: " + answered + "\n" +
                "Category: " + category.getTitle() + "\n" +
                "Date posted: " + datePosted + "\n" +
                "Date modified: " + dateModified + "\n" +
                "First post: " + firstPost.getContent() + "\n" +
                "Tags: " + tags + "\n" +
                ")";
    }
}
