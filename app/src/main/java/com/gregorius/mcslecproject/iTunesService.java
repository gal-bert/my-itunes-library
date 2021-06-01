package com.gregorius.mcslecproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface iTunesService {
    @GET("/search?")
    Call<MediaListResponse> searchMedia(@Query("media") String media, @Query("term") String term);
}
