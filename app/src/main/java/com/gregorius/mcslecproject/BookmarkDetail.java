package com.gregorius.mcslecproject;

public class BookmarkDetail {
    private int detailId;
    private String detailTitle;
    private String detailImage;
    private String detailArtist;
    private int bookmarkId;

    public BookmarkDetail(int detailId, String detailTitle, String detailImage, String detailArtist, int bookmarkId) {
        this.detailId = detailId;
        this.detailTitle = detailTitle;
        this.detailImage = detailImage;
        this.detailArtist = detailArtist;
        this.bookmarkId = bookmarkId;
    }

    public BookmarkDetail(String detailTitle, String detailImage, String detailArtist, int bookmarkId) {
        this.detailTitle = detailTitle;
        this.detailImage = detailImage;
        this.detailArtist = detailArtist;
        this.bookmarkId = bookmarkId;
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

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }


}
