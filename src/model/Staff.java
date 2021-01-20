package model;

import java.net.URL;

public class Staff {
    private int staffId;

    private String nameRu;
    private String nameEn;

    private URL posterUrl;

    private String professionText;
    private String professionKey;

    public int getStaffId() {
        return staffId;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public URL getPosterUrl() {
        return posterUrl;
    }

    public String getProfessionText() {
        return professionText;
    }

    public String getProfessionKey() {
        return professionKey;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setPosterUrl(URL posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setProfessionText(String professionText) {
        this.professionText = professionText;
    }

    public void setProfessionKey(String professionKey) {
        this.professionKey = professionKey;
    }
}
