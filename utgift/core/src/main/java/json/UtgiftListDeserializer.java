package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import core.Utgift;
import core.UtgiftList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class UtgiftListDeserializer extends JsonDeserializer<UtgiftList> {

    private final UtgiftDeserializer utgiftDeserializer = new UtgiftDeserializer();

    /**
     * oppdaterer utgiftlisten med innholdet med en liste formatert som en json streng.
     * @param jsonParser brukes for å lese json
     * @param deserializationContext beskriver hvordan oppsettet til Utgift og Utgiftlist
     * @throws IOException om den ikke greier å lese json noden fra parseren
     */
    @Override
    public UtgiftList deserialize(final JsonParser jsonParser,
                                  final DeserializationContext deserializationContext) throws IOException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        if (jsonNode instanceof ArrayNode) {
            final ArrayNode utgiftArray = (ArrayNode) jsonNode;
            final Collection<Utgift> utgifter = new ArrayList<>(utgiftArray.size());
            for (final JsonNode utgiftNode : utgiftArray) {
                final Utgift utgift = utgiftDeserializer.deserialize(utgiftNode);
                utgifter.add(utgift);
            }
            return new UtgiftList(utgifter);
        }
        return null;
    }

}
