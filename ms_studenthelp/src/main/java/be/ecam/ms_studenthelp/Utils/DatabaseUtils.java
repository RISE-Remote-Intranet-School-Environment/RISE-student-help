package be.ecam.ms_studenthelp.Utils;

import be.ecam.ms_studenthelp.Object.Author;
import be.ecam.ms_studenthelp.Object.Category;
import be.ecam.ms_studenthelp.Object.ForumThread;
import be.ecam.ms_studenthelp.Object.Post;
import be.ecam.ms_studenthelp.Database.Entities.AuthorEntity;
import be.ecam.ms_studenthelp.Database.Entities.CategoryEntity;
import be.ecam.ms_studenthelp.Database.Entities.PostEntity;
import be.ecam.ms_studenthelp.Database.Entities.ThreadEntity;
import be.ecam.ms_studenthelp.Database.Repositories.AuthorRepository;
import be.ecam.ms_studenthelp.Database.Repositories.CategoryRepository;
import be.ecam.ms_studenthelp.Database.Repositories.PostRepository;
import be.ecam.ms_studenthelp.Database.Repositories.ThreadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Utils for the database communication.
 */
public class DatabaseUtils {
    /**
     * Get a {@link ForumThread} from the database by its ID.
     * @param threadId ID of the thread to get.
     * @param threadRepository Thread repository.
     * @return Thread entity associated.
     */
    public static ThreadEntity getForumThreadFromDatabase(String threadId,
                                                           ThreadRepository threadRepository) {
        Optional<ThreadEntity> optionalThreadEntity = threadRepository.findById(threadId);

        // If the thread does not exist, return a 404 error
        if (optionalThreadEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Thread %s not found !", threadId));
        }

        return optionalThreadEntity.get();
    }

    /**
     * Get {@link Post} from the database by its ID.
     * @param postId ID of the post to get.
     * @param postRepository Post repository.
     * @return Post entity related.
     */
    public static PostEntity getPostFromDatabase(String postId,
                                                 @NonNull PostRepository postRepository) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);

        // If the post does not exist, return a 404 error
        if (optionalPostEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found !");
        }

        return optionalPostEntity.get();
    }

    /**
     * Get {@link Category} from the database by its title.
     * @param category Title of the category.
     * @param categoryRepository Category repository.
     * @return Category entity related.
     */
    public static CategoryEntity getCategoryFromDatabase(String category,
                                                         @NonNull CategoryRepository categoryRepository) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findByTitle(category);

        if (optionalCategoryEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The category %s does not exists !", category));
        }

        return optionalCategoryEntity.get();
    }

    /**
     * Get {@link Author} from the database by its ID.
     * @param authorId ID of the author to get.
     * @param authorRepository Author repository.
     * @return Author entity related.
     */
    public static AuthorEntity getAuthorFromDatabase(String authorId,
                                                     @NonNull AuthorRepository authorRepository) {
        Optional<AuthorEntity> optionalAuthorEntity = authorRepository.findById(authorId);

        if (optionalAuthorEntity.isEmpty()) {
            return new AuthorEntity(authorId);
        }

        return optionalAuthorEntity.get();
    }
}
