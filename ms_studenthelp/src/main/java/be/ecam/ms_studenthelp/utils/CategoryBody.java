package be.ecam.ms_studenthelp.utils;

import be.ecam.ms_studenthelp.Object.Category;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * Body for a category received in an API request.
 */
public class CategoryBody {
    private @NonNull String title;

    /**
     * Constructor from the title of the {@link Category}.
     * @param title Title of the category.
     */
    public CategoryBody(@NonNull String title) {
        this.title = title;
    }

    /**
     * Constructor from a body received from the database.
     * @param body Body received from the API.
     */
    public static CategoryBody fromBody(@NonNull String body) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String,Object> body_data = springParser.parseMap(body);

        return new CategoryBody(body_data.get("title") != null ? (String) body_data.get("title") : "");
    }

    /**
     * Getter for the title of the {@link Category}.
     * @return Title of the category.
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the {@link Category}.
     * @param title New title for the category.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
