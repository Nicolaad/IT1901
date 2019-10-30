package json;

import static org.junit.Assert.assertEquals;

import core.Utgift;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;

public class LoadSaveTest {
    private Collection<Utgift> utgifter = new ArrayList<>();
    private File file;

    /**
     *Initialiserer utgifter som legges til i utgifter collectionen,
     * legger også til path for jsonen
     */
    @Before
    public void init() {
        utgifter.add(new Utgift("Ost", "503", "Mat"));
        utgifter.add(new Utgift("Flytogbusstogfly", "200000", "Skole"));
        file = new File("test.json");
    }

    @Test
    public void saveAndLoadIsEqual() {
        //test that it can store and retrieve proper objects
        json.Save.save(utgifter,file);
        Collection<Utgift> retrievedUtgifter = json.Load.retrieve(file);
        assertEquals(utgifter.toString(), retrievedUtgifter.toString());
    }

    //sjekker om koden greier å handle at feil fil er spesifisert

    /* Kommentert ut 2 siste tester pga markert som dodgy
    @Test()
    public void nonexistingFileLoadHandled() {
        Collection<Utgift> retrievedUtgifter = json.Load.retrieve(new File("nonexistingFile.onenote"));
        assert (true);
    }

    //sjekker om koden greier å handle at feil fil er spesifisert
    @Test()
    public void nonexistingFileSaveHandeled(){
        Collection<Utgift> retrievedUtgifter = json.Load.retrieve(new File("nonexistingFile2.onenote"));
        assert(true);

    } */

}

