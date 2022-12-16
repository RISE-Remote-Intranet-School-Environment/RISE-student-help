package be.ecam.ms_studenthelp.Database.Entities;

import be.ecam.ms_studenthelp.Object.Category;
import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * Category entity that represent the "categories" table.
 */
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @NonNull
    @Column(name = "title")
    private String title;

    /**
     * Default constructor.
     */
    protected CategoryEntity() {}

    /**
     * Constructor by its title.
     * @param title Category title.
     */
    public CategoryEntity(@NonNull String title) {
        this.title = title;
    }

    /**
     * Getter for the category ID.
     * @return Category ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter for the category title.
     * @return Category title.
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Set the category title.
     * @param title Category title.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     * Get a {@link String} representation of {@link CategoryEntity}.
     * @return {@link String} representation.
     */
    @Override
    public String toString() {
        return String.format("Categories[id=%d, title='%s']", id, title);
    }

    /**
     * Convert a {@link CategoryEntity} to a {@link Category} object.
     * @return {@link Category} object.
     */
    public Category toCategory() {
        return new Category(id, title);
    }
}
