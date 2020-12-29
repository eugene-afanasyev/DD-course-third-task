package kinopoisk;

import com.google.gson.*;
import model.Movie;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class KinopoiskAPI {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Movie.class, new MovieDeserializer())
            .create();

    static String token = "9cc79179-7ed2-4297-8973-054018bcc494";
    static String host = "https://kinopoiskapiunofficial.tech/api/v2.1";

    public Movie getMovieByKeyword(String keyword) {
        String rawJson = null;
        final CloseableHttpClient httpclient = HttpClients.createDefault();

        keyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        String src = host + "/films/search-by-keyword?keyword=" + keyword + "&page=1";

        final HttpUriRequest httpGet = new HttpGet(src);
        httpGet.addHeader(new BasicHeader("accept", "application/json"));
        httpGet.addHeader(new BasicHeader("X-API-KEY", token));

        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity = response1.getEntity();
            rawJson = gson.toJson(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(JsonParser.parseString(rawJson), Movie.class);
    }
}
