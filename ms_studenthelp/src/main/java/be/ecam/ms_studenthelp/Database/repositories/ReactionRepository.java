package be.ecam.ms_studenthelp.Database.repositories;

import be.ecam.ms_studenthelp.Database.entities.AuthorEntity;
import be.ecam.ms_studenthelp.Database.entities.PostEntity;
import be.ecam.ms_studenthelp.Database.entities.ReactionEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Repository for the "reactions" tables.
 */
@Repository
public interface ReactionRepository extends CrudRepository<ReactionEntity, Long> {
    /**
     * Find all the {@link ReactionEntity}.
     * @return All the reactions.
     */
    @NotNull List<ReactionEntity> findAll();

    /**
     * Get a {@link ReactionEntity} by its ID.
     * @param id must not be {@literal null}.
     * @return Optional with the reaction if found.
     */
    @Override
    @NotNull
    Optional<ReactionEntity> findById(@NotNull Long id);

    /**
     * Find a {@link ReactionEntity} by a {@link PostEntity}.
     * @param post Post.
     * @return Reaction related to the post.
     */
    ReactionEntity findByPost(@NonNull PostEntity post);

    /**
     * Find a {@link ReactionEntity} by its {@link AuthorEntity}.
     * @param author Author.
     * @return Reaction related to the author.
     */
    ReactionEntity findByAuthor(@NonNull AuthorEntity author);

    /**
     * Find a {@link ReactionEntity} by a {@link PostEntity} and an {@link AuthorEntity}.
     * @param post Post.
     * @param author Author.
     * @return Reaction related to the post and author.
     */
    ReactionEntity findByPostAndAuthor(@NonNull PostEntity post, @NonNull AuthorEntity author);

    /**
     * Find a {@link ReactionEntity} by its value.
     * @param value Value.
     * @return Reaction related to the value.
     */
    ReactionEntity findReactionByValue(int value);

    /**
     * Delete all the {@link ReactionEntity} related to a {@link PostEntity}.
     * @param post Post.
     */
    @Transactional
    void deleteAllByPost(@NonNull PostEntity post);
}
