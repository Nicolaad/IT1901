package json;

import com.google.gson.Gson;
import core.Utgift;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

public class Load {
    //retrieves stored utgift objects from a json array in a json file.
    public static Collection<Utgift> retrieve(File file){
        Collection utgifter = new ArrayList<Utgift>();

        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(file);

            Utgift[] array = gson.fromJson(reader, Utgift[].class);
            reader.close();

            for (Utgift u : array){
                utgifter.add(u);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }

        return(utgifter);
    }

}
