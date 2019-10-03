package json;

import core.Utgift;
import java.io.*;
import java.util.Collection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//allows saving to json files.
public class Save {

    //writes a utgift collection as a json object to a file.
    public static void save(Collection<Utgift> utgifter, File file){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            FileWriter writer = new FileWriter(file);
            gson.toJson(utgifter, writer);
            writer.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}