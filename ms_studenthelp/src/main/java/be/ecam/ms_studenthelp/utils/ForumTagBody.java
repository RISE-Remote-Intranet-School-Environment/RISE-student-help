package be.ecam.ms_studenthelp.utils;

import be.ecam.ms_studenthelp.Object.Tag;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Body for a tag received in an API request.
 */
public class ForumTagBody {
    private @Nullable final String tag;

    /**
     * Constructor from the {@link Tag}.
     * @param tag Title of the tag.
     */
    public ForumTagBody(@Nullable String tag) {
        this.tag = tag;

    }

    /**
     * Constructor from the body received by the API.
     * @param body Body received from the API.
     * @return A tag body.
     */
    public static ForumTagBody fromBody(String body) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String,Object> body_data = springParser.parseMap(body);

        return new ForumTagBody((String) body_data.get("tag"));
    }

    /**
     * Getter for the {@link Tag} title.
     * @return Tag title.
     */
    public @Nullable String getTag(){
        return tag;
    }
}
