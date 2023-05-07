import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import Entity.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CurrencyIO {
    private Gson gson;

    public CurrencyIO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public ArrayList<Currency> read(String filepath) throws Exception{
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                return new ArrayList<Currency>();
            }

            FileReader reader = new FileReader(filepath);
            ArrayList<Currency> objects = this.gson.fromJson(reader, new TypeToken<ArrayList<Currency>>(){}.getType());
            reader.close();
            return objects == null ? new ArrayList<Currency>() : objects;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void write(ArrayList<Currency> obj, String filepath) throws Exception{
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(filepath);
            String json = this.gson.toJson(obj);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void write(Currency obj, String filepath) throws Exception{
        try {
            FileWriter writer = new FileWriter(filepath);
            String json = this.gson.toJson(obj);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
