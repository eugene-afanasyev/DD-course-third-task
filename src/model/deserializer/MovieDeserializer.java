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
        movie.setYear(jsonObject.get("year").getAsInt());
        movie.setDescription(jsonObject.get("description").getAsString());

        JsonArray countries = jsonObject.get("countries").getAsJsonArray();
        ArrayList<String> countriesList = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            countriesList.add(countries.get(0).getAsString());
        }
        movie.setCountries(countriesList);

        JsonArray genres = jsonObject.get("genres").getAsJsonArray();
        ArrayList<String> genresList = new ArrayList<>();
        for (int i = 0; i < genres.size(); i++) {
            countriesList.add(genres.get(0).getAsString());
        }
        movie.setGenres(genresList);

        movie.setRating(jsonObject.get("rating").getAsDouble());

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
