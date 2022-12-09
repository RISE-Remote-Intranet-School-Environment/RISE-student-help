package be.ecam.ms_studenthelp.Object;

import org.springframework.lang.NonNull;

/**
 * Container for the Author data.
 */
public class Author {
    @NonNull
    private final String id;

    /**
     * Construct {@link Author} by its ID.
     * @param id Author ID.
     */
    public Author(@NonNull String id) {
        this.id = id;
    }

    /**
     * Get ID of the author.
     * @return ID of the author.
     */
    @NonNull
    public String getId() {
        return id;
    }
}
