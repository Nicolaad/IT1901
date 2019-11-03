package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Utgift;
import core.UtgiftList;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;




public class UtgiftListSerializingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new UtgiftListModule());
    }

    //hjelpemetoder
    private Utgift utgift1() {
        Utgift u1 = new Utgift("ost", "53.0", "mat");
        u1.setId(1);
        return u1;
    }

    private Utgift utgift2() {
        Utgift u2 = new Utgift("bok", "1621.0", "skole");
        u2.setId(2);
        return u2;
    }

    /**
     * Tester at objektmapperen gir riktig output når den konverterer flere objekter til en jsonstreng
     * @throws JsonProcessingException om den ikke greier å skrive objektene fra utgift til inputJson
     */
    @Test
    public void testUtgiftListSerialization() throws JsonProcessingException {
        String inputJson = objectMapper.writeValueAsString(new UtgiftList(utgift1(),utgift2()));
        String expectedJson = "[{\"navn\":\"ost\",\"pris\":53.0,\"kategori\":\"mat\",\"id\":1}"
                + ",{\"navn\":\"bok\",\"pris\":1621.0,\"kategori\":\"skole\",\"id\":2}]";
        Assert.assertEquals("compared:" + inputJson + " with:" + expectedJson,inputJson,expectedJson);
    }

    /**
     * Tester om den kan parse en json string med 2 utgift objekter som objekter,
     * og se om deres strengverdi er lik tostringen til 2 vanlig instansierte utgiftobjekter.
     * @throws IOException om den ikke greier å lese fra strengen json
     */
    @Test
    public void testUtgiftListDeserialization() throws IOException {
        String json = "[{\"navn\":\"ost\",\"pris\":53.0,\"kategori\":\"mat\",\"id\":1}"
                + ",{\"navn\":\"bok\",\"pris\":1621.0,\"kategori\":\"skole\",\"id\":2}]";
        Object[] ul = objectMapper.readValue(json, UtgiftList.class).getUtgifter().toArray();
        Assert.assertEquals(ul[0].toString(), utgift1().toString(), ul[0].toString());
        Assert.assertEquals(ul[1].toString(), utgift2().toString(), ul[1].toString());
        Assert.assertEquals("Actual length is " + ul.length, 2, ul.length);

    }

    /**
     * Tester om den kan serialize kun ett objekt og sammenlikner med en allerede skrevet korrekt streng
     * @throws JsonProcessingException om den ikke greier å lese utgiften til en json streng
     */
    @Test //halvveis redundant pga testen over, men forblir atm:)
    public void testUtgiftSerialization() throws JsonProcessingException {
        String inputJson = objectMapper.writeValueAsString(new UtgiftList(utgift1()));
        String expectedJson = "[{\"navn\":\"ost\",\"pris\":53.0,\"kategori\":\"mat\",\"id\":1}]";
        Assert.assertEquals("compared:" + inputJson + " with:" + expectedJson,inputJson,expectedJson);
    }

    /**
     * tester om den kan produsere en utgiftliste med en objectArray
     * @throws IOException når den ikke kan lese fra strengen
     */
    @Test
    public void testUtgiftDeserializationObjectArray() throws IOException {
        String s1 = "[[\"ost\",\"53.0\",\"mat\",1]]";
        UtgiftList u = objectMapper.readValue(s1, UtgiftList.class);
        Assert.assertEquals(1,u.getUtgifter().size());
        Assert.assertEquals(utgift1().toString(),u.getUtgift(0).toString());
        String s2 = "[\"ost\",\"53.0\",\"mat\",1,\"returnererNull\"]";
        Utgift u2 = objectMapper.readValue(s2,Utgift.class);
        Assert.assertEquals(null, u2);
    }

}
