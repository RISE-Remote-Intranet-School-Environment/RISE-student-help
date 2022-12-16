package be.ecam.ms_studenthelp.Database.Repositories;

import java.util.List;
import java.util.Optional;

import be.ecam.ms_studenthelp.Database.Entities.CategoryEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * Repository for the "categories" tables.
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    /**
     * Find all the {@link CategoryEntity}.
     * @return All the categories.
     */
    @NotNull List<CategoryEntity> findAll();

    /**
     * Find a {@link CategoryEntity} by its ID.
     * @param id ID of the category.
     * @return Optional with the {@link CategoryEntity} if found.
     */
    @NotNull Optional<CategoryEntity> findById(@NonNull long id);

    /**
     * Find a {@link CategoryEntity} by its title.
     * @param title Title.
     * @return Optional with the {@link CategoryEntity} if found.
     */
    @NotNull Optional<CategoryEntity> findByTitle(@NonNull String title);

    /**
     * Check if a {@link CategoryEntity} exists in the database given its title.
     * @param title Title of the category.
     * @return True if the {@link CategoryEntity} exists, false otherwise.
     */
    boolean existsByTitle(@NonNull String title);

    /**
     * Delete a {@link CategoryEntity} given its title.
     * @param title Title.
     */
    void deleteAllByTitle(@NonNull String title);
}
