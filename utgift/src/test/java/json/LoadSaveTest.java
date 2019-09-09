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
    private Collection<String> utgifter = new ArrayList<>();
    private File file;
    @Before
    public void init(){
        utgifter.add(new Utgift("hei","hei","heilo").toString());
        utgifter.add(new Utgift("hei","hei","hei").toString());
        file = new File("test.txt");

    }

    @Test
    public void saveTest(){
        Save.save(utgifter,file);
        List<String> temp = (List)Load.retrieve(file);
        List<String> k = (List) utgifter;
            for(int i =0; i<k.size();i++){
                if(!temp.get(i).equals(k.get(i)))
                    fail("feil");
            }
    }

}
