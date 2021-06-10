package com.gregorius.mcslecproject;

public class BookmarkDetail {
    private int detailId;
    private String detailTitle;
    private String detailImage;
    private String detailArtist;

    public BookmarkDetail(String detailTitle, String detailImage, String detailArtist) {
        this.detailTitle = detailTitle;
        this.detailImage = detailImage;
        this.detailArtist = detailArtist;
    }

    public BookmarkDetail(int detailId, String detailTitle, String detailImage, String detailArtist) {
        this.detailId = detailId;
        this.detailTitle = detailTitle;
        this.detailImage = detailImage;
        this.detailArtist = detailArtist;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
    }

    public String getDetailArtist() {
        return detailArtist;
    }

    public void setDetailArtist(String detailArtist) {
        this.detailArtist = detailArtist;
    }
}
