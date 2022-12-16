package be.ecam.ms_studenthelp.Utils;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Body for a post received in an API request.
 */
public class PostBody {
    @Nullable private final String authorId;
    @Nullable private final String content;

    /**
     * Constructor from its parameters.
     * @param authorId Author ID of the post owner.
     * @param content Content of the post.
     */
    public PostBody(@Nullable String authorId, @Nullable String content) {
        this.authorId = authorId;
        this.content = content;
    }

    /**
     * Constructor from a body received by the API.
     * @param body Body received by the API.
     */
    public PostBody(String body) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String,Object> body_data = springParser.parseMap(body);

        authorId = body_data.get("authorId").toString();
        content = body_data.get("content").toString();
    }

    /**
     * Constructor from a HashMap with the corresponding parameters.
     * @param body Body received by the API in HashMap.
     */
    public PostBody(HashMap<String, String> body) {
        authorId = body.get("authorId");
        content = body.get("content");
    }

    /**
     * Getter for the Author ID.
     * @return Author ID.
     */
    public @Nullable String getAuthorId() {
        return authorId;
    }

    /**
     * Getter for the content of the post.
     * @return Content of the post.
     */
    public @Nullable String getContent() {
        return content;
    }
}
