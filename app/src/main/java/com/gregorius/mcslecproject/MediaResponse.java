package com.gregorius.mcslecproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MediaResponse implements Serializable
{

    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;

    public MediaResponse(Integer trackId, String artworkUrl100, String trackName, String artistName, String collectionName, String trackViewUrl, String kind, Double trackPrice)
    {
        this.trackId = trackId;
        this.artworkUrl100 = artworkUrl100;
        this.trackName = trackName;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackViewUrl = trackViewUrl;
        this.kind = kind;
        this.trackPrice = trackPrice;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }
}
