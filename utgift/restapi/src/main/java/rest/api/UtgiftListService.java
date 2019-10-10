package rest.api;

import core.Utgift;
import core.UtgiftList;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path(UtgiftListService.UTGIFT_LIST_SERVICE_PATH)
public class UtgiftListService {
    public static final String UTGIFT_LIST_SERVICE_PATH = "utgiftlist";

    @Inject
    private UtgiftList utgiftList;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UtgiftList getUtgiftList() {
        return utgiftList;
    }

/*    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift getUtgift(@PathParam("num") int num) {
        if (num >= 0 && num <= utgiftList.toList().size())  {
            return utgiftList.toList().get(num);
        }
        throw new IllegalArgumentException("index out of bounds");
    } */

    @DELETE
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift deleteUtgift(@PathParam("num") int num) {
        if (num >= 0 && num <= utgiftList.toList().size()) {
            return utgiftList.toList().remove(num);
        }
        throw new IllegalArgumentException("index out of bounds");
    }

    @GET
    @Path("/{kategori}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utgift> getKategoriUtgift(@PathParam("kategori") String kategori) {
        return utgiftList.toList().stream().filter(u -> u.getKategori().equals(kategori)).collect(Collectors.toList());
    }


}
