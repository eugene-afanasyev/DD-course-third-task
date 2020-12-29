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

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilmLength(long filmLength) {
        this.filmLength = filmLength;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRatingVoteCount(int ratingVoteCount) {
        this.ratingVoteCount = ratingVoteCount;
    }

    public void setPosterUrl(URL posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setPosterUrlPreview(URL posterUrlPreview) {
        this.posterUrlPreview = posterUrlPreview;
    }

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
