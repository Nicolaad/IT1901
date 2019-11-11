package rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import json.UtgiftListModule;


@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtgiftObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;


    /**
     * Konstruktør som registerer en objekt mapper som er er linket til en UtgiftListModule,
     * som er knyttet til serializerne til Utgift og UtgiftList
     */
    public UtgiftObjectMapperProvider() {
        objectMapper = new ObjectMapper().registerModule(new UtgiftListModule());
    }

    /**
     * returnerer objectmapperen.
     *
     * @param type klassetypen
     * @return objektmapper
     */
    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return objectMapper;
    }
}
