package json;


import com.fasterxml.jackson.core.JsonProcessingException;
import core.Utgift;
import core.UtgiftList;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UtgiftListSerializingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();{
        objectMapper.registerModule(new UtgiftListModule());
    }
    //hjelpemetoder
    private Utgift utgift1(){
        return new Utgift("ost", "53", "mat");
    }
    private Utgift utgift2(){
        return new Utgift("bok", "1621", "skole");
    }

    /**
     * Tester at objektmapperen gir riktig output når den konverterer flere objekter til en jsonstreng
     * @throws JsonProcessingException
     */
    @Test
    public void testUtgiftListSerialization() throws JsonProcessingException {

        String inputJson = objectMapper.writeValueAsString(new UtgiftList(utgift1(),utgift2()));
        String expectedJson = "[{\"navn\":\"ost\",\"pris\":53.0,\"kategori\":\"mat\"},{\"navn\":\"bok\",\"pris\":1621.0,\"kategori\":\"skole\"}]";
        Assert.assertEquals("compared:"+ inputJson + " with:" + expectedJson,inputJson,expectedJson);
        }

    /**
     * Tester om den kan parse en json string med 2 utgift objekter som objekter, og se om deres strengverdi er lik tostringen til 2 vanlig instansierte utgiftobjekter.
     * @throws Exception
     */
    @Test
    public void testUtgiftListDeserialization() throws IOException {
        String json = "[{\"navn\":\"ost\",\"pris\":\"53\",\"kategori\":\"mat\"},{\"navn\":\"bok\",\"pris\":\"1621\",\"kategori\":\"skole\"}]";
        Object[] ul = objectMapper.readValue(json, UtgiftList.class).getUtgifter().toArray();
        Assert.assertEquals(ul[0].toString(), utgift1().toString(), ul[0].toString());
        Assert.assertEquals(ul[1].toString(), utgift2().toString(), ul[1].toString());
        Assert.assertEquals("Actuall length is " + ul.length, 2, ul.length);

    }

    /**
     * Tester om den kan serialize kun ett objekt og sammenlikner med en allerede skrevet korrekt streng
     * @throws JsonProcessingException
     */
    @Test //halvveis redundant pga testen over, men forblir atm:)
    public void testUtgiftSerialization() throws JsonProcessingException{
        String inputJson = objectMapper.writeValueAsString(new UtgiftList(utgift1()));
        String expectedJson = "[{\"navn\":\"ost\",\"pris\":53.0,\"kategori\":\"mat\",\"prisString\":\"53\"}]";
        Assert.assertEquals("compared:"+ inputJson + " with:" + expectedJson,inputJson,expectedJson);
    }
    /*q
    @Test
    public void testUtgiftDeserialization() throws IOException {
        String json = "[{\"navn\":\"ost\",\"pris\":\"53\",\"kategori\":\"mat\"}]";
        Utgift u = objectMapper.readValue(json, Utgift.class);
        Assert.assertEquals("compares:"+ u+ " with:"+ utgift1(),utgift1().toString(), u.toString());

    }
     */
}
