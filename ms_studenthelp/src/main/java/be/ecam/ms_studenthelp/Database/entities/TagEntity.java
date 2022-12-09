package be.ecam.ms_studenthelp.Database.entities;

import be.ecam.ms_studenthelp.Object.Tag;
import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * Tag entity that represent the "tags" table.
 */
@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "thread_id", referencedColumnName = "id")
    private ThreadEntity thread;

    /**
     * Default constructor.
     */
    protected TagEntity() {}

    /**
     * Constructor by its parameters.
     * @param title Tag title.
     * @param thread Thread linked.
     */
    public TagEntity(@NonNull String title, @NonNull ThreadEntity thread) {
        this.title = title;
        this.thread = thread;
    }

    /**
     * Getter for the tag ID.
     * @return Tag ID.
     */
    @NonNull
    public long getId() {
        return id;
    }

    /**
     * Getter for the tag title.
     * @return Tag title.
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the {@link ThreadEntity}.
     * @return Thread.
     */
    @NonNull
    public ThreadEntity getThread() {
        return thread;
    }

    /**
     * Set the tag ID.
     * @param id Tag ID.
     */
    public void setId(@NonNull long id) {
        this.id = id;
    }

    /**
     * Set the title.
     * @param title Title.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     * Set the {@link ThreadEntity}.
     * @param thread Thread.
     */
    public void setThread(@NonNull ThreadEntity thread) {
        this.thread = thread;
    }

    /**
     * Convert {@link TagEntity} to a {@link Tag} object.
     * @return {@link Tag} object.
     */
    public Tag toTag() {
        return new Tag(id, title);
    }
}
