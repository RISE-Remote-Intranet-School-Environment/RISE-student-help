package be.ecam.ms_studenthelp.Object;

import org.springframework.lang.NonNull;

/**
 * Container for the tag information.
 */
public class Tag {
    private final long id;
    private @NonNull String title;

    /**
     * Constructor.
     * @param id ID of the tag.
     * @param title Title of the tag.
     */
    public Tag(long id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Getter for the tag ID.
     * @return Tag ID.
     */
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
     * Set the tag title.
     * @param title Tag title.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
