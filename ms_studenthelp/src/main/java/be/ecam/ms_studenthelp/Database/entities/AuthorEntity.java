package be.ecam.ms_studenthelp.Database.entities;

import be.ecam.ms_studenthelp.Object.Author;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.UUID;

/**
 * Author entity that represent the "authors" table.
 */
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @Column(name = "id", unique = true)
    private String id;

    /**
     * Default constructor. Usually used when the Author is not in the database.
     */
    protected AuthorEntity() {
        id = UUID.randomUUID().toString();
    }

    /**
     * Constructor by its ID. Usually when the Author is already in the database.
     * @param id Author ID.
     */
    public AuthorEntity(@NonNull String id) {
        this.id = id;
    }

    /**
     * Getter for the ID.
     * @return Author ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID.
     * @param id Author ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Convert the {@link AuthorEntity} to the corresponding {@link Author} object.
     * @return {@link Author} object.
     */
    public Author toAuthor() {
        return new Author(id);
    }
}
