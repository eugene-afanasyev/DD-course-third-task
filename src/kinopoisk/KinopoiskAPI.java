package kinopoisk;

import com.google.gson.*;
import model.Actor;
import model.Movie;
import model.deserializer.ActorDeserializer;
import model.deserializer.MovieDeserializer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KinopoiskAPI {
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Movie.class, new MovieDeserializer())
            .registerTypeAdapter(Actor.class, new ActorDeserializer())
            .create();

    static String token = "9cc79179-7ed2-4297-8973-054018bcc494";
    static String host_v2dot1 = "https://kinopoiskapiunofficial.tech/api/v2.1";
    static String host_v1 = "https://kinopoiskapiunofficial.tech/api/v1";

    public List<Movie> getMoviesByKeyword(String keyword) {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        List<Movie> movies = new ArrayList<>();

        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        String src = host_v2dot1 + "/films/search-by-keyword?keyword=" + keyword + "&page=1";

        final HttpUriRequest httpGet = buildHttpUriRequest(src);

        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity = response1.getEntity();
            JsonElement jsonElement = JsonParser.parseString(EntityUtils.toString(entity));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray jsonArray = jsonObject.get("films").getAsJsonArray();

            for (JsonElement filmJson : jsonArray) {
                JsonObject object = filmJson.getAsJsonObject();
                movies.add(getMovieById(object.get("filmId").getAsInt()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public Movie getMovieById(int id) {
        Movie movie = null;

        final CloseableHttpClient httpclient = HttpClients.createDefault();

        String src = host_v2dot1 + "/films/" + id;

        final HttpUriRequest httpGet = buildHttpUriRequest(src);

        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity = response1.getEntity();
            JsonElement jsonElement = JsonParser.parseString(EntityUtils.toString(entity));
            movie = gson.fromJson(jsonElement, Movie.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

    public List<Actor> getActorsByFilmId(int id) {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        List<Actor> actors = new ArrayList<>();

        String src = host_v1 + "/staff?filmId=" + id;

        final HttpUriRequest httpGet = buildHttpUriRequest(src);

        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity = response1.getEntity();
            JsonElement jsonElement = JsonParser.parseString(EntityUtils.toString(entity));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement actorJson : jsonArray) {
                JsonObject jsonObject = actorJson.getAsJsonObject();
                JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("professionKey");
                if (jsonPrimitive.getAsString().equals("ACTOR")) {
                    Actor actor = gson.fromJson(actorJson, Actor.class);
                    actors.add(actor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actors;
    }

    private HttpUriRequest buildHttpUriRequest(String src) {
        final HttpUriRequest httpGet = new HttpGet(src);
        httpGet.addHeader(new BasicHeader("accept", "application/json"));
        httpGet.addHeader(new BasicHeader("X-API-KEY", token));
        return httpGet;
    }
}
