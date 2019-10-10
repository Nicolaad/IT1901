import com.fasterxml.jackson.databind.ObjectMapper;
import core.UtgiftList;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;

    public class UtgiftListConfig extends ResourceConfig {

        public UtgiftListConfig() {
            this(new UtgiftList());
        }

        private static UtgiftList readValue(final String json) {
            try {
                final UtgiftList utgiftList = new UtgiftListObjectMapperProvider().getContext(ObjectMapper.class).readValue(json, UtgiftList.class);
                System.out.println("Read " + json + " as " + utgiftList);
                return utgiftList;
            } catch (final Exception e) {
                System.out.println("Exception when reading " + json + ": " + e);
            }
            return null;
        }

        public UtgiftListConfig(final String json) throws IOException {
            this(readValue(json));
        }

        public UtgiftListConfig(final UtgiftList utgiftList) {
            System.out.println("Serving " + utgiftList.toList());
            register(UtgiftListService.class);
            register(UtgiftListObjectMapperProvider.class);
            register(JacksonFeature.class);

            register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(utgiftList);
                }
            });

        }
    }

