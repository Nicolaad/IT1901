package json;

import core.Utgift;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//allows saving and retrieving - will be superseded by json at a later point
public class Save {

    //writes all objects in an array list to a file
    public static void save(Collection<String> utgifterString, File file){
        Collection<Utgift> temp = new ArrayList<>();
        for(String s : utgifterString ){
            temp.add(Utgift.getUtgift(s));
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            FileWriter writer = new FileWriter(file);
            gson.toJson(temp, writer);
            writer.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
