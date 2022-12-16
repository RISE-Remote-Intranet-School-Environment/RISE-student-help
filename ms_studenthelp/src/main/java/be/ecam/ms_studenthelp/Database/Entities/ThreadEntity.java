package be.ecam.ms_studenthelp.Database.Entities;

import be.ecam.ms_studenthelp.Interfaces.IForumThread;
import be.ecam.ms_studenthelp.Object.ForumThread;
import be.ecam.ms_studenthelp.Object.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Thread entity that represent the "threads" table.
 */
@Entity
@Table(name = "threads")
public class ThreadEntity {
    @Id
    @Column(name = "id", unique = true)
    private String id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id")  // Foreign key
    private CategoryEntity category;

    @NonNull
    @Column(name = "answered")
    private boolean answered;

    @NonNull
    @Column(name = "date_posted")
    private LocalDateTime datePosted;

    @NonNull
    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_post", referencedColumnName = "id")
    private PostEntity firstPost;

    @OneToMany(mappedBy="thread", cascade = CascadeType.ALL)
    private Set<TagEntity> tags;

    /**
     * Default constructor.
     */
    protected ThreadEntity() {};

    /**
     * Constructor by its parameters. Usually used when the thread is in the database.
     * @param id Thread ID.
     * @param title Title.
     * @param category Category.
     * @param answered Is the thread answered.
     * @param datePosted Date posted.
     * @param dateModified Last modification date.
     * @param firstPost First post.
     * @param tags Tags set.
     */
    public ThreadEntity(
            @NonNull String id,
            @NonNull String title,
            @NonNull CategoryEntity category,
            boolean answered,
            @NonNull LocalDateTime datePosted,
            @NonNull LocalDateTime dateModified,
            @NonNull PostEntity firstPost,
            @NonNull Set<TagEntity> tags) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.answered = answered;
        this.datePosted = datePosted;
        this.dateModified = dateModified;
        this.firstPost = firstPost;
        this.tags = tags;
    }

    /**
     * Constructor by its parameters. Usually used when the thread is not in
     * the database, and you want to create a new one
     * @param title Title.
     * @param category Category.
     * @param firstPost First post.
     */
    public ThreadEntity(
            @NonNull String title,
            @NonNull CategoryEntity category,
            @NonNull PostEntity firstPost) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.category = category;
        this.answered = false;
        this.datePosted = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.firstPost = firstPost;
        this.tags = new HashSet<>();
    }

    /**
     * Getter for the thread ID.
     * @return Thread ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the title.
     * @return Title.
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the {@link CategoryEntity}.
     * @return Category.
     */
    public @NotNull CategoryEntity getCategory() {
        return category;
    }

    /**
     * Getter for the is-answered tag.
     * @return Is the thread answered.
     */
    public boolean getAnswered() {
        return answered;
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
     * Get first {@link PostEntity}.
     * @return First post.
     */
    public @NonNull PostEntity getFirstPost() {
        return firstPost;
    }

    /**
     * Get {@link TagEntity} sets.
     * @return Tags set
     */
    @NonNull
    public Set<TagEntity> getTags() {
        return tags;
    }

    /**
     * Set the thread ID.
     * @param id Thread ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set title.
     * @param title Title.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Set the {@link CategoryEntity}.
     * @param category Category.
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Set the in-answered flag.
     * @param answered Is the thread answered.
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
        this.dateModified = LocalDateTime.now();
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
     * Set the first {@link PostEntity}.
     * @param firstPost First post.
     */
    public void setFirstPost(@NonNull PostEntity firstPost) {
        this.firstPost = firstPost;
    }

    /**
     * Set the {@link TagEntity}'s set.
     * @param tags Tags set.
     */
    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Add a {@link TagEntity}.
     * @param tag Tag.
     */
    public void addTag(TagEntity tag) {
        this.tags.add(tag);
    }

    /**
     * Convert the {@link ThreadEntity} to a {@link IForumThread} object.
     * @return {@link IForumThread} object.
     */
    public IForumThread toForumThread() {
        Set<Tag> tags = this.tags
                .stream()
                .map(object -> new Tag(
                        object.getId(),
                        object.getTitle())
                ).collect(Collectors.toSet());

        return new ForumThread(
                id,
                title,
                answered,
                category.toCategory(),
                datePosted,
                dateModified,
                firstPost.toPost(),
                tags
        );
    }
}
