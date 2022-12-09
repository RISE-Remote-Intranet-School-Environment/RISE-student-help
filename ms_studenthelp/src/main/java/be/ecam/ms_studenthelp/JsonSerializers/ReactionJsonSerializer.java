package be.ecam.ms_studenthelp.JsonSerializers;

import be.ecam.ms_studenthelp.Object.Reaction;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Class that create a custom JSON representation of a {@link Reaction}
 */
@JsonComponent
public class ReactionJsonSerializer extends JsonSerializer<Reaction> {
    /**
     * Default constructor.
     */
    public ReactionJsonSerializer() {
        this(null);
    }

    /**
     * Constructor by the JSON serial. Used internally by the JSON Serializer package.
     * @param t Reaction class.
     */
    public ReactionJsonSerializer(Class<Reaction> t) {
        super();
    }

    /**
     *
     * @param value Value to serialize; can <b>not</b> be null.
     * @param gen Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     *   serializing Objects value contains, if any.
     * @throws IOException Generate if we cannot parse a field.
     */
    @Override
    public void serialize(Reaction value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("post", value.getPost().getId());
        gen.writeStringField("authorId", value.getAuthor().getId());
        gen.writeNumberField("value", value.getValue());
        gen.writeEndObject();
    }
}
