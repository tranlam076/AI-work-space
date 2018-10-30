package gsonTest;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonConverter {
    
    private final Gson gson;
    
    public JsonConverter() {
        
        gson = new GsonBuilder().create();
    }

    public String convertToJson(List<City> cities) {
        
        JsonArray jarray = gson.toJsonTree(cities).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cities", jarray);

        return jsonObject.toString();
    }
}