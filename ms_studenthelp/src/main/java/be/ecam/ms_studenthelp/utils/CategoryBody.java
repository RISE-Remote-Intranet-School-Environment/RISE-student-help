package be.ecam.ms_studenthelp.utils;

import be.ecam.ms_studenthelp.Database.entities.CategoryEntity;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.lang.NonNull;

import java.util.Map;

public class CategoryBody {
    private @NonNull String title;

    public CategoryBody(@NonNull String title) {
        this.title = title;
    }

    /*
        {
            "category": "Math"
        }
     */
    public static CategoryBody fromBody(@NonNull String body) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String,Object> body_data = springParser.parseMap(body);

        return new CategoryBody(body_data.get("title") != null ? (String) body_data.get("title") : "");
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
