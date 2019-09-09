package json;

import core.Utgift;
import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

//allows saving and retrieving - will be superseded by json at a later point
public class Save {
    //writes all objects in an array list to a file
    public static void save(Collection objects, File file){
        try{
            PrintWriter writer = new PrintWriter(file);

        for(Object o : objects){
            writer.write(o.toString()+"\n");
        }

        writer.close();
        }catch(IOException e){

        }
    }


}
