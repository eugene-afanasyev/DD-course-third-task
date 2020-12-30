package model;

import java.net.URL;

public class Actor {
    private int id;

    private String nameRu;
    private String nameEn;

    private URL posterURL;

    public int getId() {
        return id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public URL getPosterURL() {
        return posterURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setPosterURL(URL posterURL) {
        this.posterURL = posterURL;
    }
}
