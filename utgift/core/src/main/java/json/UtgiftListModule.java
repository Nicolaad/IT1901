package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import core.UtgiftList;


/**
 * A Module for LatLongs serialization/deserialization.
 * @author hal
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
     * Initializes the LatLongsModule with appropriate serializers.
     */

    public UtgiftListModule() {
        serializers.addSerializer(UtgiftList.class, new UtgiftListSerializer());
        serializers.addSerializer(UtgiftList.class, new UtgiftListSerializer());
        deserializers.addDeserializer(UtgiftList.class, new UtgiftListDeserializer());
        deserializers.addDeserializer(UtgiftList.class, new UtgiftListDeserializer());
    }

    @Override
    public void setupModule(final SetupContext context) {
        context.addSerializers(serializers);
        context.addDeserializers(deserializers);
    }
}
