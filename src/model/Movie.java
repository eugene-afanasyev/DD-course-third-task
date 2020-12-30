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

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setWebUrl(URL webUrl) {
        this.webUrl = webUrl;
    }

    public void setPosterUrl(URL posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setPosterUrlPreview(URL posterUrlPreview) {
        this.posterUrlPreview = posterUrlPreview;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRatingAgeLimits(int ratingAgeLimits) {
        this.ratingAgeLimits = ratingAgeLimits;
    }

    public void setPremiereRu(String premiereRu) {
        this.premiereRu = premiereRu;
    }

    public void setPremiereWorld(String premiereWorld) {
        this.premiereWorld = premiereWorld;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
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

    public URL getWebUrl() {
        return webUrl;
    }

    public URL getPosterUrl() {
        return posterUrl;
    }

    public URL getPosterUrlPreview() {
        return posterUrlPreview;
    }

    public String getYear() {
        return year;
    }

    public String getFilmLength() {
        return filmLength;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getRatingAgeLimits() {
        return ratingAgeLimits;
    }

    public String getPremiereRu() {
        return premiereRu;
    }

    public String getPremiereWorld() {
        return premiereWorld;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }
}
