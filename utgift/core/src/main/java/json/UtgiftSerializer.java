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

    @Override
    public void serialize(final Utgift utgift, final JsonGenerator jsonGen, final SerializerProvider provider) throws IOException {

        jsonGen.writeStartObject();
        jsonGen.writeFieldName(NAME_FIELD_NAME);
        jsonGen.writeString(utgift.getNavn());
        jsonGen.writeFieldName(PRICE_FIELD_NAME);
        jsonGen.writeNumber(utgift.getPris());
        jsonGen.writeFieldName(CATEGORY_FIELD_NAME);
        jsonGen.writeString(utgift.getKategori());
        jsonGen.writeEndObject();
    }
}
