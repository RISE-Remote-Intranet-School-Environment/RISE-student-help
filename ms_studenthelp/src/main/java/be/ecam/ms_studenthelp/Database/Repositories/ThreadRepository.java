package be.ecam.ms_studenthelp.Database.Repositories;

import be.ecam.ms_studenthelp.Database.Entities.ThreadEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Repository for the "threads" tables.
 */
@Repository
public interface ThreadRepository extends CrudRepository<ThreadEntity, String> {
    /**
     * Find all the {@link ThreadEntity}.
     * @return All the threads.
     */
    @NotNull List<ThreadEntity> findAll();

    /**
     * Find a {@link ThreadEntity} by its ID.
     * @param id must not be {@literal null}.
     * @return Optional with the thread if found.
     */
    @Override
    @NotNull
    Optional<ThreadEntity> findById(@NotNull String id);

    /**
     * Find a {@link ThreadEntity} given its title.
     * @param title Title.
     * @return Thread.
     */
    ThreadEntity findByTitle(@NonNull String title);

    /**
     * Delete a {@link ThreadEntity} by its ID.
     * @param id must not be {@literal null}.
     */
    @Override
    void deleteById(@NotNull String id);
}
