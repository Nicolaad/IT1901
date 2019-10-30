package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Utgift;
import core.UtgiftList;

import java.io.IOException;

public class UtgiftListSerializer extends JsonSerializer<UtgiftList> {

    /**
     * Konverterer en utgiftliste til en liste med utgifter i json streng format.
     * @param utgiftList soom skal konvereres til json
     * @param jsonGen genererator til json
     * @param provider nødvendig for å overwrite standardmetoden
     * @throws IOException om den ikke greier å skrive objekter til json
     */
    @Override
    public void serialize(final UtgiftList utgiftList,
                          final JsonGenerator jsonGen, final SerializerProvider provider) throws IOException {
        jsonGen.writeStartArray(utgiftList.getUtgifter().size());
        for (final Utgift utgift : utgiftList.getUtgifter()) {
            jsonGen.writeObject(utgift);
        }
        jsonGen.writeEndArray();
    }

}
