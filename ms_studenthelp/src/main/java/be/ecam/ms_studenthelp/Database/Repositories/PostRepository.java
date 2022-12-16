package be.ecam.ms_studenthelp.Database.Repositories;

import be.ecam.ms_studenthelp.Database.Entities.PostEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the "posts" tables.
 */
@Repository
public interface PostRepository extends CrudRepository<PostEntity, String> {
    /**
     * Find all the {@link PostEntity}.
     * @return All the posts.
     */
    @Override
    @NotNull List<PostEntity> findAll();

    /**
     * Find a {@link PostEntity} by its ID.
     * @param id must not be {@literal null}.
     * @return Optional with the post if found.
     */
    @Override
    @NotNull Optional<PostEntity> findById(@NotNull String id);

    /**
     * Find a {@link PostEntity} given its parent.
     * @param parent Parent.
     * @return Post found.
     */
    PostEntity findByParent(@Nullable PostEntity parent);

    /**
     * Delete all the {@link PostEntity} given in the list.
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     */
    @Override
    void deleteAll(@NotNull Iterable<? extends PostEntity> entities);
}
