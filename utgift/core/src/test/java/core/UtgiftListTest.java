package core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtgiftListTest {
    private UtgiftList utgiftList;
    private List<Utgift> utgifter = new ArrayList<>();

    @Before
    public void setUp(){
        Utgift utgift1 = new Utgift("Fisk","200","Mat");
        Utgift utgift2 = new Utgift("Nugatti","20","Mat");
        Utgift utgift3 = new Utgift("Penn","10","Skole");
        Utgift utgift4 = new Utgift("Medisin","50","Helse");
        utgifter.addAll(Arrays.asList(utgift1,utgift2,utgift3,utgift4));
        utgiftList = new UtgiftList(utgifter);
    }

    @Test
    public void utgiftListConstructorTest(){
        UtgiftList ul = new UtgiftList();
        assertEquals(utgifter,utgiftList.toList());
    }

    @Test
    public void utgiftListAddTest(){
        Utgift utgift5 = new Utgift("Paracet","100","Helse");
        UtgiftList.add(utgift5);
        assertTrue(utgiftList.toList().contains(utgift5));
        utgiftList.removeUtgift(utgift5);
        assertFalse(utgiftList.toList().contains(utgift5));
        utgiftList.addUtgift(utgift5);
        assertTrue(utgiftList.toList().contains(utgift5));
    }

    @Test
    public void testClear(){
        System.out.println(utgiftList.getUtgifter());
        UtgiftList.clear();
        assertTrue(utgiftList.getUtgifter().isEmpty());
        assertTrue(utgiftList.getPieChart().isEmpty());

    }

}
