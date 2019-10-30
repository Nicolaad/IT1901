package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Utgift;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * Tillater lagring til fil
 */
public class Save {

    /**
     * skriver collection av utgifter til filen ved hjelp av gson.
     * @param utgifter tar inn liste som skal skrives til fil
     * @param file tar inn en fil som det skal skrives til.
     */
    public static void save(Collection<Utgift> utgifter, File file) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            System.out.println("Attempting save to file in: " + file.getCanonicalPath());
            FileWriter writer = new FileWriter(file, Charset.defaultCharset());
            gson.toJson(utgifter, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
