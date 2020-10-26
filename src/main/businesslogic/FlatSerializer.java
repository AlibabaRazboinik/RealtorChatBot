package main.businesslogic;


import com.google.gson.*;

import java.lang.reflect.Type;

public class FlatSerializer implements JsonSerializer<Flat>, JsonDeserializer<Flat> {

    @Override
    public Flat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var jsonObject = jsonElement.getAsJsonObject();

        return new Flat(
                jsonObject.has("price") ? jsonObject.get("price").getAsLong() : null,
                jsonObject.has("square") ? jsonObject.get("square").getAsDouble() : null,
                jsonObject.has("city") ? jsonObject.get("city").getAsString() : null,
                jsonObject.has("area") ? jsonObject.get("area").getAsString() : null,
                jsonObject.has("type") ? jsonObject.get("type").getAsString() : null,
                jsonObject.has("address") ? jsonObject.get("address").getAsString() : null,
                jsonObject.has("rooms_count") ? jsonObject.get("rooms_count").getAsInt() : null
        );
    }

    @Override
    public JsonElement serialize(Flat flat, Type type, JsonSerializationContext jsonSerializationContext) {

        var jsonObject = new JsonObject();
        jsonObject.addProperty("price", flat.getPrice());
        jsonObject.addProperty("square", flat.getSquare());
        jsonObject.addProperty("city", flat.getCity());
        jsonObject.addProperty("area", flat.getArea());
        jsonObject.addProperty("type", flat.getType());
        jsonObject.addProperty("address", flat.getAddress());
        jsonObject.addProperty("rooms_count", flat.getRoomsCount());
        return jsonObject;


    }
}

