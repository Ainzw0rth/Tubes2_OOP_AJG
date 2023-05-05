package DataStore.Adapter.Utils;

import com.google.gson.*;
import java.lang.reflect.Type;
import Entity.*;

public class JsonMemberSerializer implements JsonSerializer<Member>{
    @Override
    public JsonElement serialize(Member src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject memberJson = new JsonObject();
        memberJson.addProperty("id", src.getId());
        memberJson.addProperty("name", src.getName());
        memberJson.addProperty("phoneNumber", src.getPhoneNumber());
        
        String status;

        if (src instanceof VIP) {
            status = "VIP";
        } else {
            status = src.getIsActive() ? "Active" : "Inactive"; 
        }

        memberJson.addProperty("status", status);
        memberJson.addProperty("point", src.getPoint());

        return memberJson;
    }
}  
