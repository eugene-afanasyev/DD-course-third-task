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

        if(jsonObject.get("nameRu") != null)
            movie.setNameRu(jsonObject.get("nameRu").getAsString());

        if(jsonObject.get("nameEn") != null)
            movie.setNameEn(jsonObject.get("nameEn").getAsString());

        try {
            if(jsonObject.get("webUrl") != null)
                movie.setWebUrl(new URL(jsonObject.get("webUrl").getAsString()));

            if(jsonObject.get("posterUrl") != null)
                movie.setPosterUrl(new URL(jsonObject.get("posterUrl").getAsString()));

            if(jsonObject.get("posterUrlPreview") != null)
                movie.setPosterUrl(new URL(jsonObject.get("posterUrlPreview").getAsString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(jsonObject.get("year") != null)
            movie.setYear(jsonObject.get("year").getAsString());

        if(jsonObject.get("filmLength") != null)
            movie.setFilmLength(jsonObject.get("filmLength").getAsString());

        if(jsonObject.get("slogan") != null)
            movie.setSlogan(jsonObject.get("slogan").getAsString());

        if(jsonObject.get("description") != null)
            movie.setDescription(jsonObject.get("description").getAsString());

        if(jsonObject.get("type") != null)
            movie.setType(jsonObject.get("type").getAsString());

        if(jsonObject.get("ratingAgeLimits") != null)
            movie.setRatingAgeLimits(jsonObject.get("ratingAgeLimits").getAsInt());

        if(jsonObject.get("premiereRu") != null)
            movie.setPremiereRu(jsonObject.get("premiereRu").getAsString());

        if(jsonObject.get("premiereWorld") != null)
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
