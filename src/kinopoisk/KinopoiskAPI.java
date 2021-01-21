package kinopoisk;

import com.google.gson.*;
import javafx.scene.image.Image;
import model.Actor;
import model.Movie;
import model.Staff;
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
import java.net.URL;
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

    public ArrayList<Movie> getMoviesByKeyword(String keyword) {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        ArrayList<Movie> movies = new ArrayList<>();

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
        Movie movie = new Movie();

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

    public ArrayList<Staff> getStaffByFilmId(int id) {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        ArrayList<Staff> actors = new ArrayList<>();

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
                    Staff actor = gson.fromJson(actorJson, Staff.class);
                    actors.add(actor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actors;
    }

    public Actor getActorById(int id) {
        Actor actor = new Actor();

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        String src = host_v1 + "/staff/" + id;
        final HttpUriRequest httpGet = buildHttpUriRequest(src);
        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity = response1.getEntity();
            JsonElement jsonElement = JsonParser.parseString(EntityUtils.toString(entity));
            actor = gson.fromJson(jsonElement, Actor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actor;
    }

    public ArrayList<Image> getFramesById(int id) {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        ArrayList<Image> images = new ArrayList<>();

        String src = host_v2dot1 + "/films/" + id + "/frames";

        final HttpUriRequest httpGet = buildHttpUriRequest(src);

        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity = response1.getEntity();
            JsonElement jsonElement = JsonParser.parseString(EntityUtils.toString(entity));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray jsonArray = jsonObject.get("frames").getAsJsonArray();

            int counter = 0;
            for (JsonElement frameJson : jsonArray) {
                if (counter == 5)
                    break;

                JsonObject object = frameJson.getAsJsonObject();
                URL url = new URL(object.get("image").getAsString());
                Image img = new Image(url.toExternalForm());
                images.add(img);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return images;
    }

    private HttpUriRequest buildHttpUriRequest(String src) {
        final HttpUriRequest httpGet = new HttpGet(src);
        httpGet.addHeader(new BasicHeader("accept", "application/json"));
        httpGet.addHeader(new BasicHeader("X-API-KEY", token));
        return httpGet;
    }
}
