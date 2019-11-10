package rest.api;

import core.Utgift;
import core.UtgiftList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(UtgiftListService.UTGIFT_LIST_SERVICE_PATH)
public class UtgiftListService {
    public static final String UTGIFT_LIST_SERVICE_PATH = "utgiftlist";

    @Inject
    private UtgiftList utgiftList;

    /**
     * returnerer alle utgifter
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UtgiftList getUtgiftList() {
        System.out.println("getAll");
        return utgiftList;
    }

    /*
    @GET
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift getUtgift(@PathParam("num") int num) {
        System.out.println("get");
        if (num >= 0 && num <= utgiftList.toList().size())  {
            return utgiftList.toList().get(num);
        }
        throw new IllegalArgumentException("index out of bounds");
    }
*/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int addUtgift(final List<Utgift> utgifter) {
        System.out.println("POSTING");
        utgiftList.toList().addAll(utgifter);
        return 0;
    }

    /**
     * Fjerner en utgift fra serveren basert på index
     *
     * @param num
     */

    @DELETE
    @Path("/{kategori}/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift deleteUtgift(@PathParam("kategori") String kategori, @PathParam("num") int num) {
        System.out.println("delete funker nå");
        try {
            return utgiftList.toList().remove(num);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @DELETE
    @Path("/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift deleteUtgift(@PathParam("num") int num) {
        System.out.println("delete funker nå");
        Utgift u = null;
        try {
            Iterator<Utgift> it = utgiftList.toList().iterator();
            while (it.hasNext()) {
                if (it.next().getId() == num) {
                    it.remove();
                    break;
                }
            }

            return u;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    /**
     * henter en utgiftliste med alle utgiftene med samme kategori som parameteret
     *
     * @param kategori
     * @return
     */
    @GET
    @Path("/{kategori}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utgift> getKategoriUtgiftList(@PathParam("kategori") String kategori) {
        return utgiftList.toList().stream().filter(u -> u.getKategori().equals(kategori)).collect(Collectors.toList());
    }


    /**
     * sHenter eugiftobjekt etter kategori og så index
     *
     * @param kategori
     * @param num
     * @return
     */
    @GET
    @Path("/{kategori}/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift getKategoriUtgift(@PathParam("kategori") String kategori, @PathParam("num") int num) {
        return getKategoriUtgiftList(kategori).get(num);
    }

}
