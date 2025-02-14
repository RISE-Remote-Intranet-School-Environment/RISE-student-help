package be.ecam.ms_studenthelp.Controller;

import java.util.*;
import java.util.stream.Collectors;

import be.ecam.ms_studenthelp.Database.Entities.AuthorEntity;
import be.ecam.ms_studenthelp.Database.Entities.PostEntity;
import be.ecam.ms_studenthelp.Database.Entities.ReactionEntity;
import be.ecam.ms_studenthelp.Database.Repositories.AuthorRepository;
import be.ecam.ms_studenthelp.Database.Repositories.PostRepository;
import be.ecam.ms_studenthelp.Database.Repositories.ReactionRepository;
import be.ecam.ms_studenthelp.Interfaces.IPost;
import be.ecam.ms_studenthelp.Utils.DatabaseUtils;
import be.ecam.ms_studenthelp.Utils.ReactionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import be.ecam.ms_studenthelp.Object.Reaction;

@RestController
public class ReactionController {
    /**
     * Class that manage the API reaction.
     */
    @Autowired private ReactionRepository reactionRepository;
    @Autowired private PostRepository postRepository;
    @Autowired private AuthorRepository authorRepository;

    /**
     * Get the {@link Reaction} of a {@link IPost} by its ID.
     * @param postId ID of the post to get the reactions.
     * @return Set with the reactions linked to the post.
     */
	@GetMapping("/posts/{postId}/reactions")
	public Set<Reaction> getPostsPostIdReactions(@PathVariable("postId") String postId) {
        PostEntity postEntity = DatabaseUtils.getPostFromDatabase(postId, postRepository);
        Set<ReactionEntity> reactionEntities = postEntity.getReactions();

        // Convert ReactionEntity to Reaction
        return reactionEntities
                .stream()
                .map(ReactionEntity::toReaction)
                .collect(Collectors.toSet());
	}

    /**
     * Add/Update a {@link Reaction} to the {@link IPost} specified by its ID.
     * @param postId ID of the post to add/update a reaction
     * @param body   Body passed to the request in JSON format.
     * @return Reaction added/updated.
     */
	@PutMapping("/posts/{postId}/reactions")
	public Reaction putPostsPostIdReactions(@PathVariable("postId") String postId,
                                            @RequestBody String body) {
        PostEntity postEntity = DatabaseUtils.getPostFromDatabase(postId, postRepository);
        ReactionBody reactionBody = new ReactionBody(body);

        // Check the field passed in the body
        if ((reactionBody.getValue() == null) || (reactionBody.getAuthorId() == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Value and authorId should be presents in the body !");
        }
        if ((reactionBody.getValue() != 1) && (reactionBody.getValue() != -1)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Value should be 1 or -1 !");
        }

        // Get the author and the reaction from the database
        AuthorEntity authorEntity = DatabaseUtils.getAuthorFromDatabase(
                reactionBody.getAuthorId(),
                authorRepository);
        ReactionEntity reactionEntity = reactionRepository.findByPostAndAuthor(postEntity,
                authorEntity);

        // If the author has already reacted to this post, update his reaction
        if (reactionEntity == null) {
            reactionEntity = new ReactionEntity(reactionBody.getValue(), authorEntity, postEntity);
        } else {
            reactionEntity.setValue(reactionBody.getValue());
        }

        // Save the author if created
        authorRepository.save(authorEntity);
        reactionRepository.save(reactionEntity);

        return reactionEntity.toReaction();
	}

    /**
     * Delete all {@link Reaction}'s of a {@link IPost} specified by its ID.
     * @param postId ID of the post to delete the reaction.
     * @return Post where the reaction has been deleted.
     */
	@DeleteMapping("/posts/{postId}/reactions")
	public IPost deletePostsPostIdReactions(@PathVariable("postId") String postId) {
        PostEntity postEntity = DatabaseUtils.getPostFromDatabase(postId, postRepository);

        reactionRepository.deleteAllByPost(postEntity);
        return postEntity.toPost();
	}
}
