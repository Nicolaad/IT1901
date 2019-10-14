package rest.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Utgift;
import core.UtgiftList;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import rest.api.UtgiftListService;
import rest.api.UtgiftObjectMapperProvider;

import java.io.IOException;
import java.util.Arrays;

public class UtgiftListConfig extends ResourceConfig {

    public UtgiftListConfig() {
        this(new UtgiftList());
    }

    private static UtgiftList readValue(final String json) {
        try {
            final UtgiftList utgiftList = new UtgiftObjectMapperProvider().getContext(ObjectMapper.class).readValue(json, UtgiftList.class);
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
        utgiftList.addUtgifter(Arrays.asList(new Utgift("Fisk","200.0","Mat")));
        register(UtgiftListService.class);
        register(UtgiftObjectMapperProvider.class);
        register(JacksonFeature.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(utgiftList);
            }
        });

    }
}

