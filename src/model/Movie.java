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

    private URL webUrl;
    private URL posterUrl;
    private URL posterUrlPreview;

    private String year;

    private String filmLength;

    private String slogan;
    private String description;

    private String type;

    private int ratingAgeLimits;

    private String premiereRu;
    private String premiereWorld;

    private ArrayList<String> countries;

    private ArrayList<String> genres;

    public void setWebUrl(URL webUrl) {
        this.webUrl = webUrl;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setPosterUrl(URL posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setPosterUrlPreview(URL posterUrlPreview) {
        this.posterUrlPreview = posterUrlPreview;
    }

    public URL getWebUrl() {
        return webUrl;
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

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getFilmLength() {
        return filmLength;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public URL getPosterUrl() {
        return posterUrl;
    }

    public URL getPosterUrlPreview() {
        return posterUrlPreview;
    }
}
