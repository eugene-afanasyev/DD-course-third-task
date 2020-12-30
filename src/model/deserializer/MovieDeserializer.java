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

        Movie movie = new Movie();
        movie.setFilmId(jsonObject.get("filmId").getAsInt());
        movie.setNameRu(jsonObject.get("nameRu").getAsString());
        movie.setNameEn(jsonObject.get("nameEn").getAsString());
        movie.setYear(jsonObject.get("year").getAsString());
        movie.setDescription(jsonObject.get("description").getAsString());

        JsonArray countries = jsonObject.getAsJsonArray("countries");
        ArrayList<String> countriesList = new ArrayList<>();
        for (JsonElement country : countries) {
            if (country != null && country.isJsonPrimitive())
                countriesList.add(country.getAsString());
        }
        movie.setCountries(countriesList);

        JsonArray genres = jsonObject.getAsJsonArray("genres");
        ArrayList<String> genresList = new ArrayList<>();
        for (JsonElement genre : genres) {
            if (genre != null && genre.isJsonPrimitive())
                genresList.add(genre.getAsString());
        }
        movie.setGenres(genresList);

        if(jsonObject.get("rating") != null)
            movie.setRating(jsonObject.get("rating").getAsString());

        if (jsonObject.get("ratingVoteCount") != null)
            movie.setRatingVoteCount(jsonObject.get("ratingVoteCount").getAsInt());

        try {
            movie.setPosterUrl(new URL(jsonObject.get("posterUrl").getAsString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            movie.setPosterUrlPreview(new URL(jsonObject.get("posterUrlPreview").getAsString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return movie;
    }
}
