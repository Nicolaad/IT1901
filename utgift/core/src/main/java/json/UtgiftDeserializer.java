package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.Utgift;


import java.io.IOException;

public class UtgiftDeserializer extends JsonDeserializer {

    @Override
    public Utgift deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public Utgift deserialize(JsonNode jsonNode) {
            final ObjectNode objectNode = (ObjectNode) jsonNode;
            final String name = objectNode.get(UtgiftSerializer.NAME_FIELD_NAME).asText();
            final double price = objectNode.get(UtgiftSerializer.PRICE_FIELD_NAME).asDouble();
            final String category = objectNode.get(UtgiftSerializer.CATEGORY_FIELD_NAME).asText();
            return new Utgift(name, Double.toString(price), category); //why is price a string? temp fix
    }
}

