package be.ecam.ms_studenthelp.Object;

import be.ecam.ms_studenthelp.Interfaces.IPost;
import org.jetbrains.annotations.Nullable;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Container for the Post information.
 */
public class Post implements IPost{
    @NonNull private final String id;
    @NonNull private String content;
    private int upVotes = 0;
    private int downVotes = 0;
    @NonNull private final LocalDateTime datePosted;
    @NonNull private LocalDateTime dateModified;
    @NonNull private final Author author;
    @Nullable private IPost parent;

    /**
     * Constructor. Usually used to get post from the database.
     * @param id Post ID.
     * @param content Content of the post.
     * @param upVotes UpVotes of the post.
     * @param downVotes DownVotes of the post.
     * @param datePosted Posted date.
     * @param dateModified Last modification date.
     * @param author Author of the post.
     * @param parent Parent of the post.
     */
    public Post(@NonNull String id,
                @NonNull String content,
                int upVotes,
                int downVotes,
                @NonNull LocalDateTime datePosted,
                @NonNull LocalDateTime dateModified,
                @NonNull Author author,
                @Nullable IPost parent) {
        this.id = id;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.datePosted = datePosted;
        this.dateModified = dateModified;
        this.author = author;
        this.parent = parent;
    }

    /**
     * Create a new post that is not stored in the database.
     * @param content Post content.
     * @param author Author of the post.
     * @param parent Parent of the post.
     */
    public Post(@NonNull String content,
                @NonNull Author author,
                @Nullable IPost parent) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.upVotes = 0;
        this.downVotes = 0;
        this.datePosted = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.author = author;
        this.parent = parent;
    }

    /**
     * Getter for the post ID.
     * @return Post ID.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Get author of the Post.
     * @return Author of the post.
     */
    @Override
    @NonNull
    public Author getAuthor() {
        return author;
    }

    /**
     * Getter for the up votes.
     * @return Up votes.
     */
    @Override
    public int getUpVotes() {
        return upVotes;
    }

    /**
     * Getter for the down votes.
     * @return Down votes.
     */
    @Override
    public int getDownVotes() {
        return downVotes;
    }

    /**
     * Getter for the posted date.
     * @return Posted date.
     */
    @Override
    @NonNull
    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    /**
     * Getter for the last modification date.
     * @return Last modification date.
     */
    @Override
    @NonNull
    public LocalDateTime getDateModified() {
        return dateModified;
    }

    /**
     * Getter for the post content.
     * @return Post content.
     */
    @Override
    @NonNull
    public String getContent() {
        return content;
    }

    /**
     * Getter for the parent of the post.
     * @return Parent of the post.
     */
    @Override
    public @Nullable IPost getParent() {
        return parent;
    }

    /**
     * Set the post content.
     * @param content Post content.
     */
    @Override
    public void setContent(@NonNull String content) {
        this.content = content;
        dateModified = LocalDateTime.now();
    }

    /**
     * Set up votes value.
     * @param upVotes Up votes.
     */
    @Override
    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
        dateModified = LocalDateTime.now();
    }

    /**
     * Set the down votes value.
     * @param downVotes Down votes.
     */
    @Override
    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
        dateModified = LocalDateTime.now();
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
     * Set the parent of the post.
     * @param parent Parent of the post.
     */
    @Override
    public void setParent(IPost parent) {
        this.parent = parent;
        dateModified = LocalDateTime.now();
    }

    /**
     * Increment the up votes value.
     * @param upVotes Value to increment the up votes.
     */
    @Override
    public void incrementUpVotes(int upVotes) {
        this.upVotes += upVotes;
        dateModified = LocalDateTime.now();
    }

    /**
     * Increment the down votes value.
     * @param downVotes Value to increment the down votes.
     */
    @Override
    public void incrementDownVotes(int downVotes) {
        this.downVotes += downVotes;
        dateModified = LocalDateTime.now();
    }

    /**
     * Decrement the up votes value.
     * @param upVotes Value to decrement the up votes.
     */
    @Override
    public void decrementUpVotes(int upVotes) {
        this.upVotes -= upVotes;
        dateModified = LocalDateTime.now();

        if (this.upVotes < 0) {
            this.upVotes = 0;
        }
    }

    /**
     * Decrement the down votes value.
     * @param downVotes Value to decrement the down votes.
     */
    @Override
    public void decrementDownVotes(int downVotes) {
        this.downVotes -= downVotes;
        dateModified = LocalDateTime.now();

        if (this.downVotes < 0) {
            this.downVotes = 0;
        }
    }
}
