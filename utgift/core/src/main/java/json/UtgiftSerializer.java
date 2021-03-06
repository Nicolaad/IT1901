package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Utgift;

import java.io.IOException;

public class UtgiftSerializer extends JsonSerializer<Utgift> {

    public static final String NAME_FIELD_NAME = "navn";
    public static final String PRICE_FIELD_NAME = "pris";
    public static final String CATEGORY_FIELD_NAME = "kategori";
    public static final String ID_FIELD_NAME = "id";

    /**
     * konverterer et utgiftobjekt til en json streng.
     * @param utgift utgiften som skal skrives til json
     * @param jsonGen json generatoren
     * @throws IOException om den ikke greier å skrive utgiften til json
     */
    @Override
    public void serialize(final Utgift utgift, final JsonGenerator jsonGen,
                          final SerializerProvider provider) throws IOException {

        jsonGen.writeStartObject();
        jsonGen.writeFieldName(NAME_FIELD_NAME);
        jsonGen.writeString(utgift.getNavn());
        jsonGen.writeFieldName(PRICE_FIELD_NAME);
        jsonGen.writeNumber(utgift.getPris());
        jsonGen.writeFieldName(CATEGORY_FIELD_NAME);
        jsonGen.writeString(utgift.getKategori());
        jsonGen.writeFieldName(ID_FIELD_NAME);
        jsonGen.writeNumber(utgift.getId());
        jsonGen.writeEndObject();
    }
}
