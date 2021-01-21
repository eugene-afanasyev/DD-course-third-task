package model;

import java.net.URL;
import java.util.ArrayList;

public class Actor {
    private int id;

    private String nameRu;
    private String nameEn;
    private String description;

    private String sex;

    private int growth;

    private String birthday;
    private String birthplace;

    private String profession;

    private int age;

    private ArrayList<FilmIdName> filmsId;

    private URL posterURL;

    public String getProfession() {
        return profession;
    }

    public String getDescription() {
        return description;
    }

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

    public String getSex() {
        return sex;
    }

    public int getGrowth() {
        return growth;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public ArrayList<FilmIdName> getFilmsId() {
        return filmsId;
    }

    public int getAge() {
        return age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setFilmsId(ArrayList<FilmIdName> filmsId) {
        this.filmsId = filmsId;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
