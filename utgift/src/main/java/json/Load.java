package json;

import core.Utgift;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import com.google.gson.Gson;

public class Load {
    //retrieves stored utgift objects from a file and returns an arrayList
    public static Collection<Utgift> retrieve(File file){
        Collection<Utgift> utgifter = new ArrayList<Utgift>();

        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(file);
            utgifter = gson.fromJson(reader, ArrayList.class);
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
        objectify(utgifter);
        return(utgifter);
    }

    private static void objectify(Collection utgifterJsoned){
        Collection cleanedCollection = new ArrayList<>();
        for (Object u : utgifterJsoned){
            System.out.println(u);
        }
        //return cleanedCollection;
    }
}
