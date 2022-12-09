package be.ecam.ms_studenthelp.JsonSerializers;

import be.ecam.ms_studenthelp.Object.Post;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Class that create a custom JSON representation of a {@link Post}
 */
@JsonComponent
public class PostJsonSerializer extends JsonSerializer<Post> {
    /**
     * Default constructor.
     */
    public PostJsonSerializer() {
        this(null);
    }

    /**
     * Constructor by the JSON serial. Used internally by the JSON Serializer package.
     * @param t Post class.
     */
    public PostJsonSerializer(Class<Post> t) {
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
    public void serialize(Post value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String parent = value.getParent() != null ? value.getParent().getId() : null;

        gen.writeStartObject();
        gen.writeStringField("id", value.getId());
        gen.writeStringField("content", value.getContent());
        gen.writeNumberField("upVotes", value.getUpVotes());
        gen.writeNumberField("downVotes", value.getDownVotes());
        gen.writeStringField("datePosted", value.getDatePosted().toString());
        gen.writeStringField("dateModified", value.getDateModified().toString());
        gen.writeStringField("authorId", value.getAuthor().getId());
        gen.writeStringField("parent", parent);
        gen.writeEndObject();
    }
}
