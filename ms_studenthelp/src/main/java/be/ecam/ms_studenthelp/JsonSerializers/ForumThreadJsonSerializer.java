package be.ecam.ms_studenthelp.JsonSerializers;

import be.ecam.ms_studenthelp.Object.ForumThread;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Class that create a custom JSON representation of a {@link ForumThread}
 */
@JsonComponent
public class ForumThreadJsonSerializer extends JsonSerializer<ForumThread> {
    /**
     * Default constructor.
     */
    public ForumThreadJsonSerializer() {
        this(null);
    }

    /**
     * Constructor by the JSON serial. Used internally by the JSON Serializer package.
     * @param t ForumThread class.
     */
    public ForumThreadJsonSerializer(Class<ForumThread> t) {
        super();
    }

    /**
     * Function where we declare the JSON output.
     * @param value Value to serialize; can <b>not</b> be null.
     * @param gen Generator used to output resulting Json content
     * @param provider Provider that can be used to get serializers for
     *   serializing Objects value contains, if any.
     * @throws IOException Generate if we cannot parse a field.
     */
    @Override
    public void serialize(ForumThread value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeBooleanField("answered", value.isAnswered());
        gen.writeStringField("category", value.getCategory().getTitle());
        gen.writeStringField("datePosted", value.getDatePosted().toString());
        gen.writeStringField("dateModified", value.getDateModified().toString());
        gen.writeStringField("firstPost", value.getFirstPost().getId());

        // Create a list with every tag title
        gen.writeArrayFieldStart("tags");
        value.getTags().forEach(tag -> {
            try {
                gen.writeString(tag.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
