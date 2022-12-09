package be.ecam.ms_studenthelp.Database.repositories;

import be.ecam.ms_studenthelp.Database.entities.AuthorEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the "authors" tables.
 */
@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, String> {
    /**
     * Find all the {@link  AuthorEntity}.
     * @return All the authors.
     */
    @NotNull List<AuthorEntity> findAll();

    /**
     * Find the {@link AuthorEntity} by its ID.
     * @param id must not be {@literal null}.
     * @return Optional with the author if found.
     */
    @Override
    @NotNull
    Optional<AuthorEntity> findById(@NotNull String id);
}
