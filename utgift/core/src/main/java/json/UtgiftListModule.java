package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import core.Utgift;
import core.UtgiftList;


/**
 * A Module for UtgiftList serialization and deserializiation.
 *
 */
public class UtgiftListModule extends Module {

    @Override
    public String getModuleName() {
        return "UtgiftListModule";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    private final SimpleSerializers serializers = new SimpleSerializers();
    private final SimpleDeserializers deserializers = new SimpleDeserializers();

    /**
     * Initializes the UtgiftListModule with appropriate serializers.
     */

    public UtgiftListModule() {
        serializers.addSerializer(Utgift.class, new UtgiftSerializer());
        serializers.addSerializer(UtgiftList.class, new UtgiftListSerializer());
        deserializers.addDeserializer(Utgift.class, new UtgiftDeserializer());
        deserializers.addDeserializer(UtgiftList.class, new UtgiftListDeserializer());
    }

    @Override
    public void setupModule(final SetupContext context) {
        context.addSerializers(serializers);
        context.addDeserializers(deserializers);
    }
}
