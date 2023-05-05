package DataStore.Adapter.Utils;

import com.google.gson.*;
import java.lang.reflect.Type;
import Entity.*;

class JsonMemberDeserializer implements JsonDeserializer<Member> {
    @Override
    public Member deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        Integer id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String phoneNumber = jsonObject.get("phoneNumber").getAsString();
        Integer point = jsonObject.get("point").getAsInt();
        String status = jsonObject.get("status").getAsString();

        Member member;
        if (status.equals("VIP")) {
            member = new VIP(id, name, phoneNumber, true, point);
        } else {
            member = new Member(id, name, phoneNumber, status.equals("Active"), point);
        }

        return member;
    }
}