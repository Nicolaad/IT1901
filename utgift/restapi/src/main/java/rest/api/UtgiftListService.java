package rest.api;

import core.Utgift;
import core.UtgiftList;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(UtgiftListService.UTGIFT_LIST_SERVICE_PATH)
public class UtgiftListService {
    public static final String UTGIFT_LIST_SERVICE_PATH = "utgiftList";

    @Inject
    private UtgiftList utgiftList;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UtgiftList getUtgiftList() {
        return utgiftList;
    }

    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift getUtgift(@PathParam("num") int num) {
        if (num >= 0 && num <= utgiftList.toList().size())  {
            return utgiftList.toList().get(num);
        }
        throw new IllegalArgumentException("index out of bounds");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addUtgifter(final List<Utgift> utgifter) {
        utgiftList.addUtgifter(utgifter);
        //return this.latLongs.addLatLongs(latLongs.toArray(new LatLong[latLongs.size()]));
    }

    @DELETE
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift deleteUtgift(@PathParam("num") int num) {
        if (num >= 0 && num <= utgiftList.toList().size()) {
            return utgiftList.toList().remove(num);
        }
        throw new IllegalArgumentException("index out of bounds");
    }
/*
    // Sorterer utgiftlist på Kategori
    @GET
    @Path("/{kategori}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utgift> getKategoriUtgiftList(@PathParam("kategori") String kategori) {
        return utgiftList.toList().stream().filter(u -> u.getKategori().equals(kategori)).collect(Collectors.toList());
    }

    // Sorterer ut utgift objekt på først kategori og så på index
    @GET
    @Path("/{kategori}/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift getKategoriUtgift(@PathParam("kategori") String kategori, @PathParam("num") int num) {
        return getKategoriUtgiftList(kategori).get(num);
    }
*/
}
