package model.deserializer;

import com.google.gson.*;
import model.Actor;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

public class ActorDeserializer implements JsonDeserializer<Actor> {

    @Override
    public Actor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        Actor actor = new Actor();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        actor.setId(jsonObject.get("staffId").getAsInt());
        actor.setNameRu(jsonObject.get("nameRu").getAsString());
        actor.setNameEn(jsonObject.get("nameEn").getAsString());
        try {
            actor.setPosterURL(new URL(jsonObject.get("posterUrl").getAsString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        actor.setDescription(jsonObject.get("description").getAsString());

        return actor;
    }
}
