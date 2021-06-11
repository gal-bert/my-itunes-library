package com.gregorius.mcslecproject;

public class BookmarksDetail
{
    private int BookmarkId;
    private int trackId;

    public BookmarksDetail(int bookmarkId, int trackId)
    {
        BookmarkId = bookmarkId;
        this.trackId = trackId;
    }

    public int getBookmarkId()
    {
        return BookmarkId;
    }

    public void setBookmarkId(int bookmarkId)
    {
        BookmarkId = bookmarkId;
    }

    public int getTrackId()
    {
        return trackId;
    }

    public void setTrackId(int trackId)
    {
        this.trackId = trackId;
    }
}
