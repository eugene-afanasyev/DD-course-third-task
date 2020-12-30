package model.deserializer;

import com.google.gson.*;
import model.Movie;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieDeserializer implements JsonDeserializer<Movie> {

    @Override
    public Movie deserialize(JsonElement src, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = src.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject("data");

        Movie movie = new Movie();

        movie.setFilmId(jsonObject.get("filmId").getAsInt());

        if(!(jsonObject.get("nameRu") instanceof JsonNull))
            movie.setNameRu(jsonObject.get("nameRu").getAsString());

        if(!(jsonObject.get("nameEn") instanceof JsonNull))
            movie.setNameEn(jsonObject.get("nameEn").getAsString());

        try {
            if(!(jsonObject.get("webUrl") instanceof JsonNull))
                movie.setWebUrl(new URL(jsonObject.get("webUrl").getAsString()));

            if(!(jsonObject.get("posterUrl") instanceof JsonNull))
                movie.setPosterUrl(new URL(jsonObject.get("posterUrl").getAsString()));

            if(!(jsonObject.get("posterUrlPreview") instanceof JsonNull))
                movie.setPosterUrl(new URL(jsonObject.get("posterUrlPreview").getAsString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(!(jsonObject.get("year") instanceof JsonNull))
            movie.setYear(jsonObject.get("year").getAsString());

        if(!(jsonObject.get("filmLength") instanceof JsonNull))
                movie.setFilmLength(jsonObject.get("filmLength").getAsString());

        if(!(jsonObject.get("slogan") instanceof JsonNull))
            movie.setSlogan(jsonObject.get("slogan").getAsString());

        if(!(jsonObject.get("description") instanceof JsonNull))
            movie.setDescription(jsonObject.get("description").getAsString());

        if(!(jsonObject.get("type") instanceof JsonNull))
            movie.setType(jsonObject.get("type").getAsString());

        if(!(jsonObject.get("ratingAgeLimits") instanceof JsonNull))
            movie.setRatingAgeLimits(jsonObject.get("ratingAgeLimits").getAsInt());

        if(!(jsonObject.get("premiereRu") instanceof JsonNull))
            movie.setPremiereRu(jsonObject.get("premiereRu").getAsString());

        if(!(jsonObject.get("premiereWorld") instanceof JsonNull))
            movie.setPremiereWorld(jsonObject.get("premiereWorld").getAsString());

        JsonArray countries = jsonObject.getAsJsonArray("countries");
        ArrayList<String> countriesList = new ArrayList<>();
        for (JsonElement country : countries) {
            JsonObject jObj = country.getAsJsonObject();
            countriesList.add(jObj.get("country").getAsString());
        }
        movie.setCountries(countriesList);

        JsonArray genres = jsonObject.getAsJsonArray("genres");
        ArrayList<String> genresList = new ArrayList<>();
        for (JsonElement genre : genres) {
            JsonObject jObj = genre.getAsJsonObject();
            genresList.add(jObj.get("genre").getAsString());
        }
        movie.setGenres(genresList);

        return movie;
    }
}
