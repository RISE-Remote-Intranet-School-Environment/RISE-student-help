package be.ecam.ms_studenthelp.utils;
import be.ecam.ms_studenthelp.Object.ForumThread;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Body for a thread received in an API request.
 */
public class ForumThreadBody {
    private @Nullable final String title;
    private @Nullable final List<String> tags;
    private @Nullable final String category;
    private final boolean answered;
    private @Nullable final PostBody firstPost;

    /**
     * Constructor from its parameters.
     * @param title Title of the thread.
     * @param tags List with the tags.
     * @param category Category of the thread.
     * @param answered Is the thread answered.
     * @param firstPost First post of the thread.
     */
    public ForumThreadBody(@Nullable String title,
                           @Nullable List<String> tags,
                           @Nullable String category,
                           boolean answered,
                           @Nullable PostBody firstPost) {
        this.title = title;
        this.tags = tags;
        this.category = category;
        this.answered = answered;
        this.firstPost = firstPost;
    }

    /**
     * Constructor from a body received from the API.
     * @param body Body received from the API.
     */
    public ForumThreadBody(String body) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String,Object> body_data = springParser.parseMap(body);

        // Set the parameters from the body
        title = (String) body_data.get("title");
        tags = (List<String>) body_data.get("tags");
        category = (String) body_data.get("category");
        answered = body_data.get("answered") != null && (Boolean) body_data.get("answered");

        if (body_data.get("firstPost") != null) {
            firstPost = new PostBody((HashMap<String, String>) body_data.get("firstPost"));
        } else {
            firstPost = null;
        }
    }

    /**
     * Getter for the title of the {@link ForumThread}.
     * @return Title of the thread.
     */
    public @Nullable String getTitle() {
        return title;
    }

    /**
     * Getter for the list of the tags.
     * @return List with all the tags.
     */
    public @Nullable List<String> getTags() {
        return tags;
    }

    /**
     * Getter for the category.
     * @return Category of the thread.
     */
    public @Nullable String getCategory() {
        return category;
    }

    /**
     * Getter for the first post.
     * @return First post of the thread.
     */
    public @Nullable PostBody getFirstPost() {
        return firstPost;
    }

    /**
     * Get if the thread is answered or not.
     * @return Is the thread answered.
     */
    public boolean isAnswered() {
        return answered;
    }
}
