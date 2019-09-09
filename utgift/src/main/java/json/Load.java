package json;

import core.Utgift;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Load {
    //retrieves stored utgift objects from a file and returns an arrayList
    public static Collection<String> retrieve(File file){
        Collection<String> retrievedList = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String temp =scanner.nextLine();
                retrievedList.add(temp);
            }
        }
        //bedre exception -- todo
        catch(Exception e){

        }
        return retrievedList;
    }
}
