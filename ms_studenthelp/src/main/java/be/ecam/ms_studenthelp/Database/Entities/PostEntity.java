package be.ecam.ms_studenthelp.Database.Entities;

import be.ecam.ms_studenthelp.Interfaces.IPost;
import be.ecam.ms_studenthelp.Object.Post;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Post entity that represent the "posts" table.
 */
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @Column(name = "id", unique = true)
    private String id;

    @NonNull
    @Column(name = "content")
    private String content;

    @NonNull
    @Column(name = "upvotes")
    private int upVotes;

    @NonNull
    @Column(name = "downvotes")
    private int downVotes;

    @NonNull
    @Column(name = "date_posted")
    private LocalDateTime datePosted;

    @NonNull
    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")  // Foreign key
    private PostEntity parent;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @NonNull
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<ReactionEntity> reactions;

    @NonNull
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<PostEntity> children;

    /**
     * Constructor by its parameters. Usually used when the post is not in the
     * database, and you want to create a new one.
     * @param content Post content.
     * @param upVotes Up votes values.
     * @param downVotes Down votes values.
     * @param datePosted Posted date.
     * @param dateModified Last modification date.
     * @param parent Parent of the post.
     * @param author Author of the post.
     * @param reactions Reactions linked to the post.
     * @param children Children linked to the post.
     */
    public PostEntity(@NonNull String content,
                      @NonNull int upVotes,
                      @NonNull int downVotes,
                      @NonNull LocalDateTime datePosted,
                      @NonNull LocalDateTime dateModified,
                      @Nullable PostEntity parent,
                      @NonNull AuthorEntity author,
                      @NonNull Set<ReactionEntity> reactions,
                      @NonNull Set<PostEntity> children) {
        id = UUID.randomUUID().toString();
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.datePosted = datePosted;
        this.dateModified = dateModified;
        this.parent = parent;
        this.author = author;
        this.reactions = reactions;
        this.children = children;
    }

    /**
     * Constructor by its content and author.
     * @param content Post content.
     * @param author Author of the post.
     */
    public PostEntity(@NonNull String content,
                      @NonNull AuthorEntity author) {
        id = UUID.randomUUID().toString();
        this.content = content;
        this.upVotes = 0;
        this.downVotes = 0;
        this.datePosted = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();;
        this.parent = null;
        this.author = author;
        this.reactions = new HashSet<>();
        this.children = new HashSet<>();
    }

    /**
     * Default constructor.
     */
    public PostEntity() {}

    /**
     * Getter for the post ID.
     * @return Post ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the content.
     * @return Content of the post.
     */
    @NonNull
    public String getContent() {
        return content;
    }

    /**
     * Getter for the up votes value.
     * @return Up votes.
     */
    public int getUpVotes() {
        return upVotes;
    }

    /**
     * Getter for the down votes value.
     * @return Down votes.
     */
    public int getDownVotes() {
        return downVotes;
    }

    /**
     * Getter for the posted date in {@link LocalDateTime}.
     * @return Posted date.
     */
    @NonNull
    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    /**
     * Getter for the last modification date in {@link LocalDateTime}.
     * @return Last modification date.
     */
    @NonNull
    public LocalDateTime getDateModified() {
        return dateModified;
    }

    /**
     * Get {@link AuthorEntity} of the post.
     * @return Author.
     */
    public AuthorEntity getAuthor() {
        return author;
    }

    /**
     * Get parent of the post.
     * @return Parent.
     */
    @Nullable
    public PostEntity getParent() {
        return parent;
    }

    /**
     * Get the {@link ReactionEntity}'s linked to the post.
     * @return Reaction set.
     */
    @NonNull
    public Set<ReactionEntity> getReactions() {
        return reactions;
    }

    /**
     * Get the children linked to the post.
     * @return Children set.
     */
    @NonNull
    public Set<PostEntity> getChildren() {
        return children;
    }

    /**
     * Set the post ID.
     * @param id Post ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the content.
     * @param content Content.
     */
    public void setContent(@NonNull String content) {
        this.content = content;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Set the up votes value.
     * @param upVotes Up votes.
     */
    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
        this.dateModified = LocalDateTime.now();

        if (this.upVotes < 0) {
            this.upVotes = 0;
        }
    }

    /**
     * Set the down votes value.
     * @param downVotes Down votes.
     */
    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
        this.dateModified = LocalDateTime.now();

        if (this.downVotes < 0) {
            this.downVotes = 0;
        }
    }

    /**
     * Set the date posted in {@link LocalDateTime}.
     * @param datePosted Date posted.
     */
    public void setDatePosted(@NonNull LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    /**
     * Set the last modification date in {@link LocalDateTime}.
     * @param dateModified Last modification date.
     */
    public void setDateModified(@NonNull LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * Set author of the post with an {@link AuthorEntity}.
     * @param author Author.
     */
    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    /**
     * Set the parent of the post with an {@link PostEntity}.
     * @param parent Parent.
     */
    public void setParent(@Nullable PostEntity parent) {
        this.parent = parent;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Set reactions set with {@link ReactionEntity}.
     * @param reactions Reactions set.
     */
    public void setReactions(@NonNull Set<ReactionEntity> reactions) {
        this.reactions = reactions;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Convert a {@link PostEntity} to a {@link Post} object.
     * @return {@link Post} object.
     */
    public IPost toPost() {
        IPost parent = this.parent != null ? this.parent.toPost() : null;

        return new Post(
                id,
                content,
                upVotes,
                downVotes,
                datePosted,
                dateModified,
                author.toAuthor(),
                parent);
    }
}
