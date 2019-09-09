package json;

import core.Utgift;
import java.io.File;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

//allows saving and retrieving - will be superseded by json at a later point
public class save {
    //writes all objects in an array list to a file
    public static void save(ArrayList objects, File file){
        try{
            PrintWriter writer = new PrintWriter(file);

        for(Object o : objects){
            writer.write(o.toString());
        }

        writer.close();
        }catch(IOException e){

        }
    }
    //retrieves stored utgift objects from a file and returns an arrayList
    public static ArrayList retrieve(File file){
        ArrayList<Utgift> retrievedList= new ArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = "temp";
            while ((currentLine != null)) {
                currentLine = reader.readLine();
                String[] s = currentLine.split(";");
                retrievedList.add(new Utgift(s[0], s[1], s[2]));
            }
            reader.close();
        }catch(IOException e){
        }
        return retrievedList;
    }

}
