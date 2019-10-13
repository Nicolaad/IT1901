package json;


import com.fasterxml.jackson.core.JsonProcessingException;
import core.Utgift;
import core.UtgiftList;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class UtgiftListSerializingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();{
        objectMapper.registerModule(new UtgiftListModule());
    }

    private Utgift utgift1(){
        return new Utgift("ost", "53", "mat");
        }

    private Utgift utgift2(){
        return new Utgift("bok", "1621", "skole");
    }
    private void assertEqualsNoWhiteSpace(String input, String expected){
        Assert.assertEquals(input.replaceAll("\\s+", ""), expected);
    }
    /*
    @Test
    public void testUtgiftListSerialization() throws JsonProcessingException {
        Collection<Utgift> initiadedUtgifter = new ArrayList<>();
        initiadedUtgifter.add(utgift1());
        initiadedUtgifter.add(utgift2());
        String inputJson = objectMapper.writeValueAsString(new UtgiftList(initiadedUtgifter));
        String expectedJson = "[{\"navn\":\"ost\",\"pris\":\"53\",\"kategori\":\"Mat\"}," + "{\"navn\":\"Akademikabok\",\"pris\":\"1621\",\"kategori\":\"Skole\"}]";
        Assert.assertEquals(inputJson,expectedJson);
        }
    /*
    @Test
    public void testUtgiftListDeserialization() throws Exception {
        String json = "[{\"navn\":\"ost\",\"pris\":\"53\",\"kategori\":\"mat\"}," + "{\"navn\":\"bok\",\"pris\":\"1621\",\"kategori\":\"skole\"}]";
        Collection ul = objectMapper.readValue(json, UtgiftList.class).getUtgifter();
        //Assert.assertEquals(utgift1().toString(), utgiftList.toList().get(0).toString());
        //Assert.assertEquals(utgift2(), utgiftList.toList().get(1).toString());
        Assert.assertEquals(2, ul.size());

    }
    /*
     */
}
