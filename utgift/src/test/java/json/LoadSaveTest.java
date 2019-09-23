package json;

import core.Utgift;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertEquals;

public class LoadSaveTest {
    private Collection<Utgift> utgifter = new ArrayList<>();
    private File file;

    //initialises some base cases
    @Before
    public void init() {
        utgifter.add(new Utgift("Ost", "503", "Mat"));
        utgifter.add(new Utgift("Flytogbusstogfly", "200000", "Skole"));
        file = new File("test.json");

    /*
    @Before
    public void init(){
        utgifter.add(new Utgift("hei","hei","heilo").toString());
        utgifter.add(new Utgift("hei","hei","hei").toString());
        file = new File("test.txt");

    }
    */
        @Test
        public void saveTest() {
            //test that it can store and retrieve proper objects
            json.Save.save(utgifter,file);
            Collection<Utgift> retrievedUtgifter = json.Load.retrieve(file);
            assertTrue(trueutgifter.equals(retrievedUtgifter))){
                assertTrue();
            }
        }


    }
}
