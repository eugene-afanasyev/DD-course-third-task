package model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import javafx.util.Pair;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;

public class Movie {
    private int filmId;

    private String nameRu;
    private String nameEn;

    private int year;

    private String description;

    private long filmLength;

    private ArrayList<String> countries;

    private ArrayList<String> genres;

    private double rating;
    private int ratingVoteCount;

    private URL posterUrl;
    private URL posterUrlPreview;

    public int getFilmId() {
        return filmId;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public long getFilmLength() {
        return filmLength;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public double getRating() {
        return rating;
    }

    public int getRatingVoteCount() {
        return ratingVoteCount;
    }

    public URL getPosterUrl() {
        return posterUrl;
    }

    public URL getPosterUrlPreview() {
        return posterUrlPreview;
    }
}
