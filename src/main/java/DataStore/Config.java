package DataStore;

import java.util.*;
import java.io.*;

import lombok.*;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.*;

import DataStore.Enums.FileStoreExt;

@Getter
@Setter
public class Config {
    private String dirPath;
    private FileStoreExt ext;    

    public Config() {
        try {
            String jsonStr = "";
            BufferedReader reader = new BufferedReader(new FileReader("src/main/config/config.json"));
            String line = reader.readLine();
            while (line != null) {
                jsonStr += line;
                line = reader.readLine();
            }
            reader.close();
    
            JSONObject jsonObj = new JSONObject(jsonStr);
            String extString = jsonObj.getString("ext");
            FileStoreExt ext = FileStoreExt.JSON;
            switch (extString) {
                case "JSON":
                    ext = FileStoreExt.JSON;
                    break;
                case "XML":
                    ext = FileStoreExt.XML;
                    break;
                    case "OBJ":
                    ext = FileStoreExt.OBJ;
                    break;
                default:
                    ext = FileStoreExt.JSON;
                    break;
            }
            
            setExt(ext);
            setDirPath(jsonObj.getString("dirPath"));
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeConfig(String dirPath, FileStoreExt ext) {
        this.setExt(ext);
        try {
            // create a Gson object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // create a map representing the new JSON structure
            Map<String, String> myMap = new HashMap<>();
            myMap.put("dirPath", dirPath);
            myMap.put("ext", getExtAsString());
            

            // serialize the map into JSON format
            String jsonString = gson.toJson(myMap);
            
            // write the JSON string to a file
            FileWriter writer = new FileWriter("src/main/config/config.json");
            writer.write(jsonString);
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getExtAsString() {
        String extString = "JSON";
        switch (this.ext) {
            case JSON:
                extString = "JSON";
                break;
            case XML:
                extString = "XML";
                break;
            case OBJ:
                extString = "OBJ";
                break;
            default:
                extString = "JSON";
                break;
        }
        return extString;
    }
}
