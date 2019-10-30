package core;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UtgiftTest {

    Utgift utgift1;
    //Utgift utgift2; disabled as it is not used

    /**
     * Initialiseer utgiftene
     */
    @Before
    public void init() {
        utgift1 = new Utgift("Mat","200.0","matvarer");
        //utgift2 = new Utgift("Bok","300.0","skole");  disabled as it is not used
    }

    @Test
    public void checkPrice() {
        int low = 0;
        assertTrue("Error, price is negative or zero", low <= utgift1.getPris());
        System.out.println("Test passed, " + utgift1.getPris() + " is a positive number");
    }

    @Test
    public void checkPriceLength() {
        assertTrue("Error, no price was entered.", utgift1.prisString().length() > 0);
        System.out.println("Price length test passed");
    }
    /*
    @Test
    public void checktoStringMethod() {
        String u1 = utgift1.toString();
        Assert.assertEquals("Mat" + ";" + "200.0" + ";" + "matvarer", u1);
    } */
    /*
    @Test
    public void getNavn() {
        assertEquals("Mat",utgift1.getNavn());
        assertEquals("Bok",utgift2.getNavn());
    }

    @Test
    public void getKategori() {
        assertEquals("matvarer",utgift1.getKategori());
        assertEquals("skole",utgift2.getKategori());
    }

    @Test
    public void getPrisString() {
        assertEquals("200.0",utgift1.prisString());
    }

    @Test
    public void getPris() {
        assertEquals(200.0,utgift1.getPris(),0.1);
    }
/*
    @Test
    public void testToString() {
        assertEquals(utgift1.toString(),"Mat;200.0;matvarer");
    } */

    @Test
    public void testcreateUtgiftObject() {
        Utgift temp = Utgift.createUtgiftObject("Mat;200.0;matvarer");
        assertEquals(utgift1.getNavn(), temp.getNavn());
        assertEquals(utgift1.getPris(), temp.getPris(),0.1);
        assertEquals(utgift1.getKategori(), temp.getKategori());
    }

}