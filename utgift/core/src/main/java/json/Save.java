package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Utgift;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

//allows saving to json files.
public class Save {

    /**
     * skriver collection av utgifter til filen ved hjelp av gson.
     * @param utgifter tar inn liste som skal skrives til fil
     * @param file tar inn en fil som det skal skrives til.
     */
    public static void save(Collection<Utgift> utgifter, File file) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(utgifter, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
