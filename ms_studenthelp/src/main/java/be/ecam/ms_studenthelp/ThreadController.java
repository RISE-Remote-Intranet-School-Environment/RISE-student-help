package be.ecam.ms_studenthelp;

import be.ecam.ms_studenthelp.Database.entities.*;
import be.ecam.ms_studenthelp.Database.repositories.AuthorRepository;
import be.ecam.ms_studenthelp.Database.repositories.CategoryRepository;
import be.ecam.ms_studenthelp.Database.repositories.TagRepository;
import be.ecam.ms_studenthelp.Database.repositories.ThreadRepository;
import be.ecam.ms_studenthelp.Object.*;
import be.ecam.ms_studenthelp.utils.DatabaseUtils;
import be.ecam.ms_studenthelp.utils.ForumTagBody;
import be.ecam.ms_studenthelp.utils.ForumThreadBody;
import be.ecam.ms_studenthelp.utils.PostBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import be.ecam.ms_studenthelp.Interfaces.IForumThread;
import be.ecam.ms_studenthelp.Interfaces.IPost;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class ThreadController {
    private @Autowired ThreadRepository threadRepository;
    private @Autowired CategoryRepository categoryRepository;
    private @Autowired AuthorRepository authorRepository;
    private @Autowired TagRepository tagRepository;


    /**
     * Create a {@link ForumThread} with the parameters passed in the body.
     * @param body Body passed to the request in JSON format.
     * @return Thread that have been created.
     */
    @PostMapping("/threads")
    public IForumThread PostThreads(@RequestBody String body) {
        ForumThreadBody forumThreadBody = new ForumThreadBody(body);

        // If title, tags or category is not specified, return a 3xx error
        if ((forumThreadBody.getTitle() == null) || (forumThreadBody.getTags() == null) ||
                (forumThreadBody.getCategory() == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters !");
        }

        CategoryEntity categoryEntity = DatabaseUtils.getCategoryFromDatabase(
                forumThreadBody.getCategory(), categoryRepository);

        // Check if the first post is defined
        if (forumThreadBody.getFirstPost() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A first post must be defined !");
        }

        IPost firstPost = postFromPostBody(forumThreadBody.getFirstPost());

        // Check if the first post is defined
        if (firstPost == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A first post must be defined !");
        }

        AuthorEntity authorEntity = DatabaseUtils.getAuthorFromDatabase(
                firstPost.getAuthor().getId(),
                authorRepository);
        PostEntity postEntity = new PostEntity(
                firstPost.getContent(),
                firstPost.getUpVotes(),
                firstPost.getDownVotes(),
                firstPost.getDatePosted(),
                firstPost.getDateModified(),
                null, // No child when the thread is created
                authorEntity,
                new HashSet<>(),
                new HashSet<>()
        );

        ThreadEntity threadEntity = new ThreadEntity(
                forumThreadBody.getTitle(),
                categoryEntity,
                postEntity);
        authorRepository.save(authorEntity);
        threadRepository.save(threadEntity);

        return threadEntity.toForumThread();
    }

    /**
     * Get a {@link ForumThread} by its ID.
     * @param threadId ID of the thread to get.
     * @return Thread loaded from the database.
     */
    @GetMapping("/threads/{threadId}")
    public IForumThread getThreadsThreadId(@PathVariable("threadId") String threadId) {
        return DatabaseUtils.getForumThreadFromDatabase(threadId, threadRepository)
                .toForumThread();
    }

    /**
     * Update the content, title and {@link Tag}'s of a {@link ForumThread} specified by its ID.
     * @param threadId ID of the thread to update.
     * @param body Body passed to the request in JSON format.
     * @return Thread that have been updated.
     */
    @PatchMapping("/threads/{threadId}")
    public IForumThread patchThreadsThreadId(@PathVariable("threadId") String threadId, @RequestBody String body) {
        ThreadEntity threadEntity = DatabaseUtils.getForumThreadFromDatabase(threadId,
                threadRepository);
        ForumThreadBody forumThreadBody = new ForumThreadBody(body);

        // Set new values for the current thread
        if (forumThreadBody.getTitle() != null) {
            threadEntity.setTitle(forumThreadBody.getTitle());
        }
        if (forumThreadBody.getTags() != null) {
            Set<TagEntity> tagEntities = new HashSet<>();

            for (String tag : forumThreadBody.getTags()) {
                TagEntity tagEntity = tagRepository.findByTitleAndThread(tag, threadEntity);

                tagEntities.add(tagEntity != null ? tagEntity : new TagEntity(tag, threadEntity));
            }

            threadEntity.setTags(tagEntities);
        }

        if (forumThreadBody.getCategory() != null) {
            threadEntity.setCategory(DatabaseUtils.getCategoryFromDatabase(
                    forumThreadBody.getCategory(),
                    categoryRepository));
        }
        threadEntity.setAnswered(forumThreadBody.isAnswered());

        threadRepository.save(threadEntity);
        return threadEntity.toForumThread();
    }

    /**
     * Delete a {@link ForumThread} specified by its ID.
     * @param threadId ID of the thread to be deleted.
     * @return Thread that have been deleted.
     */
    @DeleteMapping("/threads/{threadId}")
    public IForumThread DeleteForumThreadTitle(@PathVariable("threadId") String threadId) {
        ThreadEntity threadEntity = DatabaseUtils.getForumThreadFromDatabase(
                threadId,
                threadRepository);

        threadRepository.deleteById(threadEntity.getId());
        return threadEntity.toForumThread();
    }


    /**
     * Get all the {@link ForumThread} that exists in the database.
     * @return List with all the threads present in the database.
     */
    @GetMapping("/threads")
    public List<ForumThread> GetForumThreadPages() {
        List<ThreadEntity> threadEntities = threadRepository.findAll();

        // Convert all the ThreadEntity to ForumThread
        return threadEntities
                .stream()
                .map(object -> new ForumThread(
                        object.getId(),
                        object.getTitle(),
                        object.getAnswered(),
                        object.getCategory().toCategory(),
                        object.getDatePosted(),
                        object.getDateModified(),
                        object.getFirstPost().toPost(),
                        object.getTags()
                                .stream()
                                .map(tag -> new Tag(tag.getId(), tag.getTitle()))
                                .collect(Collectors.toSet()))
                ).collect(Collectors.toList());
    }


    /**
     * Get the {@link Tag}'s related to a {@link ForumThread} specified by its ID.
     * @param threadId ID of the thread to get the tags.
     * @return List with the tags related to the thread.
     */
    @GetMapping("/threads/{threadId}/tags")
    public Set<Tag> getThreadsThreadIdTags(@PathVariable("threadId") String threadId) {

        return DatabaseUtils.getForumThreadFromDatabase(threadId, threadRepository).toForumThread().getTags();
    }


    /**
     * Add a {@link Tag}'s to a {@link ForumThread} specified by its ID.
     * @param threadId ID of the thread to add the tag.
     * @param body Body passed to the request in JSON format.
     * @return Tags contained by the thread ID.
     */
    @PostMapping("/threads/{threadId}/tags")
    public Set<Tag> postTagThreadId(@PathVariable("threadId") String threadId, @RequestBody String body) {
        ThreadEntity threadEntity = DatabaseUtils.getForumThreadFromDatabase(threadId, threadRepository);
        ForumTagBody forumTagBody = ForumTagBody.fromBody(body);
        String tag = forumTagBody.getTag();

        if (tag != null) {
            TagEntity tagEntity = new TagEntity(tag, threadEntity);

            threadEntity.addTag(tagEntity);
            threadRepository.save(threadEntity);
        } else if (forumTagBody.getTag() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No tag mentioned !");
        }

        return threadEntity.toForumThread().getTags();
    }


    /**
     * Delete a {@link ForumThread} by its title and thread ID.
     * @param tagTitle Title of the tag to delete.
     * @param threadId ID of the thread where delete the tags.
     * @return Remaining tags of the threads.
     */
    @DeleteMapping("/threads/{threadId}/tags/{tagtitle}")
    public Set<Tag> DeleteTagFromThread(@PathVariable("tagtitle") String tagTitle, @PathVariable("threadId") String threadId) {

        ThreadEntity threadEntity = DatabaseUtils.getForumThreadFromDatabase(threadId, threadRepository);
        TagEntity tag = tagRepository.findByTitleAndThread(tagTitle, threadEntity);
        tagRepository.deleteById(tag.getId());

        return threadEntity.toForumThread().getTags();
    }

    private static IPost postFromPostBody(@NonNull PostBody postBody) {
        if ((postBody.getContent() == null) || (postBody.getAuthorId() == null)) {
            return null;
        }

        return new Post(postBody.getContent(), new Author(postBody.getAuthorId()), null);
    }
}
