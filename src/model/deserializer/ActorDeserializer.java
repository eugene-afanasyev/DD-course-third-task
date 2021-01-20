package model.deserializer;

import com.google.gson.*;
import model.Actor;
import model.Movie;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ActorDeserializer implements JsonDeserializer<Actor> {

    @Override
    public Actor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        Actor actor = new Actor();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        actor.setId(jsonObject.get("personId").getAsInt());
        actor.setNameRu(jsonObject.get("nameRu").getAsString());
        actor.setNameEn(jsonObject.get("nameEn").getAsString());
        actor.setSex(jsonObject.get("sex").getAsString());
        actor.setGrowth(jsonObject.get("growth").getAsInt());
        actor.setBirthday(jsonObject.get("birthday").getAsString());
        actor.setBirthplace(jsonObject.get("birthplace").getAsString());
        actor.setAge(jsonObject.get("age").getAsInt());

        try {
            actor.setPosterURL(new URL(jsonObject.get("posterUrl").getAsString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(!(jsonObject.get("description") instanceof JsonNull))
            actor.setDescription(jsonObject.get("description").getAsString());
        else
            actor.setDescription("");

        ArrayList<Integer> films = new ArrayList<>();
        JsonArray filmsJsonArray = jsonObject.get("films").getAsJsonArray();
        for (JsonElement film : filmsJsonArray) {
            JsonObject tmp = film.getAsJsonObject();
            films.add(tmp.get("filmId").getAsInt());
        }
        actor.setFilmsId(films);

        return actor;
    }
}
