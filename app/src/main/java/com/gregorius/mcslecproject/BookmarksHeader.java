package com.gregorius.mcslecproject;

public class BookmarksHeader {
    private int bookmarkId;

    private String bookmarkName;

    public BookmarksHeader(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public BookmarksHeader(int bookmarkId, String bookmarkName) {
        this.bookmarkId = bookmarkId;
        this.bookmarkName = bookmarkName;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }
}
