package core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtgiftTest {
    Utgift utgift1;
    Utgift utgift2;
    @Before
    public void init(){
        utgift1 = new Utgift("Mat","200.0","matvarer");
        utgift2 = new Utgift("Bok","300.0","skole");
    }
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
        assertEquals("200.0",utgift1.getPrisString());
    }

    @Test
    public void getPris() {
        assertEquals(200.0,utgift1.getPris(),0.1);
    }

    @Test
    public void testToString() {
        assertEquals(utgift1.toString(),"Mat;200.0;matvarer");
    }
}