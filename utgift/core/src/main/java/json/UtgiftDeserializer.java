package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.Utgift;

import java.io.IOException;
import java.util.ArrayList;

public class UtgiftDeserializer extends JsonDeserializer {

    private static final int ARRAY_NODE_SIZE = 3;

    @Override
    public Utgift deserialize(final JsonParser jsonParser,
                              final DeserializationContext deserializationContext) throws IOException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode);
    }

    /**
     * Konverterer en jsonNode til et utgiftsobjekt.
     *
     * @param jsonNode representerer et json objekt
     * @return
     */
    public Utgift deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            final ObjectNode objectNode = (ObjectNode) jsonNode;
            final String name = objectNode.get(UtgiftSerializer.NAME_FIELD_NAME).asText();
            final double price = objectNode.get(UtgiftSerializer.PRICE_FIELD_NAME).asDouble();
            final String category = objectNode.get(UtgiftSerializer.CATEGORY_FIELD_NAME).asText();
            return new Utgift(name, Double.toString(price), category); //why is price a string? temp fix
        } else if (jsonNode instanceof ArrayNode) {
            final ArrayNode utgiftArray = (ArrayNode) jsonNode;
            if (utgiftArray.size() == ARRAY_NODE_SIZE) {
                final String navn = utgiftArray.get(0).asText();
                final String pris = utgiftArray.get(1).asText();
                final String kategori = utgiftArray.get(2).asText();
                return new Utgift(navn, pris, kategori);
            }
        }
        return null;
    }
}
