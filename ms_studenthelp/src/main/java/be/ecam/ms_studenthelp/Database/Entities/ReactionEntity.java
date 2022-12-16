package be.ecam.ms_studenthelp.Database.Entities;

import be.ecam.ms_studenthelp.Object.Reaction;
import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * Reaction entity that represent the "reactions" table.
 */
@Entity
@Table(name = "reactions")
public class ReactionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "value")
    private int value;

    @NonNull
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "author_id", referencedColumnName = "id")  // Foreign key
    private AuthorEntity author;

    @NonNull
    @ManyToOne
    @JoinColumn(name="post_id")
    private PostEntity post;

    /**
     * Default constructor.
     */
    protected ReactionEntity() {};

    /**
     * Construction by its parameters.
     * @param value Reaction value.
     * @param author Author of the reaction.
     * @param post Post linked.
     */
    public ReactionEntity(int value, @NonNull AuthorEntity author, @NonNull PostEntity post) {
        this.value = value;
        this.author = author;
        this.post = post;
    }

    /**
     * Getter for the reaction ID.
     * @return Reaction ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for the value.
     * @return Value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter for the {@link AuthorEntity}.
     * @return Author.
     */
    @NonNull
    public AuthorEntity getAuthor() {
        return author;
    }

    /**
     * Getter for the {@link PostEntity} linked to the reaction.
     * @return Post.
     */
    @NonNull
    public PostEntity getPost() {
        return post;
    }

    /**
     * Set the reaction ID.
     * @param id Reaction ID.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Set the value.
     * @param value Value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Set the {@link AuthorEntity}.
     * @param author Author.
     */
    public void setAuthor(@NonNull AuthorEntity author) {
        this.author = author;
    }

    /**
     * Set the {@link PostEntity} linked.
     * @param post Post.
     */
    public void setPost(@NonNull PostEntity post) {
        this.post = post;
    }

    /**
     * Convert the {@link ReactionEntity} to a {@link Reaction} object.
     * @return {@link Reaction} object.
     */
    public Reaction toReaction() {
        return new Reaction(post.toPost(), author.toAuthor(), value);
    }
}
