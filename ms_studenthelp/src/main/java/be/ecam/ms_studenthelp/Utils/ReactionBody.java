package be.ecam.ms_studenthelp.Utils;

import org.jetbrains.annotations.Nullable;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * Body for a reaction received in an API request.
 */
public class ReactionBody {
    private @Nullable final Integer value;
    private @Nullable final String authorId;

    /**
     * Constructor from its parameters.
     * @param value Value for the reaction.
     * @param authorId ID of the author of the reaction.
     */
    public ReactionBody(int value, @Nullable String authorId) {
        this.value = value;
        this.authorId = authorId;
    }

    /**
     * Constructor from a body to parse.
     * @param body Body received by the API.
     */
    public ReactionBody(@NonNull String body) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String,Object> body_data = springParser.parseMap(body);

        value = (Integer) body_data.get("value");
        authorId = (String) body_data.get("authorId");
    }

    /**
     * Get the value of the reaction.
     * @return Value of the reaction.
     */
    public @Nullable Integer getValue() {
        return value;
    }

    /**
     * Get the author ID of the reaction.
     * @return Author ID.
     */
    public @Nullable String getAuthorId() {
        return authorId;
    }
}
