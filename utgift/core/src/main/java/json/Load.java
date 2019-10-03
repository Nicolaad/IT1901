package json;

import core.Utgift;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import com.google.gson.Gson;

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
