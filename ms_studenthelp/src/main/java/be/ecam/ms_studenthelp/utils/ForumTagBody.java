package be.ecam.ms_studenthelp.utils;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Body for a tag received in an API request.
 */
public class ForumTagBody {
    private @Nullable final String tag;

    /**
     * Constructor from the tag.
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
     * Getter for the tag title.
     * @return Tag title.
     */
    public @Nullable String getTag(){
        return tag;
    }
}
