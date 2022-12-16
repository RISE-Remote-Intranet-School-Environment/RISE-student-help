package be.ecam.ms_studenthelp.Controller;

import be.ecam.ms_studenthelp.Database.entities.AuthorEntity;
import be.ecam.ms_studenthelp.Database.entities.PostEntity;
import be.ecam.ms_studenthelp.Database.repositories.AuthorRepository;
import be.ecam.ms_studenthelp.Database.repositories.PostRepository;
import be.ecam.ms_studenthelp.utils.DatabaseUtils;
import be.ecam.ms_studenthelp.utils.PostBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import be.ecam.ms_studenthelp.Interfaces.IPost;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PostController {
    /**
     * Post that manage the Post API.
     */
    @Autowired private PostRepository postRepository;
    @Autowired private AuthorRepository authorRepository;

    /**
     *  Get {@link IPost} by its ID.
     *  @param postId ID of the post to get.
     *  @return Post corresponding to the ID.
     */
    @GetMapping(value = "/posts/{postId}", produces="application/json")
    public IPost GetPostByPostId(@PathVariable("postId") String postId) {
        return DatabaseUtils.getPostFromDatabase(postId, postRepository).toPost();
    }


    /**
     * Patch request to update the content of a {@link IPost}.
     * @param postId ID of the post to update.
     * @param body Body passed to the request in a JSON format.
     * @return Post with the content updated.
     */
    @PatchMapping(value = "/posts/{postId}", produces="application/json")
    public IPost PatchPostByPostId(@PathVariable("postId") String postId,
                                   @RequestBody String body) {
        PostEntity postEntity = DatabaseUtils.getPostFromDatabase(postId, postRepository);
        PostBody postBody = new PostBody(body);

        // If no content passed, return a bad request error
        if (postBody.getContent() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A content must be set !");
        }

        // Update the content
        postEntity.setContent(postBody.getContent());
        postRepository.save(postEntity);

        return postEntity.toPost();
    }

    /**
     *  Put request to answer to a {@link IPost} by its ID.
     * @param postId ID of the post to answer.
     * @param body  Body sent to the request in JSON format.
     * @return Child that have been created.
     */
    @PutMapping(value = "/posts/{postId}/answers", produces="application/json")
    public IPost ReplyPostByPostId(@PathVariable("postId") String postId, @RequestBody String body) {
        PostEntity postEntity = DatabaseUtils.getPostFromDatabase(postId, postRepository);
        PostBody postBody = new PostBody(body);

        // Check if the authorId and content are passed
        if ((postBody.getContent() == null) ||(postBody.getAuthorId() == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Content and authorId must be passed !");
        }

        AuthorEntity authorEntity = DatabaseUtils.getAuthorFromDatabase(
                postBody.getAuthorId(), authorRepository);
        PostEntity childPostEntity = new PostEntity(postBody.getContent(), authorEntity);

        // Save information in the database
        // The post child is saved thanks to cascade.ALL
        childPostEntity.setParent(postEntity);
        authorRepository.save(authorEntity);
        postRepository.save(childPostEntity);

        return childPostEntity.toPost();
    }

    /**
     * Delete a {@link IPost} by its ID.
     * @param postId ID of the post to delete.
     * @return Post that have been deleted.
     */
    @DeleteMapping(value = "/posts/{postId}", produces = "application/json")
    public IPost deletePostByPostId(@PathVariable("postId") String postId) {
        PostEntity postEntity = DatabaseUtils.getPostFromDatabase(postId, postRepository);

        // You can't delete the first post, delete the thread instead
        if (postEntity.getParent() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You can't delete the first post !");
        }

        postRepository.deleteById(postId);

        return postEntity.toPost();
    }

    /**
     * Get all the {@link IPost} that exists in the databases.
     * @return List with the post that are in the database.
     */
    @GetMapping(value = "/posts", produces = "application/json")
    public List<IPost> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();

        // Convert PostEntity to Post
        return postEntities
                .stream()
                .map(PostEntity::toPost)
                .collect(Collectors.toList());
    }
}
