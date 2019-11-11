package rest.api;

import core.Utgift;
import core.UtgiftList;
import java.io.File;
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
import json.Save;

@Path(UtgiftListService.UTGIFT_LIST_SERVICE_PATH)
public class UtgiftListService {

    public static final String UTGIFT_LIST_SERVICE_PATH = "utgiftlist";

    @Inject
    private UtgiftList utgiftList;

    /**
     * returnerer alle utgifter
     *
     * @return et utgiftList objekt
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

    /**
     * Legger til en utgiftliste
     * @param utgifter utgifter som skal legges til
     * @return 0
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int addUtgift(final List<Utgift> utgifter) {
        System.out.println("POSTING");
        utgiftList.toList().addAll(utgifter);
        this.save();
        return 0;
    }

    /**
     * lagrer utgiftene til fil
     */
    private void save() {
        Save.save(utgiftList.toList(), new File("../core/src/main/resources/json/save.json"));
    }

    /**
     * Sletter en utgift basert på kategori og index
     * @param kategori Kategorien utgiftene blir filtrert til
     * @param num indexen på utgiften etter filtreringen
     * @return null
     */
    @DELETE
    @Path("/{kategori}/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift deleteUtgift(@PathParam("kategori") String kategori, @PathParam("num") int num) {
        System.out.println("delete funker nå");
        try {
            Utgift u = utgiftList.toList().remove(num);
            this.save();
            return u;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Sletter en utgift basert på index
     * @param num indexen
     * @return null
     */
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
            this.save();
            return u;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    /**
     * henter en utgiftliste med alle utgiftene med samme kategori som parameteret
     *
     * @param kategori kategorien på utgiftene som skal hentes
     * @return en liste med utgifter basert på kategorien
     */
    @GET
    @Path("/{kategori}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utgift> getKategoriUtgiftList(@PathParam("kategori") String kategori) {
        return utgiftList.toList().stream().filter(u -> u.getKategori().equals(kategori)).collect(Collectors.toList());
    }


    /**
     * Henter eugiftobjekt etter kategori og så index
     *
     * @param kategori kategorien til utgiften
     * @param num indexen etter den har blitt filtrert iht kategori
     * @return utgiften som matcher parameterne
     */
    @GET
    @Path("/{kategori}/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utgift getKategoriUtgift(@PathParam("kategori") String kategori, @PathParam("num") int num) {
        return getKategoriUtgiftList(kategori).get(num);
    }

}
