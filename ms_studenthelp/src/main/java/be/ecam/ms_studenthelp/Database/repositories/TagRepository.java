package be.ecam.ms_studenthelp.Database.repositories;

import be.ecam.ms_studenthelp.Database.entities.TagEntity;
import be.ecam.ms_studenthelp.Database.entities.ThreadEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the "tags" tables.
 */
@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {
    /**
     * Find all the {@link TagEntity}.
     * @return All the tags.
     */
    @NotNull List<TagEntity> findAll();

    /**
     * Find a {@link TagEntity} by its ID.
     * @param id ID of the tag.
     * @return Optional with the tag if found.
     */
    @NotNull Optional<TagEntity> findById(@NonNull long id);

    /**
     * Find a {@link TagEntity} given its title.
     * @param title Title.
     * @return Tag related to the title.
     */
    TagEntity findByTitle(@NonNull String title);

    /**
     * Find a {@link TagEntity} given a {@link ThreadEntity};
     * @param thread Thread.
     * @return Tag.
     */
    TagEntity findByThread(ThreadEntity thread);

    /**
     * Find a {@link TagEntity} given a title and a {@link ThreadEntity};
     * @param title Title.
     * @param thread Thread.
     * @return Tag related to the title and the thread.
     */
    TagEntity findByTitleAndThread(@NonNull String title, @NonNull ThreadEntity thread);

    /**
     * Check if a {@link TagEntity} by a title and a {@link ThreadEntity}.
     * @param title Title.
     * @param thread Thread.
     * @return True if the tag exists, false otherwise.
     */
    boolean existsByTitleAndThread(@NonNull String title, @NonNull ThreadEntity thread);
}
