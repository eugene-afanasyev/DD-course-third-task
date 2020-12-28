package model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Movie {
    private int id;

    private String url;

    private String type;

    private String title;
    private String title_alternative;

    private String tagline;

    private String description;
    private String year;

    private URL poster;
    private URL trailer;

    private String age;

    private ArrayList<String> actors;

    private ArrayList<String> Genres;

    private int rating_kinopoisk;

    private int rating_imdb;

    private ArrayList<URL> frames;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle_alternative() {
        return title_alternative;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public URL getPoster() {
        return poster;
    }

    public URL getTrailer() {
        return trailer;
    }

    public String getAge() {
        return age;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getGenres() {
        return Genres;
    }

    public int getRating_kinopoisk() {
        return rating_kinopoisk;
    }

    public int getRating_imdb() {
        return rating_imdb;
    }

    public ArrayList<URL> getFrames() {
        return frames;
    }
}
