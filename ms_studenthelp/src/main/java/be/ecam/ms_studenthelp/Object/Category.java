package be.ecam.ms_studenthelp.Object;

import org.springframework.lang.NonNull;

/**
 * Container for the category informations.
 */
public class Category {
    private final long id;

    @NonNull
    private final String title;

    /**
     * Constructor for the {@link Category} by its parameters.
     * @param id ID of the category in the database.
     * @param title Title of the category.
     */
    public Category(long id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Get ID of the category.
     * @return ID of the category.
     */
    public long getId() {
        return id;
    }

    /**
     * Get title of the category.
     * @return Title of the category.
     */
    @NonNull
    public String getTitle() {
        return title;
    }
}
