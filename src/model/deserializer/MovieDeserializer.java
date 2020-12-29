package model.deserializer;

import com.google.gson.*;
import model.Movie;

import java.lang.reflect.Type;

public class MovieDeserializer implements JsonDeserializer<Movie> {

    @Override
    public Movie deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
